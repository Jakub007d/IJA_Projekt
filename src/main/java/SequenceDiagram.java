import java.util.ArrayList;

/**
 * Trieda reprezentuje sekvenčný diagram.
 */
public class SequenceDiagram extends Element{
    /* zoznam účastníkov sekvenčného diagramu */
    private java.util.List<UMLParticipant> participantList = new ArrayList<>();
    private java.util.List<UMLMessage> messageList = new ArrayList<>();
    /**
     * Konstruktor pro vytvoření instance diagramu.
     * @param name Název diagramu
     */
    public SequenceDiagram(String name)
    {
        super(name);
    }

    /**
     * Vytvori inštanciu UML účastníka a vloží ju do diagramu.
     * Ak v diagrame
     * @param name Názov vytváraného účastníka
     * @return Objekt reprezentujúci účastníka. Ak účasník s daným názvom už existuje, vracia null.
     */
    public UMLParticipant createParticipant(String name)
    {
        for (UMLClassifier participant : this.participantList) {
            if(participant.getName().equals(name)) {
                return null;
            }
        }
        UMLParticipant newParticipant = new UMLParticipant(name);
        this.participantList.add(newParticipant);
        return newParticipant;
    }

    /**
     * Testuje, či účasstník zodpovedá inštancii triedy z diagramu tried.
     * @param classDiagram Diagram tried.
     */
    public void checkPresence(ClassDiagram classDiagram)
    {
        for (UMLParticipant participant : this.participantList) {
            // toto je na chvilku zamyslenia
            participant.setPresence(classDiagram.checkClassifierPresence(participant.getName()));
        }
    }
}
