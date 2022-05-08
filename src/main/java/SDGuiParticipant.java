package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SDGuiParticipant extends JPanel {
    int x;
    int y;
    private boolean isPresentInCD = true;
    private UMLParticipant participant;
    Color participantColor = Color.red;
    int height;
    SDController sdController;

    SDGuiParticipant(UMLParticipant participant, SDController sdController) {
        this.x = this.getX();
        this.y = this.getY()+25;

        this.sdController = sdController;
        this.participant = participant;

        //nastavi meno komponentu
        this.setName(participant.getName());
        //System.out.println(this.getName());

        this.isPresentInCD = participant.getPresence();
        JLabel participantName = new JLabel(participant.getName());
        JPopupMenu participantPopup = new SDGuiParticipantPopup(sdController);

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

        // Draw to the copy
        int offset = this.getWidth()/2;
        g2d.setColor(participantColor);
        g2d.drawLine(this.x + offset, this.y, this.x + offset, this.y+200);

        // Get rid of the copy
        g2d.dispose();


    }
}
