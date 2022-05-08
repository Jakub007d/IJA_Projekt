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
    private UMLParticipant participant;
    Color participantColor = Color.red;
    int height;
    SDController sdController;

    /**
     * Konštruktor triedy SDGuiParticipant.
     *
     * @param participant Účastník, ktorý sa má vykresliť.
     * @param sdController Kontroler pre sekvenčný diagram.
     */
    SDGuiParticipant(UMLParticipant participant, SDController sdController) {
        this.x = this.getX();
        this.y = this.getY()+25; // offset pre vykreslenie lifeline

        this.sdController = sdController;
        this.participant = participant;

        //nastavi meno komponentu
        this.setName(participant.getName());

        this.isPresentInCD = participant.getPresence();
        JLabel participantName = new JLabel(participant.getName());

        JPopupMenu participantPopup = new SDGuiParticipantPopup(sdController, participant, participantName);
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
                            e.getX(), e.getY());
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
                0, new float[]{9}, 0);
        g2d.setStroke(dashed);

        int offset = this.getWidth()/2;
        g2d.setColor(participantColor);
        g2d.drawLine(this.x + offset, this.y, this.x + offset, this.y+200);

        g2d.dispose();
    }
}
