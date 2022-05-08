package main.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SDGuiParticipantPopup extends JPopupMenu implements ActionListener {
    JButton addParticipant = new JButton("Add");
    JTextField textField = new JTextField();
    SequenceDiagram sequenceDiagram;

    public SDGuiParticipantPopup(SDController sdController){
        this.sequenceDiagram = sdController.sequenceDiagram;
        this.addParticipant.addActionListener(this);
        textField.setColumns(7);
        this.add(textField);
        this.add(addParticipant);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addParticipant) {

        }
    }
}
