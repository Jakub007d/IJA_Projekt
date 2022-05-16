package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SDGuiDeleteMessageDialog {
    SDController sdController;

    SDGuiDeleteMessageDialog(SDController sdController) {
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

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Message:"));
        panel.add(comboMessage);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add message",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            /* vymazanie spravy z diagramu */
            deleteMessage();
        }
    }

    private void deleteMessage(){}
}
