import javax.swing.*;
import java.awt.*;

/**
 *
 *
 * @author xdrobe01
 */
public class PanelForClass extends JPanel {
    private String nameOfPanel;
    PanelForClass(String name)
    {
        this.nameOfPanel=name;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.yellow);
        JLabel className = new JLabel(name);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.add(className);
    }

    /**
     *
     *
     * @param name
     * @param type
     */
    public void addAttribute(String name, String type)
    {
        this.add(new JLabel(type+" "+name));
    }

    /**
     * Sprístupní informáciu o názve Panela.
     *
     * @return Názov panela.
     */
    public String getName()
    {
        return this.nameOfPanel;
    }
}
