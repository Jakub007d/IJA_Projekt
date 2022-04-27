/**
 * Trieda reprezentuje UML správu.
 * Správa má typ, odosielateľa a prijímateľa.
 *
 * @author xstrak38
 */
public class UMLMessage {
    enum UMLMessageType {
        SYN,
        ASYNC,
        RETURN,
        CREATE,
        DESTROY,
        ACTIVATE,
        DEACTIVATE;
    }
    private UMLParticipant sender;
    private UMLParticipant recipient;
    private UMLMessageType messageType;

    /**
     * Konštruktor pre UML správu.
     *
     * @param sender Užívateľ, ktorý odosiela správu.
     * @param recipient Užívateľ, ktorý prijíma správu.
     * @param messageType Typ správy.
     */
    public UMLMessage(UMLParticipant sender, UMLParticipant recipient, UMLMessageType messageType)
    {
        this.sender = sender;
        this.recipient = recipient;
        this.messageType = messageType;
    }
}