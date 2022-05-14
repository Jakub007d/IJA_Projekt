package main.java;

import javax.swing.*;
import java.awt.*;

/**
 * Trieda reprezentuje dialog pre pridanie účastníka sekvenčného diagramu.
 *
 * @author xstrak38
 */
public class SDGuiParticipantDialog extends JPanel {
    SDController sdController;

    /**
     * Konštruktor triedy SDGuiParticipantDialog.
     *
     * @param sdController Kontroler pre sekvenčný diagram.
     */
    SDGuiParticipantDialog(SDController sdController)
    {
        this.sdController = sdController;

        JTextField objectNameField = new JTextField("");
        JTextField classNameField = new JTextField("");
        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("name of object:"));
        panel.add(objectNameField);
        panel.add(new JLabel("name of class:"));
        panel.add(classNameField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add participant",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            /* pridanie ucastnika do diagramu */
            addParticipant(objectNameField.getText(), classNameField.getText());
        }
    }

    /**
     * Trieda pridá účastníka do sekvenčného diagramu.
     *
     * @param objectName Názov objektu.
     * @param className Názov triedy.
     */
    private void addParticipant(String objectName, String className) {
        UMLParticipant newParticipant = sdController.sequenceDiagram.createParticipant(objectName+":"+className);
        if(newParticipant != null) {
            if (sdController.classDiagram != null) {
                sdController.sequenceDiagram.checkConsistence(sdController.classDiagram);
            }
            sdController.sdView.drawSD(sdController.sequenceDiagram);
            sdController.sdView.setVisible(true);
        }
    }
}
