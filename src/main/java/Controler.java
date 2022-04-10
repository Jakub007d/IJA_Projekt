import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controler implements ActionListener {
    private ClassDiagram classDiagram;
    private View view;

    public Controler(View view){
        this.classDiagram = new Parser().parse();
        this.view=view;
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
            for(int pos = 0 ;pos<= classDiagram.numberOfClasses() - 1; pos++)
            {
                UMLClass tmpCLassReference = classDiagram.returnClassAtPos(pos);
                PanelForClass classPanel = new PanelForClass(tmpCLassReference.getName());
                for(UMLAttribute attribute : tmpCLassReference.getAttributes())
                {
                    classPanel.addAttribute(attribute.getName(),attribute.getType().getName());
                }
                view.testClassPanel.add((JPanel)classPanel);
                JLabel spacer = new JLabel("     ");
                view.testClassPanel.add(spacer);

            }
            view.setVisible(true);
        }
    }
}
