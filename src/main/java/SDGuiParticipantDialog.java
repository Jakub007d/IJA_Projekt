package main.java;

import javax.swing.*;
import java.awt.*;

public class SDGuiParticipantDialog extends JPanel {
    SDController sdController;
    SDGuiParticipantDialog(SDController sdController)
    {
        this.sdController = sdController;
//        String[] items = {"One", "Two", "Three", "Four", "Five"};
//        JComboBox<String> combo = new JComboBox<>(items);
        JTextField objectNameField = new JTextField("");
        JTextField classNameField = new JTextField("");
        JPanel panel = new JPanel(new GridLayout(0, 1));
//        panel.add(combo);
        panel.add(new JLabel("name of object:"));
        panel.add(objectNameField);
        panel.add(new JLabel("name of class:"));
        panel.add(classNameField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Add participant",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Adding participant: " + objectNameField.getText()
                    + ":" + classNameField.getText());
            addParticipant(objectNameField.getText(),classNameField.getText());
        } else {
            System.out.println("Adding new participant cancelled");
        }
    }

    private void addParticipant(String objectName, String className) {
        UMLParticipant newParticipant = sdController.sequenceDiagram.createParticipant(objectName+":"+className);
        if(newParticipant != null) {
//            SDGuiParticipant participant = new SDGuiParticipant(newParticipant);
//            participant.setPreferredSize(new Dimension(100,100));
//            participant.setBorder(BorderFactory.createLineBorder(Color.red));
//            sdController.sdView.SDViewMainPanel.add((JPanel) participant);
//            sdController.sdView.setVisible(true);
            if (sdController.classDiagram != null) {
                sdController.sequenceDiagram.checkParticipantPresence(sdController.classDiagram);
            } else System.out.println("ADDPARTICIPANT - CD == NULL");
            sdController.sdView.drawSD(sdController.sequenceDiagram);
            sdController.sdView.setVisible(true);
        }
    }
}
