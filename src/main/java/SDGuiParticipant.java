package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class SDGuiParticipant extends JPanel {
    int x;
    int y;
    private boolean isPresentInCD = true;
    private UMLParticipant participant;
    Color participantColor = Color.red;

    SDGuiParticipant(int x, int y, UMLParticipant participant)
    {
        this.x = x;
        this.y = y;
        this.participant = participant;
        //this.setBackground(Color.LIGHT_GRAY);
        JLabel participantName = new JLabel(participant.getName());
        if(isPresentInCD) {
            this.participantColor = Color.black;
        }
        participantName.setBorder(BorderFactory.createLineBorder(participantColor, 2));
        participantName.setForeground(participantColor);
        this.add(participantName);
        participantName.setVisible(true);
        setVisible(true);
        repaint();
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);
        super.paintChildren(g);
        this.setLocation(this.x,this.y);
        Graphics2D g2d = (Graphics2D) g.create();
        Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 0);
        g2d.setStroke(dashed);

        // Draw to the copy
        g2d.drawLine(this.x, this.y, this.x, this.y+200);
        // Get rid of the copy
        g2d.dispose();
        //repaint();
    }
}
