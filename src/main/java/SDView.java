package main.java;

import javax.swing.*;

public class SDView extends JFrame {

    public JPanel SDViewMainPanel;
    //static JPopupMenu SDPopupMenu;

    SDView(String name) {
        //JFrame frame = new JFrame();
        this.setTitle("Sequence Diagram: "+name);
        this.setSize(420,420);
        SDViewMainPanel = new JPanel();
        this.add(SDViewMainPanel);
        new SDPopupMenu(this,SDViewMainPanel);
        new SDGuiMenuBar(this);
        this.setVisible(true);

    }
}
