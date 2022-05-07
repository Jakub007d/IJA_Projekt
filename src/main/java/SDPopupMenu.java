package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SDPopupMenu extends JPopupMenu implements ActionListener {

    JMenuItem addParticipant;
    JMenuItem addSomething;

    JPopupMenu SDPopup;

    JPanel SDViewMainPanel;

    SequenceDiagram sequenceDiagram;

    //https://www.zentut.com/java-swing/how-to-create-popup-menu-in-java-swing/
    public SDPopupMenu(JFrame frame, JPanel SDViewMainPanel, SequenceDiagram sequenceDiagram) {
        this.SDPopup = new JPopupMenu();
        this.SDViewMainPanel = SDViewMainPanel;
        this.sequenceDiagram = sequenceDiagram;
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
        if (e.getSource() == addParticipant) {
            System.out.println("*beep boop* you are trying to add participant");
//            UMLParticipant participant = new UMLParticipant("moja:Mamka");
//            SDGuiParticipant p = new SDGuiParticipant(12,12,participant);
//            SDViewMainPanel.add(p);
//            SDViewMainPanel.setVisible(true);
//            p.setVisible(true);
//            repaint();

            for (int pos = 0 ; pos < sequenceDiagram.numberOfParticipants() ; pos++) {
                System.out.println(pos);
                UMLParticipant tmpParticipant = this.sequenceDiagram.participantAtPosition(pos);
                SDGuiParticipant participantPanel = new SDGuiParticipant(10+100*pos,10,tmpParticipant);
                SDViewMainPanel.add((JPanel) participantPanel);
                SDViewMainPanel.add(Box.createRigidArea(new Dimension(100,0)));
            }
            SDViewMainPanel.setVisible(true);
        }
        if (e.getSource()==addSomething) {
            System.out.println("*beep boop* you are trying to add something");
        }

    }
}
