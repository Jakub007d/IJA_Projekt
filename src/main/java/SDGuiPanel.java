package main.java;

import javax.swing.*;
import java.awt.*;
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
                        if (Objects.equals(panel.getName(), message.getSender().getName())) {
                            int index = sequenceDiagram.messagePosition(message);

                            Graphics2D g2d = (Graphics2D) g.create();
                            //settings for message color
                            if (message.getMethodExists()) {
                                g2d.setColor(Color.black);
                            } else {
                                g2d.setColor(Color.red);
                            }

                            // settings for message line type
                            switch (message.getMessageType()) {
                                case RETURN:
                                    Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT,
                                            BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                                    g2d.setStroke(dashed);
                                    break;
                                default:
                                    g2d.setStroke(new BasicStroke(2));
                            }

                            int offset = 40 + index * 40;
                            int x = panel.getX() + panel.getWidth() / 2;
                            int y = panel.getY() + offset;
                            int sender = sequenceDiagram.participantPosition(message.getSender());
                            int recipient = sequenceDiagram.participantPosition(message.getRecipient());

                            //dlzka spravy a smer
                            int direction = panel.getWidth() * (recipient-sender);

                            //smer spravy
                            int dir;
                            if (direction > 0) {
                                dir = -1;
                            } else {
                                dir = 1;
                            }

                            //nakresli ciaru spojujucu ucastnikov medzi ktorymi je sprava
                            if (message.getMessageType() == UMLMessage.UMLMessageType.DESTROY) {
                                g2d.drawLine(x, y, x + direction + dir*10, y);
                            } else {
                                g2d.drawLine(x, y, x + direction, y);
                            }

                            //vypise text
                            int strWidth = g2d.getFontMetrics().stringWidth(message.getMessage());
                            g2d.drawString(message.getMessage(),x+direction/2-strWidth/2, y-10);

                            // test jlabel
                            JLabel label = new JLabel(message.getMessage());
                            label.setBackground(Color.orange);
                            // test jlabel

                            // typ sipky
                            switch (message.getMessageType()) {
                                case RETURN:
                                case ASYN:
                                    drawArrow(g2d, x+direction, y, dir);
                                    break;
                                case DESTROY:
                                    drawDestroyArrow(g2d, x + direction, y, dir);
                                    break;
                                default:
                                    drawFilledArrow(g2d, x+direction, y, dir);
                            }
                            // Get rid of the copy
                            g2d.dispose();
                        }
                    }
                }
            }
        }
    }
}
