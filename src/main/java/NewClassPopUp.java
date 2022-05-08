package main.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class NewClassPopUp extends JPopupMenu implements ActionListener {
    JButton addClass =new JButton("Add Class");
    JTextField textField = new JTextField();
    ClassPanel classPanel;
    public NewClassPopUp(ClassPanel classPanel)
    {
        this.classPanel=classPanel;
        this.textField.setColumns(7);
        this.addClass.addActionListener(this);
        this.add(textField);
        this.add(this.addClass);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addClass)
        {
            this.classPanel.addClassWithName(this.textField.getText());
        }

    }
    public String getNameClass()
    {
        return this.textField.getText();
    }
}
