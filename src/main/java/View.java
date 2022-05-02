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
        saveButton = new JButton("Save Data");
        saveButton.addActionListener(controller);
        JLabel nadpis = new JLabel("Testovacie vykreslenie načítaných tried");
        nadpis.setAlignmentX(CENTER_ALIGNMENT);
        button.setAlignmentX(CENTER_ALIGNMENT);
        saveButton.setAlignmentX(CENTER_ALIGNMENT);
        mainPanel.add(nadpis);
        mainPanel.add(button);
        mainPanel.add(saveButton);
        mainPanel.add(classPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,700);
        this.add(mainPanel);
        this.setVisible(true);
    }

}
