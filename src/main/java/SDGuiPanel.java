package main.java;

import javax.swing.*;
import java.awt.*;

public class SDGuiPanel extends JPanel {
    private SequenceDiagram sequenceDiagram;

    SDGuiPanel(SequenceDiagram sequenceDiagram)
    {
        this.sequenceDiagram = sequenceDiagram;
        this.setBackground(Color.gray);

    }


//    @Override
//    public void paint(Graphics g) {
//        super.paintComponent(g);
//        super.paintChildren(g);
//        Component[] components = this.getComponents();
//
//    }
}
