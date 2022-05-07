package main.java;

import javax.swing.*;

public class SDView extends JFrame {

    public JPanel SDViewMainPanel;

    SDView(String name) {
        this.setTitle("Sequence Diagram: "+name);
        this.setSize(420,420);

        SDViewMainPanel = new JPanel();
        SDViewMainPanel.setLayout(new BoxLayout(SDViewMainPanel, BoxLayout.X_AXIS));

        /*----------------*/
        SequenceDiagram sd = new SequenceDiagram("My Sequence Diagram");

        sd.createParticipant("tvoja:Mamka");
        sd.createParticipant("moja:Mamka");
        sd.createParticipant("moja:Person");
        /*----------------*/

        new SDPopupMenu(this,SDViewMainPanel,sd);
        new SDGuiMenuBar(this);

        this.add(SDViewMainPanel);
        this.setVisible(true);
    }
}
