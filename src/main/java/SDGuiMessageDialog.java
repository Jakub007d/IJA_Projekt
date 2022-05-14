package main.java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Trieda reprezentuje dialog pre pridanie spravy v sekvencnom diagrame.
 *
 * @author xstrak38
 */
public class SDGuiMessageDialog extends JPanel {
    SDController sdController;

    /**
     * Konštruktor triedy SDGuiMessageDialog.
     *
     * @param sdController Kontroler pre sekvenčný diagram.
     */
    SDGuiMessageDialog(SDController sdController) {
        this.sdController = sdController;

        // ziskanie ucastnikov diagramu
        List<UMLParticipant> tmp = sdController.sequenceDiagram.getParticipantList();
        ArrayList<String> items = new ArrayList<String>();
        for (UMLParticipant p : tmp) {
            items.add(p.getName());
        }
        // transformacia na pole typu String
        String[] participantStrings = items.toArray(String[]::new);
        // typy sprav
        String[] messageTypes = {"Synchronous", "Asynchronous", "Create", "Destroy", "Return"};

        JComboBox<String> comboSender = new JComboBox<>(participantStrings);   /*combo box pre odosielatela spravy*/
        JComboBox<String> comboReceiver = new JComboBox<>(participantStrings); /*combo box pre prijimatela spravy*/
        JComboBox<String> comboType = new JComboBox<>(messageTypes); /*combo box pre typy sprav*/
        JTextField messageField = new JTextField(""); /*odosielana sprava*/

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Sender:"));
        panel.add(comboSender);
        panel.add(new JLabel("Receiver:"));
        panel.add(comboReceiver);
        panel.add(new JLabel("Message type:"));
        panel.add(comboType);
        panel.add(new JLabel("Message:"));
        panel.add(messageField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add message",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            /* pridanie spravy do diagramu */
            //addParticipant(objectNameField.getText(),classNameField.getText());
            addMessage(comboSender.getSelectedItem().toString(),
                    comboReceiver.getSelectedItem().toString(),
                    comboType.getSelectedIndex(),
                    messageField.getText());
        }
    }

    private void addMessage(String sender, String receiver, int type, String message) {
        System.out.println(sender+" --"+type+"--> "+receiver+" : "+message);
        sdController.sequenceDiagram.addMessage(
               sdController.sequenceDiagram.participantWithName(sender),
               sdController.sequenceDiagram.participantWithName(receiver),
               UMLMessage.UMLMessageType.values()[type],
               message);
        sdController.sequenceDiagram.checkConsistence(sdController.classDiagram);
        sdController.sdView.drawSD(sdController.sequenceDiagram);
        sdController.sdView.setVisible(true);
    }
}
