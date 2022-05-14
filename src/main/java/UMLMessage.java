package main.java;

/**
 * Trieda reprezentuje UML správu.
 * Správa má typ, odosielateľa a prijímateľa.
 *
 * @author xstrak38
 */
public class UMLMessage {
    enum UMLMessageType {
        SYN,
        ASYN,
        CREATE,
        DESTROY,
        RETURN
    }
    private UMLParticipant sender;
    private UMLParticipant recipient;
    private UMLMessageType messageType;
    private String message;
    private boolean methodExists = false;

    /**
     * Konštruktor pre UML správu.
     * @param sender Užívateľ, ktorý odosiela správu.
     * @param recipient Užívateľ, ktorý prijíma správu.
     * @param messageType Typ správy.
     * @param message Správa.
     */
    public UMLMessage(UMLParticipant sender, UMLParticipant recipient, UMLMessageType messageType, String message)
    {
        this.sender = sender;
        this.recipient = recipient;
        this.messageType = messageType;
        this.message = message;
    }

    public void setMethodExists(boolean methodExists) {
        this.methodExists = methodExists;
    }

    public boolean getMethodExists() {
        return this.methodExists;
    }

    public UMLParticipant getSender() {
        return this.sender;
    }

    public UMLParticipant getRecipient() {
        return this.recipient;
    }

    public UMLMessageType getMessageType() {
        return this.messageType;
    }

    public String getMessage() {
        return this.message;
    }
}
