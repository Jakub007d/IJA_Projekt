package main.java;

import javax.swing.*;
import java.awt.*;
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
    SDGuiParticipant sdGuiParticipant;

    /**
     * Konštruktor triedy SDGuiParticipantPopup.
     *  @param sdController Kontroler pre sekvenčný diagram.
     * @param participant Účastník, pre ktorý sa má vykonať akcia.
     * @param participantName Referencia na JLabel s názvom účastníka.
     * @param sdGuiParticipant Referencia na JPanel, do ktorehu je vykresleny ucastnik.
     */
    public SDGuiParticipantPopup(SDController sdController, UMLParticipant participant, JLabel participantName, SDGuiParticipant sdGuiParticipant){
        this.sdController = sdController;
        this.sequenceDiagram = sdController.sequenceDiagram;
        this.participant = participant;
        this.participantName = participantName;
        this.sdGuiParticipant = sdGuiParticipant;
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
        if (e.getSource() == deleteParticipant) {
            //vymaze ucastnika z view aj zo sekvencneho diagramu
            sdGuiParticipant.deleteParticipant();

            sdController.sdView.drawSD(sdController.sequenceDiagram);
            sdController.sdView.setVisible(true);
        }
    }
}
