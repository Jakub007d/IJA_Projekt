package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Trieda reprezentuje sekvenčný diagram.
 * Je odvodený z triedy Element.
 * Obsahuje zoznam účastníkov diagramu a zoznam správ.
 *
 * @author xstrak38
 */
public class SequenceDiagram extends Element {
    /* zoznam účastníkov sekvenčného diagramu */
    private java.util.List<UMLParticipant> participantList = new ArrayList<>();
    /* zoznam správ sekvenčného diagramu */
    private java.util.List<UMLMessage> messageList = new ArrayList<>();

    /**
     * Konštruktor pre vytvorenie inštancie diagramu.
     *
     * @param name Názov diagramu
     */
    public SequenceDiagram(String name) {
        super(name);
    }

    public List<UMLParticipant> getParticipantList() {
        return participantList;
    }

    public List<UMLMessage> getMessageList() {
        return messageList;
    }

    /**
     * Vytvorí inštanciu UML účastníka a vloží ju do diagramu.
     *
     * @param name Názov vytváraného účastníka
     * @return Objekt reprezentujúci účastníka. Ak účasník s daným názvom už existuje, vracia null.
     */
    public UMLParticipant createParticipant(String name) {
        if (participantWithName(name) == null) {
            UMLParticipant newParticipant = new UMLParticipant(name);
            this.participantList.add(newParticipant);
            return newParticipant;
        } else {
            return null;
        }
    }

    /**
     * Prida spravu do sekvencneho diagramu.
     * @param sender
     * @param recipient
     * @param type
     * @param message
     */
    public void addMessage(UMLParticipant sender, UMLParticipant recipient, UMLMessage.UMLMessageType type, String message) {
        UMLMessage newMessage = new UMLMessage(sender, recipient, type, message);
        this.messageList.add(newMessage);
    }
    public int numberOfMessages()
    {
        return this.messageList.size();
    }

    /**
     * Vracia pozíciu v zozname správ.
     * Ak diagram danú správú neobsahuje, vráti -1.
     *
     * @param message Správa v sekvenčnom diagrame.
     * @return Pozíca správy v zozname.
     */
    public int messagePosition(UMLMessage message) {
        int len = numberOfMessages();
        for (int i = 0 ; i < len ; i++) {
            if(this.messageList.get(i).equals(message)) return i;
        }
        return -1;
    }

    /**
     * Sprístupňuje informáciu o počte účastníkov sekvenčného diagramu.
     *
     * @return Počet účastníkov dekvenčného diagramu.
     */
    public int numberOfParticipants()
    {
        return this.participantList.size();
    }

    /**
     * Vracia pozíciu v zozname účastníkov.
     * Ak diagram účastníka neobsahuje, vráti -1.
     *
     * @param participant Účastník sekvenčného diagramu.
     * @return Pozíca účastníka v zozname.
     */
    public int participantPosition(UMLParticipant participant) {
        int len = numberOfParticipants();
        for (int i = 0 ; i < len ; i++) {
            if(this.participantList.get(i).getName().equals(participant.getName())) return i;
        }
        return -1;
    }

    /**
     * Vracia účastníka na danej pozícii. Ak je pozícia mimo medzí, vráti null.
     * @param position Pozícia účastníka.
     * @return Hľadaný účastník.
     */
    public UMLParticipant participantAtPosition(int position)
    {
        if (position - 1 > numberOfParticipants()) {
            System.err.println("Out of bounds in participantList");
            return null;
        }
        else {
            return this.participantList.get(position);
        }
    }

    public UMLMessage messageAtPosition(int position)
    {
        if (position - 1 > numberOfMessages()) {
            System.err.println("Out of bounds in messageList");
            return null;
        }
        else {
            return this.messageList.get(position);
        }
    }

    /**
     * vrati ucastnika diagramu podla nazvu, ak ucastnik s danym nazvom neexistuje, vrati null.
     * @param name
     * @return
     */
    public UMLParticipant participantWithName(String name)
    {
        for (UMLParticipant participant : this.participantList) {
            if (participant.getName().equals(name)) {
                return participant;
            }
        }
        return null;
    }

    /**
     * Premenuje účastníka na danej pozícii.
     * @param index Pozícia v zozname účastníkov.
     * @param name Nový názov účastníka.
     */
    public void renameParticipant(int index, String name) {
        UMLParticipant p = participantAtPosition(index);
        if (p != null) {
            p.setName(name);
        }
    }

