import javax.swing.*;

public class View extends JFrame{
    public JButton button;
    Controler controler;
    public JPanel mainPanel;


    View()
    {
        controler = new Controler(this);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        button = new JButton("Testik");
        button.addActionListener(controler);
        mainPanel.add(button);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.add(mainPanel);
        this.setVisible(true);
    }

}
