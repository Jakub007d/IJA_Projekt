package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewClassPopUp extends JPopupMenu implements ActionListener {
    JButton addClass =new JButton("Add Class");
    JTextField textField = new JTextField();
    ClassPanel classPanel;
    JTextField textFieldFrom = new JTextField();
    JTextField textFieldTo = new JTextField();
    JCheckBox inheritance = new JCheckBox("Inheritance");
    JCheckBox composition = new JCheckBox("Agregation");
    JCheckBox association = new JCheckBox("Association");
    JCheckBox directedAsociation = new JCheckBox("DirAssociation");
    JPanel checkBox = new JPanel();
    JButton addRelation = new JButton("Add Relation");
    String relationType= "";
    public NewClassPopUp(ClassPanel classPanel)
    {
        this.classPanel=classPanel;
        this.textField.setColumns(7);
        this.addClass.addActionListener(this);
        this.add(textField);
        this.add(this.addClass);
        this.composition.addActionListener(this);
        this.directedAsociation.addActionListener(this);
        this.association.addActionListener(this);
        this.addRelation.addActionListener(this);
        this.inheritance.addActionListener(this);
        this.add(new JLabel("Relation"));
        this.add(new JLabel("From"));
        this.add(this.textFieldFrom);
        this.add(new JLabel("To"));
        this.add(this.textFieldTo);
        this.checkBox.setLayout(new BoxLayout(this.checkBox, BoxLayout.Y_AXIS));
        this.checkBox.add(this.composition);
        this.checkBox.add(this.association);
        this.checkBox.add(this.directedAsociation);
        this.checkBox.add(this.inheritance);
        this.add(this.checkBox);
        this.add(this.addRelation);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addClass)
        {
            this.classPanel.addClassWithName(this.textField.getText());
        }
        if(e.getSource() == this.inheritance)
        {
            this.relationType="Inheritance";
            this.composition.setSelected(false);
            this.association.setSelected(false);
            this.directedAsociation.setSelected(false);
        }
        if(e.getSource() == this.association)
        {
            this.relationType="Composition";
            this.composition.setSelected(false);
            this.inheritance.setSelected(false);
            this.directedAsociation.setSelected(false);
        }
        if(e.getSource() == this.composition)
        {
            this.relationType="Aggregation";
            this.association.setSelected(false);
            this.inheritance.setSelected(false);
            this.directedAsociation.setSelected(false);
        }
        if(e.getSource() == this.directedAsociation)
        {
            this.relationType="DirectedAsociation";
            this.composition.setSelected(false);
            this.inheritance.setSelected(false);
            this.association.setSelected(false);
        }
        if(e.getSource() == addRelation)
        {
            int err = this.classPanel.addRelation(this.relationType,this.textFieldFrom.getText(),this.textFieldTo.getText());
            switch(err)
            {
                case 0:
                    this.textFieldFrom.setBackground(Color.white);
                    this.textFieldTo.setBackground(Color.white);
                    break;
                case 1:
                    this.textFieldFrom.setBackground(Color.red);
                    break;
                case 2:
                    this.textFieldTo.setBackground(Color.red);
                    break;
                case 3:
                    this.textFieldFrom.setBackground(Color.red);
                    this.textFieldTo.setBackground(Color.red);
                    break;
            }
        }

    }
    public String getNameClass()
    {
        return this.textField.getText();
    }
}
