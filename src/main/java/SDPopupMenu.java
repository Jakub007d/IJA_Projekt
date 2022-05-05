package main.java;

import javax.swing.*;
import java.awt.event.*;

public class SDPopupMenu extends JFrame {

    JPopupMenu SDPopup;

    //https://www.zentut.com/java-swing/how-to-create-popup-menu-in-java-swing/
    public SDPopupMenu(JFrame frame, JPanel SDViewMainPanel) {
        this.SDPopup = new JPopupMenu();
        JLabel tmp = new JLabel();

        // Pridanie noveho ucastnika
        JMenuItem menuItem = new JMenuItem("Add participant");
        menuItem.setMnemonic(KeyEvent.VK_P);

        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "New Project clicked!");
                //TODO: zeby tam miesto toho ^ dialogu vykresilo nejaky stvorcek

                PanelForClass classPanel = new PanelForClass(new UMLClass("test:test"));
                SDViewMainPanel.add((JPanel)classPanel);

                UMLParticipant myTestParticipant = new UMLParticipant("test:Main");
                SDGuiParticipant myGUITestParticipant = new SDGuiParticipant(100,10,myTestParticipant);
                SDViewMainPanel.add(myGUITestParticipant);
                SDViewMainPanel.setVisible(true);
                SDViewMainPanel.repaint();
                myGUITestParticipant.repaint();
            }
        });
        SDPopup.add(menuItem);
        // New File menu item
        menuItem = new JMenuItem("New File...");
        menuItem.setMnemonic(KeyEvent.VK_F);
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(frame, "New File clicked!");

                Object[] possibilities = {"ham", "spam", "yam"};
                String s = (String)JOptionPane.showInputDialog(
                        frame,
                        "Complete the sentence:\n"
                                + "\"Green eggs and...\"",
                        "Customized Dialog",
                        JOptionPane.PLAIN_MESSAGE,null,
                        possibilities,
                        "ham");

                //If a string was returned, say so.
                if ((s != null) && (s.length() > 0)) {
                    tmp.setText("Green eggs and... " + s + "!");
                } else {
                    //If you're here, the return value was null/empty.
                    tmp.setText("Come on, finish the sentence!");
                }
                SDViewMainPanel.add(tmp);

                String m = JOptionPane.showInputDialog("Anyone there?");
                System.out.println(m);
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
