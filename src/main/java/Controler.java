import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controler implements ActionListener {
    private Model model = new Model();
    private View view;

    public Controler(View view){
        this.model.parseDiagram();
        this.view=view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.button)
        {
            UMLClass referenceClass = model.getClassFromModel();
            JLabel className = new JLabel(referenceClass.getName());
            view.testClassPanel.add(className);
            for(UMLAttribute attr : referenceClass.getAttributes())
            {
                JLabel atrb = new JLabel(attr.getType()+" "+attr.getName());
                view.testClassPanel.add(atrb);
            }

            view.setVisible(true);
        }
    }
}
