package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Trieda slúži na vykreslenie jedného účastníka diagramu. Rozširuje JPanel.
 *
 * @author xstrak38
 */
public class SDGuiParticipant extends JPanel {
    int x;
    int y;
    private boolean isPresentInCD = true;
    private UMLParticipant participantRef;
    Color participantColor = Color.red;
    int height;
    SDController sdController;

    /**
     * Konštruktor triedy SDGuiParticipant.
     *
     * @param participantRef Účastník, ktorý sa má vykresliť.
     * @param sdController Kontroler pre sekvenčný diagram.
     */
    SDGuiParticipant(UMLParticipant participantRef, SDController sdController) {
        this.x = this.getX();
        this.y = this.getY()+25; // offset pre vykreslenie lifeline

        this.sdController = sdController;
        this.participantRef = participantRef;

        //nastavi meno komponentu
        this.setName(participantRef.getName());

        this.isPresentInCD = participantRef.getPresence();
        JLabel participantName = new JLabel(participantRef.getName());

        JPopupMenu participantPopup = new SDGuiParticipantPopup(sdController, participantRef, participantName, this);
        participantName.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    participantPopup.show(e.getComponent(),
                            e.getComponent().getWidth(), e.getY()); //popup sa zobrazi vedla jlabel
                }
            }
        });


        if(isPresentInCD) {
            this.participantColor = Color.black;
        }
        participantName.setBorder(BorderFactory.createLineBorder(participantColor, 2));
        participantName.setForeground(participantColor);
        participantName.setOpaque(true);
        height = participantName.getHeight();
        this.add(participantName);
        setVisible(true);
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);
        super.paintChildren(g);
//        this.setLocation(this.x,this.y); // <-- toto to totalne dokazilo
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{5}, 0);
        g2d.setStroke(dashed);

        int offset = this.getWidth()/2;
        g2d.setColor(participantColor);
        g2d.drawLine(this.x + offset, this.y, this.x + offset, this.y+200);

        g2d.dispose();
    }

    /**
     * vymaze ucastnika z diagramu.
     */
    public void deleteParticipant()
    {
        sdController.sequenceDiagram.deleteParticipant(participantRef.getName());

        sdController.sdView.drawSD(sdController.sequenceDiagram);
        sdController.sdView.setVisible(true);
    }
}
