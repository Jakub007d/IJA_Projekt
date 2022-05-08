package main.java;

import javax.swing.*;
import java.awt.event.*;

/**
 * Trieda reprezentuje JPopupMenu, ktoré umožňuje pridať účastníka alebo správu do sekvenčného diagramu.
 */
public class SDPopupMenu extends JPopupMenu implements ActionListener {
    JMenuItem addParticipant;
    JMenuItem addMessage;

    JPopupMenu SDPopup;

    JPanel SDViewMainPanel;
    JFrame SDView;

    SDController sdController;
    SequenceDiagram sequenceDiagram;

    /**
     * Konštruktor triedy SDPopupMenu.
     *
     * @param frame Okno aplikácie.
     * @param SDViewMainPanel Hlavný panel, do ktorého sa vykresľuje sekvenčný diagram.
     * @param sdController Kontroler pre sekvenčný diagram.
     */
    public SDPopupMenu(JFrame frame, JPanel SDViewMainPanel, SDController sdController) {
        this.SDPopup = new JPopupMenu();
        this.SDViewMainPanel = SDViewMainPanel;
        this.sdController = sdController;
        this.sequenceDiagram = sdController.sequenceDiagram;

        this.SDView = frame;

        // Pridanie noveho ucastnika
        addParticipant = new JMenuItem("Add participant");
        addParticipant.addActionListener(this);
        SDPopup.add(addParticipant);

        // Pridanie novej spravy
        addMessage = new JMenuItem("Add message");
        addMessage.addActionListener(this);
        SDPopup.add(addMessage);

        frame.addMouseListener(new MouseAdapter() {
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
                    SDPopup.show(e.getComponent(),
                            e.getX(), e.getY());
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addParticipant) {
            new SDGuiParticipantDialog(sdController);
        }
        if (e.getSource()== addMessage) {
            SDView.setVisible(true);
        }

    }
}
