package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

public class SDGuiPanel extends JPanel {
    private SequenceDiagram sequenceDiagram;

    SDGuiPanel(SequenceDiagram sequenceDiagram)
    {
        this.sequenceDiagram = sequenceDiagram;
        this.setBackground(Color.gray);
    }

    public SequenceDiagram getSequenceDiagram() {
        return sequenceDiagram;
    }

    private void drawArrow(Graphics g, int x, int y, int dir){
        g.drawLine(x, y, x + dir*10, y-dir*5);
        g.drawLine(x, y, x + dir*10, y+dir*5);
    }

    private void drawFilledArrow(Graphics g, int x, int y, int dir) {
        int[] xs = {x, x + dir*10, x + dir*10};
        int[] ys = {y, y - dir*5, y + dir*5};
        g.drawPolygon(xs,ys,3);
        g.fillPolygon(xs,ys,3);
    }

    private void drawDestroyArrow(Graphics g, int x, int y, int dir) {
        drawFilledArrow(g,x+dir*10,y,dir);
        g.drawLine(x+dir*10, y+dir*10, x-dir*10, y-dir*10);
        g.drawLine(x+dir*10, y-dir*10, x-dir*10, y+dir*10);
    }

    /**
     * vracia text, podla typu spravy.
     * @param message sprava, ktora sa posiela.
     * @return text spravy, ktora sa vykresli.
     */
    private String messageText(UMLMessage message) {
        switch (message.getMessageType()) {
            case CREATE:
                return "<<create>>"+message.getMessage();
            case DESTROY:
                return "<<destroy>>" + message.getMessage();
            default:
                return message.getMessage();
        }
    }

    /**
     * nastavi farbu spravy.
     * @param g
     * @param message
     */
    private void setMessageColor(Graphics g, UMLMessage message) {
        if (message.getMethodExists()) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.red);
        }
    }

    /**
     * nastavi typ ciary sprave
     * @param g
     * @param message
     */
    public void setLineType(Graphics2D g, UMLMessage message) {
        if (message.getMessageType() == UMLMessage.UMLMessageType.RETURN) {
            Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g.setStroke(dashed);
        } else {
            g.setStroke(new BasicStroke(2));
        }
    }

    private void drawMessage(Graphics g, int index, Component panel, UMLMessage message) {
        int offset = 40 + index * 40;
        int x = panel.getX() + panel.getWidth() / 2;
        int y = panel.getY() + offset;
        int sender = sequenceDiagram.participantPosition(message.getSender());
        int recipient = sequenceDiagram.participantPosition(message.getRecipient());
        int direction = panel.getWidth() * (recipient-sender);
        int dir = arrowDirection(direction);

        drawMessageLine(g,x,y,direction,dir,message.getMessageType());
        //nastavi text spravy
        String messageString = messageText(message);
        //vypise text
        int strWidth = g.getFontMetrics().stringWidth(messageString);

        //pozadie rect
        Color textColor = g.getColor();
        g.setColor(Color.white);
        Rectangle2D r = g.getFontMetrics().getStringBounds(messageString, g);
        g.fillRect(x + direction / 2 - strWidth / 2,
                y - 10 - g.getFontMetrics().getAscent(),
                (int) r.getWidth(),
                (int) r.getHeight());
        g.setColor(textColor);


        g.drawString(messageString, x + direction / 2 - strWidth / 2, y - 10);
        drawMessageArrow(g,x,y,direction,dir,message.getMessageType());

    }

    private void drawMessageLine(Graphics g, int x, int y, int direction, int dir, UMLMessage.UMLMessageType type) {
        if (type == UMLMessage.UMLMessageType.DESTROY) {
            g.drawLine(x, y, x + direction + dir*10, y);
        } else {
            g.drawLine(x, y, x + direction, y);
        }
    }

    private void drawMessageArrow(Graphics g, int x, int y, int direction, int dir, UMLMessage.UMLMessageType type) {
        switch (type) {
            case RETURN:
            case ASYN:
                drawArrow(g, x+direction, y, dir);
                break;
            case DESTROY:
                drawDestroyArrow(g, x + direction, y, dir);
                break;
            default:
                drawFilledArrow(g, x+direction, y, dir);
        }
    }

    private int arrowDirection(int direction) {
        if (direction > 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Component[] components = this.getComponents();
        java.util.List<UMLMessage> messageList = sequenceDiagram.getMessageList();

        if (components.length != 0) {

            // panel <- obsahuje participanta a jeho lifeline
            for (Component panel : components) {

                if (panel.getName() != null) {

                    // message <- sprava v sd
                    for (UMLMessage message : messageList) {
                        message.senderUpdate(sequenceDiagram.getParticipantList());
                        message.recipientUpdate(sequenceDiagram.getParticipantList());
                        if (Objects.equals(panel.getName(), message.getSender().getName())) {
                            int index = sequenceDiagram.messagePosition(message);
                            Graphics2D g2d = (Graphics2D) g.create();
                            //settings for message color
                            setMessageColor(g2d,message);

                            // settings for message line type
                            setLineType(g2d,message);

                            // nakresli spravu
                            drawMessage(g2d,index,panel,message);

                            // test jlabel
                            JLabel label = new JLabel(message.getMessage());
                            label.setBackground(Color.orange);
                            // test jlabel

                            // Get rid of the copy
                            g2d.dispose();
                        }
                    }
                }
            }
        }
    }
}
