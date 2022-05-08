package main.java;

import javax.swing.*;
import java.awt.*;

public class SDView extends JFrame {

    public SDGuiPanel SDViewMainPanel;
    public SDController sdController;

    SDView(String name, SequenceDiagram sd, ClassDiagram cd) {
        this.setTitle("Sequence Diagram: "+name);
        this.setSize(720,520);

        //panel pre pridavanie ucastnikov diagramu
        SDViewMainPanel = new SDGuiPanel(sd);
        SDViewMainPanel.setLayout(new BoxLayout(SDViewMainPanel, BoxLayout.X_AXIS));

        //
        sdController = new SDController(this);
        //nastavi SD na nacitany zo suboru
        sdController.setSequenceDiagram(sd);
        sdController.setClassDiagram(cd);

        //vykresli SD zo suboru
        drawSD(sd);
        this.setVisible(true);
        //

        //TODO: sd -> sdController
        new SDPopupMenu(this,SDViewMainPanel,sdController);
        new SDGuiMenuBar(this, sdController);

        this.add(SDViewMainPanel);
        this.setVisible(true);
    }

    /**
     * Vykreslí štruktúru sekvenčného diagramu.
     *
     * @param sd Sekvenčný diagram.
     */
    public void drawSD(SequenceDiagram sd) {
        SDViewMainPanel.removeAll();
        for (int pos = 0 ; pos < sd.numberOfParticipants() ; pos++) {
            System.out.println(pos);
            UMLParticipant tmpParticipant = sd.participantAtPosition(pos);
            SDGuiParticipant participantPanel = new SDGuiParticipant(tmpParticipant, sdController);
            participantPanel.setPreferredSize(new Dimension(100,100));
            participantPanel.setBorder(BorderFactory.createLineBorder(Color.red));
            SDViewMainPanel.add((JPanel) participantPanel);

            //toto prida margin
            //SDViewMainPanel.add(Box.createRigidArea(new Dimension(10,0)));

        }
        repaint();
        SDViewMainPanel.setVisible(true);
    }
}
