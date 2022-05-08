package main.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Trieda implementuje rozhranie pre premenovanie alebo vymazanie účastníka diagramu.
 */
public class SDGuiParticipantPopup extends JPopupMenu implements ActionListener {
    JButton renameParticipant = new JButton("Rename");
    JButton deleteParticipant = new JButton("Delete");
    JTextField textField = new JTextField();
    SequenceDiagram sequenceDiagram;
    UMLParticipant participant;
    JLabel participantName;
    SDController sdController;

    /**
     * Konštruktor triedy SDGuiParticipantPopup.
     *
     * @param sdController Kontroler pre sekvenčný diagram.
     * @param participant Účastník, pre ktorý sa má vykonať akcia.
     * @param participantName Referencia na JLabel s názvom účastníka.
     */
    public SDGuiParticipantPopup(SDController sdController, UMLParticipant participant, JLabel participantName){
        this.sdController = sdController;
        this.sequenceDiagram = sdController.sequenceDiagram;
        this.participant = participant;
        this.participantName = participantName;
        this.renameParticipant.addActionListener(this);
        this.deleteParticipant.addActionListener(this);
        textField.setColumns(7);
        this.add(textField);
        this.add(renameParticipant);
        this.add(deleteParticipant);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == renameParticipant) {
            int index = sdController.sequenceDiagram.participantPosition(participant);

            sdController.sequenceDiagram.renameParticipant(index, textField.getText());

            participantName.setText(textField.getText());
            sdController.sequenceDiagram.checkConsistence(sdController.classDiagram);

            sdController.sdView.drawSD(sdController.sequenceDiagram);
            sdController.sdView.setVisible(true);
        }
    }
}
