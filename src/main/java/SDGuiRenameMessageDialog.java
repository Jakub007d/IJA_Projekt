package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SDGuiRenameMessageDialog {

    SDController sdController;

    SDGuiRenameMessageDialog(SDController sdController) {
        this.sdController = sdController;

        // ziskanie sprav diagramu
        List<UMLMessage> tmp = sdController.sequenceDiagram.getMessageList();
        ArrayList<String> items = new ArrayList<String>();
        for (UMLMessage m : tmp) {
            items.add(m.getMessage());
        }
        // transformacia na pole typu String
        String[] messageStrings = items.toArray(String[]::new);

        JComboBox<String> comboMessage = new JComboBox<>(messageStrings);
        JTextField messageField = new JTextField("");

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Message:"));
        panel.add(comboMessage);
        panel.add(new JLabel("New message:"));
        panel.add(messageField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Rename message",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            /* premenovanie spravy */
            renameMessage(comboMessage.getSelectedIndex(),messageField.getText());
        }
    }

    private void renameMessage(int selectedIndex, String text) {
        sdController.sequenceDiagram.renameMessage(selectedIndex,text);
        sdController.sdView.SDViewMainPanel.removeAll();
        sdController.sequenceDiagram.checkConsistence(sdController.classDiagram);
        sdController.sdView.drawSD(sdController.sequenceDiagram);
        sdController.sdView.setVisible(true);
    }
}
