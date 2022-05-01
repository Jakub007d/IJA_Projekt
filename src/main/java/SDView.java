package main.java;

import javax.swing.*;

public class SDView {

    public JPanel SDViewMainPanel;
    static JPopupMenu SDPopupMenu;

    SDView(String name) {
        JFrame frame = new JFrame();
        frame.setTitle("Sequence Diagram: "+name);
        frame.setSize(420,420);
        frame.setVisible(true);
        SDViewMainPanel = new JPanel();
        frame.add(SDViewMainPanel);
        new SDPopupMenu(frame,SDViewMainPanel);

    }
}
