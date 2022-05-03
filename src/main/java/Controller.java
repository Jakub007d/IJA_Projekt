package main.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 * Trieda implementuje Controller, ktorý sa stará o dáta načítané cez parser a stará sa o zmeny v zobrazení
 *
 * @author xdrobe01
 */
public class Controller implements ActionListener {
    private ClassDiagram classDiagram;
    private View view;
    /**
     * Konštruktor triedy Controller.
     *
     * @param view Referencia na triedu View
     */
    public Controller(View view){
        this.classDiagram = new JsonParser().parse();
        this.view=view;
        this.view.classPanel = new ClassPanel(classDiagram.getRelationShipList());
    }

    /**
     * Metóda, ktorá aktualizuje View po stlačení tlčítka.
     *
     * @param e Event stlacenia tlačítka
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.button)
        {
            view.classPanel.removeAll();

            for(int pos = 0 ;pos<= classDiagram.numberOfClasses() - 1; pos++)
            {
                System.out.println(pos);
                UMLClass tmpCLassReference = this.classDiagram.returnClassAtPos(pos);
                PanelForClass classPanel = new PanelForClass(tmpCLassReference.getName());
                for(UMLAttribute attribute : tmpCLassReference.getAttributes())
                {
                    classPanel.addAttribute(attribute.toString(),attribute.getAccessModifier());
                }
                for(UMLAttribute operation : tmpCLassReference.getOperations())
                {
                    System.out.println("ERES HIERE");
                    classPanel.addAttribute(operation.toString(),operation.getAccessModifier());
                }
                view.classPanel.add((JPanel)classPanel);
                view.classPanel.add(Box.createRigidArea(new Dimension(100,0)));

            }
            view.setVisible(true);
        }
        if (e.getSource() == view.saveButton)
        {
            for (Component component : view.classPanel.getComponents())
            {
                if (component.getName() != null)
                {
                    UMLClass classReference = (UMLClass) this.classDiagram.findClassifier(component.getName());
                    Container container = (Container) component;
                    boolean firstDone = false;
                    for (Component innerComponent : container.getComponents())
                    {
                        JTextField toSaveAttr = (JTextField) innerComponent;
                        if(!firstDone)
                        {
                            component.setName(toSaveAttr.getText()); // TODO
                            classReference.deleteAttributes(); // TODO
                            classReference.rename(toSaveAttr.getText());
                            firstDone=true;
                        }
                        else
                        {
                            try
                            {
                                String[] readyToAttr = toSaveAttr.getText().split(" ");
                                String accesibility = Character.toString(readyToAttr[0].charAt(0));
                                UMLAttribute toAddAttribute = new UMLAttribute(readyToAttr[0].substring(1).replace(":",""),new UMLClassifier(readyToAttr[1]),accesibility);
                                classReference.addAttribute(toAddAttribute);
                                toSaveAttr.setBackground(Color.white);
                            }
                            catch (Exception exception)
                            {
                                toSaveAttr.setText("Zle zadaný atribút");
                                toSaveAttr.setBackground(Color.red);
                            }

                        }
                    }
                    ClassPanel refClassPanel = (ClassPanel) this.view.classPanel;
                    refClassPanel.updateRelList(classDiagram.getRelationShipList());
                    this.view.classPanel = (JPanel) refClassPanel;
                }
            }
        }
    }
}
