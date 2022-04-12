public class UMLMessage extends UMLClassifier {
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

    public UMLMessage(String name, UMLParticipant sender, UMLParticipant recipient, UMLMessageType messageType)
    {
        super(name);
        this.sender = sender;
        this.recipient = recipient;
        this.messageType = messageType;
    }
}
