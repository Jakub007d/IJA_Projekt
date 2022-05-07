package main.java;

import javax.swing.*;
import java.awt.*;

public class SDView extends JFrame {

    public SDGuiPanel SDViewMainPanel;

    SDView(String name) {
        this.setTitle("Sequence Diagram: "+name);
        this.setSize(720,520);

        /*----------------*/
        SequenceDiagram sd = new SequenceDiagram("My Sequence Diagram");

        sd.createParticipant("tvoja:Mamka");
        sd.createParticipant("moja:Mamka");
        sd.createParticipant("tvoja:Person");
        sd.createParticipant("moja:Person");
        /*----------------*/

        SDViewMainPanel = new SDGuiPanel(sd);
        SDViewMainPanel.setLayout(new BoxLayout(SDViewMainPanel, BoxLayout.X_AXIS));
        SDViewMainPanel.setBackground(Color.darkGray);



        new SDPopupMenu(this,SDViewMainPanel,sd);
        new SDGuiMenuBar(this);

        this.add(SDViewMainPanel);
        this.setVisible(true);
    }
}
