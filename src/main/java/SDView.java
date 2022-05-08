package main.java;

import javax.swing.*;
import java.awt.*;

/**
 * Trieda reprezentuje okno pre vykreslenie sekvenčného diagramu. Dedí z JFrame.
 */
public class SDView extends JFrame {

    public SDGuiPanel SDViewMainPanel;
    public SDController sdController;

    /**
     * Konštruktor triedy SDView
     * @param name Názov súboru, ktorý sa otvára.
     * @param sd Štruktúra vykresľovaného sekvenčného diagramu.
     * @param cd Štruktúra diagramu tried, ku ktorému sa zobrazuje sekvenčný diagram.
     */
    SDView(String name, SequenceDiagram sd, ClassDiagram cd) {
        this.setTitle("Sequence Diagram: "+name);
        this.setSize(720,520);

        sdController = new SDController(this);

        //nastavi SD na nacitany zo suboru
        sdController.setSequenceDiagram(sd);
        sdController.setClassDiagram(cd);
        sdController.sequenceDiagram.checkConsistence(sdController.classDiagram);

        //panel pre pridavanie ucastnikov diagramu
        SDViewMainPanel = new SDGuiPanel(sd);
        SDViewMainPanel.setLayout(new BoxLayout(SDViewMainPanel, BoxLayout.X_AXIS));

        //vykresli SD zo suboru
        drawSD(sd);
        this.setVisible(true);

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
            UMLParticipant tmpParticipant = sd.participantAtPosition(pos);
            SDGuiParticipant participantPanel = new SDGuiParticipant(tmpParticipant, sdController);
            participantPanel.setPreferredSize(new Dimension(100,100));

            SDViewMainPanel.add((JPanel) participantPanel);
        }
        repaint();
        SDViewMainPanel.setVisible(true);
    }
}
