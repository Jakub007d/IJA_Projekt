import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame{
    public JButton button;
    Controler controler;
    public JPanel testClassPanel;


    View()
    {
        controler = new Controler(this);
        testClassPanel= new JPanel();
        testClassPanel.setBounds(61,11,81,140);
        testClassPanel.setLayout(new BoxLayout(testClassPanel, BoxLayout.Y_AXIS));
        button = new JButton("Testik");
        button.addActionListener(controler);
        testClassPanel.add(button);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.add(testClassPanel);
        this.setVisible(true);
    }

}
