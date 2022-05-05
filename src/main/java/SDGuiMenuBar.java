package main.java;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SDGuiMenuBar extends JPanel implements ActionListener {

    JMenuBar menuBar;

    JMenu fileMenu;
    JMenu helpMenu;

    JMenuItem loadItem;
    JMenuItem saveItem;
    JMenuItem helpItem;

    SDGuiMenuBar(JFrame view){
        /* create new menu bar */
        menuBar = new JMenuBar();

        /* create menus */
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        /* create menu items */
        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        helpItem = new JMenuItem("Show Help");

        /* add action listeners */
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        helpItem.addActionListener(this);

        /* add menu items to file menu */
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);

        helpMenu.add(helpItem); // https://stackoverflow.com/questions/9862165/jmenu-actionlistener
                                // actionListener na helpItem nefunguje, ale da sa pouzit menuListener

        /* add menus to the menu bar */
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        /* add the menu bar to the view */
        view.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loadItem) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println("*beep boop* you loaded a file "+file);
            } else System.out.println("you didn't load a file :/");
        }
        if (e.getSource()==saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                System.out.println("*beep boop* you saved a file "+file);
            } else System.out.println("*beep boop* you didn't save a file");
        }
        if (e.getSource()==helpItem) {
            System.out.println("*beep boop* you need help :(");
        }
    }
}
