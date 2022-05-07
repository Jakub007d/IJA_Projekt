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

}
