package main.java;

import javax.swing.*;
import java.awt.event.*;

public class SDPopupMenu extends JFrame {

    JPopupMenu SDPopup;

    //https://www.zentut.com/java-swing/how-to-create-popup-menu-in-java-swing/
    public SDPopupMenu(JFrame frame, JPanel SDViewMainPanel){
        this.SDPopup = new JPopupMenu();

        // New project menu item
        JMenuItem menuItem = new JMenuItem("New Project...");
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "New Project");
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "New Project clicked!");
                //TODO: zeby tam miesto toho ^ dialogu vykresilo nejaky stvorcek
                PanelForClass classPanel = new PanelForClass("test:Test");
                SDViewMainPanel.add((JPanel)classPanel);
                SDViewMainPanel.setVisible(true);
            }
        });
        SDPopup.add(menuItem);
        // New File menu item
        menuItem = new JMenuItem("New File...");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "New File clicked!");
            }
        });
        SDPopup.add(menuItem);

        frame.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                showPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showPopup(e);
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    SDPopup.show(e.getComponent(),
                            e.getX(), e.getY());
                }
            }
        });

    }
}
