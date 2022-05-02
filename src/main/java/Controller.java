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
        this.classDiagram = new Parser().parse();
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
                UMLClass tmpCLassReference = classDiagram.returnClassAtPos(pos);
                PanelForClass classPanel = new PanelForClass(tmpCLassReference.getName());
                for(UMLAttribute attribute : tmpCLassReference.getAttributes())
                {
                    classPanel.addAttribute(attribute.getName(),attribute.getType().getName());
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
                    System.out.println(component.getName());
                    UMLClass classReference = (UMLClass) this.classDiagram.findClassifier(component.getName());
                    Container container = (Container) component;
                    boolean firstDone = false;
                    for (Component innerComponent : container.getComponents())
                    {
                        JTextField toSaveAttr = (JTextField) innerComponent;
                        if(!firstDone)
                        {
                            component.setName(toSaveAttr.getText());
                            classReference.deleteAttributes();
                            classReference.rename(toSaveAttr.getText());
                            firstDone=true;
                        }
                        else
                        {
                            try
                            {
                                String[] readyToAttr = toSaveAttr.getText().split(" ");
                                UMLAttribute toAddAttribute = new UMLAttribute(readyToAttr[1],new UMLClassifier(readyToAttr[0]));
                                classReference.addAttribute(toAddAttribute);
                            }
                            catch (Exception exception)
                            {

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
