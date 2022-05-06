package main.java;

import javax.swing.*;
import java.awt.event.*;

public class SDPopupMenu extends JPopupMenu implements ActionListener {

    JMenuItem addParticipant;
    JMenuItem addSomething;

    JPopupMenu SDPopup;
    JPanel SDViewMainPanel;

    //https://www.zentut.com/java-swing/how-to-create-popup-menu-in-java-swing/
    public SDPopupMenu(JFrame frame, JPanel SDViewMainPanel) {
        this.SDPopup = new JPopupMenu();
        this.SDViewMainPanel = SDViewMainPanel;
        //JLabel tmp = new JLabel();

        // Pridanie noveho ucastnika
        addParticipant = new JMenuItem("Add participant");
        addParticipant.addActionListener(this);
        SDPopup.add(addParticipant);

        // New File menu item
        addSomething = new JMenuItem("sth sth ...");
        addSomething.addActionListener(this);
        SDPopup.add(addSomething);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addParticipant) {
            System.out.println("*beep boop* you are trying to add participant");
//            PanelForClass classPanel = new PanelForClass(new UMLClass("test:test"));
//            SDViewMainPanel.add((JPanel)classPanel);
//            repaint();
        }
        if (e.getSource()==addSomething) {
            System.out.println("*beep boop* you are trying to add something");
        }

    }
}
