import javax.swing.*;

public class View extends JFrame{
    public JButton button;
    Controler controler;
    public JPanel mainPanel;
    public JPanel classPanel;


    View()
    {
        controler = new Controler(this);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        button = new JButton("Load Data");
        button.addActionListener(controler);
        JLabel nadpis = new JLabel("Testovacie vykreslenie načítaných tried");
        nadpis.setAlignmentX(CENTER_ALIGNMENT);
        button.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(nadpis);
        mainPanel.add(button);
        mainPanel.add(classPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.add(mainPanel);
        this.setVisible(true);
    }

}
