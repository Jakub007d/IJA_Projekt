import javax.swing.*;

/**
 * Triesa View dedí z JFrame a zabezpečuje zobrazenie okna.
 *
 * @author xdrobe01
 */
public class View extends JFrame {
    public JButton button;
    Controller controller;
    public JPanel mainPanel;
    public JPanel classPanel;

    /**
     * Konštruktor triedy View
     */
    View()
    {
        controller = new Controller(this);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        button = new JButton("Load Data");
        button.addActionListener(controller);
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