    /**
     * Testuje, či účastník zodpovedá inštancii triedy z diagramu tried.
     * Nastaví účastníkom sekvenčného diagramu atribút "isPresentInCD"
     * na true, ak sa ekvivalentná trieda nachádza v diagrame tried,
     * inak false.
     *
     * @param classDiagram Diagram tried.
     */
    public void checkParticipantPresence(ClassDiagram classDiagram)
    {
        for (UMLParticipant participant : this.participantList) {
            // toto je na chvilku zamyslenia
            participant.setPresence(classDiagram.checkClassifierPresence(participant.getClassName()));
            // nastavi referenciu na odpovedajucu triedu v CD
            UMLClass result = classDiagram.getClassByName(participant.getClassName());
            if (result != null)
            {
                participant.setUmlClass(result);
            }
        }
    }

    /**
     * Testje, či správa v sekvenčnom diagrame zodpovedá metóde z diagramu tried.
     * @param classDiagram Diagram tried.
     */
    public void checkOperationPresence(ClassDiagram classDiagram)
    {
        for (UMLMessage message : this.messageList) {
            String messageRecipient = message.getRecipient().getClassName();
            UMLClass tmpRefClass = (UMLClass) classDiagram.findClassifier(messageRecipient);
                switch (message.getMessageType()) {
                    case ASYN:
                    case SYN:
                        if (tmpRefClass != null) {
                            checkOperation(message, tmpRefClass);
                        }
                        else message.setMethodExists(false);
                        break;
                    case CREATE:
                        System.out.println("CREATE: "+message+" => "+message.getMessage());
                        if (tmpRefClass != null) {
                            checkConstructor(message, tmpRefClass);
                        } else message.setMethodExists(false);
                        break;
                    case DESTROY:
                    case RETURN:
                        message.setMethodExists(true);
                        break;
                }
        }
    }

    public void checkConstructor(UMLMessage message, UMLClass tmpRefClass) {
        try {
            for (UMLOperation operation : tmpRefClass.getOperations()) {
                if (operation.isConstructor(tmpRefClass)) { //TODO isConstructor
                    message.setMethodExists(true);
                    return; // konstruktor najdeny
                }
            }
            // trieda neobsahuje konstruktor

            // rekurzivne volanie funkcie pre triedu, z ktorej "tmpRefClass" dedi
            if (tmpRefClass.isChild()) {
                checkConstructor(message,tmpRefClass.getParentClass());
                return;
            }
        } catch (Exception e) {
            //
        }
        message.setMethodExists(false);
    }

    public void checkOperation(UMLMessage message, UMLClass tmpRefClass) {
        String[] tmp = message.getMessage().split("\\(",2);
        //                     ^
        //                 "metoda(argumenty)"
        String messageMethodName = tmp[0]; // "metoda"
        try {
            // porovna kazdu operaciu triedy "tmpRefClass" so spravou
            for (UMLOperation operation : tmpRefClass.getOperations()) {
                if (operation.getName().equals(messageMethodName)) {
                    message.setMethodExists(true);
                    return; // zhoda najdena
                }
            }
            // odpovedajuca metoda nebola najdena

            // rekurzivne volanie funkcie pre triedu, z ktorej "tmpRefClass" dedi
            if (tmpRefClass.isChild()) {
                checkOperation(message,tmpRefClass.getParentClass());
            }
        }
        catch (Exception e) {
            /* System.err.println("checkOperationPresence: "+e); */
        }
    }

    /**
     * Zisťuje, či sú správy a účastníci sekvenčného diagramu konzistentné s diagramom tried.
     *
     * @param classDiagram Zobrazený diagram tried.
     */
    public void checkConsistence(ClassDiagram classDiagram)
    {
        this.checkParticipantPresence(classDiagram); /* Nastaví účastníkom sekvenčného diagramu atribút "isPresentInCD" */
        this.checkOperationPresence(classDiagram);   /* Nastaví správam v sekvenčnom diagrame atribút "methodExists" */
    }

    /**
     * Vymaze zo sekvencneho diagramu ucastnika s nazvom "name".
     *
     * @param name nazov mazaneho ucastnika.
     */
    public void deleteParticipant(String name)
    {
        this.participantList.removeIf(participant -> participant.getName().equals(name));
        this.messageList.removeIf(message -> message.getSender().getName().equals(name));
        this.messageList.removeIf(message -> message.getRecipient().getName().equals(name));
    }

    /**
     * Vymaze zo sekvencneho diagramu spravu.
     *
     * @param participant
     */
    public void deleteMessage(UMLParticipant participant)
    {
//TODO
    }

}
