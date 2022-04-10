import javax.swing.*;
import java.awt.*;

public class PanelForClass extends JPanel {
    PanelForClass(String name)
    {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.yellow);
        JLabel className = new JLabel(name);
        className.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.add(className);
    }
    public void addAttribute(String name, String type)
    {
        this.add(new JLabel(type+" "+name));
    }
}
