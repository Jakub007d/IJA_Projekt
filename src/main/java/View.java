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
    public JButton checkButton;
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
        checkButton = new JButton("Check Consistence");
        button.addActionListener(controller);
        saveButton.addActionListener(controller);
        loadSDButton.addActionListener(controller);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        JPanel topMenu = new JPanel();
        topMenu.setLayout(new BoxLayout(topMenu, BoxLayout.X_AXIS));
        JLabel nadpis = new JLabel("Testovacie vykreslenie načítaných tried");
        nadpis.setAlignmentX(CENTER_ALIGNMENT);
        button.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(nadpis);
        topMenu.add(button);
        topMenu.add(loadSDButton);
        topMenu.add(saveButton);
        topMenu.add(checkButton);
        mainPanel.add(topMenu);
        mainPanel.add(classPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,700);
        this.add(mainPanel);
        this.setVisible(true);
    }

}
