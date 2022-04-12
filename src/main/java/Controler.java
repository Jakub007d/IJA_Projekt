import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;
import java.awt.Graphics;

public class Controler implements ActionListener {
    private ClassDiagram classDiagram;
    private View view;
    public Controler(View view){
        this.classDiagram = new Parser().parse();
        this.view=view;
        view.classPanel = new ClassPanel(classDiagram.getRelationShipList());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.button)
        {
            /*
            UMLClass referenceClass = classDiagram.getClassFromModel();
            JLabel className = new JLabel(referenceClass.getName());
            view.testClassPanel.add(className);
            for(UMLAttribute attr : referenceClass.getAttributes())
            {
                JLabel atrb = new JLabel(attr.getType()+" "+attr.getName());
                view.testClassPanel.add(atrb);
            }

            view.setVisible(true);

             */
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
    }
}
