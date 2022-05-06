package main.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassDiagramPopUp extends JPopupMenu implements ActionListener {
    JButton addattribute =new JButton("Add Attribute");
    JButton addOperation =new JButton("Add Operation");
    JTextField textField = new JTextField();
    PanelForClass panelForClass;
    public ClassDiagramPopUp(String label, PanelForClass panelForClass)
    {
        super(label);
        this.panelForClass=panelForClass;
        textField.setColumns(7);
        this.addattribute.addActionListener(this);
        this.addOperation.addActionListener(this);
        this.add(textField);
        this.add(this.addattribute);
        this.add(this.addOperation);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addattribute)
        {
            System.out.println("LOL");
            this.panelForClass.addAttributeTextField(this.textField.getText());
        }

        if(e.getSource() == addOperation)
        {
            System.out.println("LOL");
            this.panelForClass.addOperationTextField(this.textField.getText());
        }
    }
}