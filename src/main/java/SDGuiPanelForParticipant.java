package main.java;

import javax.swing.*;
import java.awt.*;

public class SDGuiPanelForParticipant extends JPanel {
    private UMLParticipant guiParticipant;
    private SequenceDiagram sequenceDiagram;
    Point startCorner;
    SDGuiPanelForParticipant(UMLParticipant umlParticipant, SequenceDiagram sequenceDiagram)
    {
        this.sequenceDiagram = sequenceDiagram;
        this.guiParticipant = umlParticipant;
        JTextField participantName = new JTextField(this.guiParticipant.getName());

        startCorner = new Point(20,20);
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        super.paintChildren(g);
        this.setLocation((int)this.startCorner.getX(),(int)this.startCorner.getY());
    }
}
