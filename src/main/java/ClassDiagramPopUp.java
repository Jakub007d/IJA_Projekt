package main.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Trieda dedí z JPopupMenu a zaistuje popup okno pre danú triedu v gui.
 * Trieda implementuje ActionListener.
 */
public class ClassDiagramPopUp extends JPopupMenu implements ActionListener {
    JButton addAttribute = new JButton("Add Attribute");
    JButton addOperation = new JButton("Add Operation");
    JButton deleteClass = new JButton("Delete Class");
    JTextField textField = new JTextField();
    PanelForClass panelForClass;


    /**
     * Konštruktor popup okna triedy v gui
     * @param label názov popup okna
     * @param panelForClass panel triedy
     */
    public ClassDiagramPopUp(String label, PanelForClass panelForClass)
    {
        super(label);
        this.panelForClass=panelForClass;
        textField.setColumns(7);
        this.addAttribute.addActionListener(this);
        this.addOperation.addActionListener(this);
        this.deleteClass.addActionListener(this);
        this.add(textField);
        this.add(this.addAttribute);
        this.add(this.addOperation);
        this.add(this.deleteClass);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addAttribute)
        {
            this.panelForClass.addAttributeTextField(this.textField.getText());
        }

        if(e.getSource() == addOperation)
        {
            this.panelForClass.addOperationTextField(this.textField.getText());
        }
        if(e.getSource() == deleteClass)
        {
            this.panelForClass.deleteClass();
        }
    }
}
