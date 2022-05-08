package main.java;

import javax.swing.*;

/**
 * Triesa View dedí z JFrame a zabezpečuje zobrazenie okna.
 *
 * @author xdrobe01
 */
public class View extends JFrame {
    public JButton button;
    public JButton saveButton;
    Controller controller;
    public JPanel mainPanel;
    public JPanel classPanel;
    public JButton loadSDButton;
    /**
     * Konštruktor triedy View
     */
    View()
    {
        controller = new Controller(this);
        mainPanel = new JPanel();
        button = new JButton("Load Class Data");
        saveButton = new JButton("Save Data");
        loadSDButton = new JButton("Load Sequence Data");
        button.addActionListener(controller);
        saveButton.addActionListener(controller);
        loadSDButton.addActionListener(controller);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel topMenu = new JPanel();
        topMenu.setLayout(new BoxLayout(topMenu, BoxLayout.X_AXIS));
        JLabel nadpis = new JLabel("Main Class Diagram View");
        nadpis.setAlignmentX(CENTER_ALIGNMENT);
        button.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(nadpis);
        topMenu.add(button);
        topMenu.add(loadSDButton);
        topMenu.add(saveButton);
        mainPanel.add(topMenu);
        mainPanel.add(classPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,700);
        this.add(mainPanel);
        this.setVisible(true);
    }

}
