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

    /**
     * Vytvorí inštanciu UML účastníka a vloží ju do diagramu.
     *
     * @param name Názov vytváraného účastníka
     * @return Objekt reprezentujúci účastníka. Ak účasník s daným názvom už existuje, vracia null.
     */
    public UMLParticipant createParticipant(String name) {
        for (UMLClassifier participant : this.participantList) {
            if (participant.getName().equals(name)) {
                return null;
            }
        }
        UMLParticipant newParticipant = new UMLParticipant(name);
        this.participantList.add(newParticipant);
        return newParticipant;
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

            UMLClass result = classDiagram.getClassByName(participant.getClassName());
            if (result != null)
            {
                participant.setUmlClass(result);
            }
        }
    }

    // TODO: toto je dost zle, treba prerobit struktury pre SD
    public void checkOperationPresence(ClassDiagram classDiagram)
    {
        for (UMLMessage message : this.messageList) {
            switch (message.getMessageType()) {
                case ASYN:
                case SYN:
                    String[] tmp = message.getMessage().split("\\(",2);
                    //                     ^
                    //                 "metoda(argumenty)"
                    String messageMethodName = tmp[0]; // "metoda"
                    String messageRecipient = message.getRecipient().getClassName();
                    try {
                        UMLClass tmpRecClass = (UMLClass) classDiagram.findClassifier(messageRecipient);
                        for (UMLAttribute attribute : tmpRecClass.getAttributes()) {
                            if (!attribute.getName().equals(messageMethodName)) {
                                message.setMethodExists(false);
                                break;
                            }
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
            }
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
}
