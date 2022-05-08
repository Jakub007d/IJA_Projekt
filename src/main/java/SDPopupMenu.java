package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SDPopupMenu extends JPopupMenu implements ActionListener {
    JMenuItem addParticipant;
    JMenuItem addSomething;

    JPopupMenu SDPopup;

    JPanel SDViewMainPanel;
    JFrame SDView;

    SDController sdController;
    SequenceDiagram sequenceDiagram;

    //https://www.zentut.com/java-swing/how-to-create-popup-menu-in-java-swing/
    public SDPopupMenu(JFrame frame, JPanel SDViewMainPanel, SDController sdController) {
        this.SDPopup = new JPopupMenu();
        this.SDViewMainPanel = SDViewMainPanel;
        this.sdController = sdController;
        this.sequenceDiagram = sdController.sequenceDiagram;
        System.out.println(this.sequenceDiagram);
        this.SDView = frame;
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

            new SDGuiParticipantDialog(sdController);
        }
        if (e.getSource()==addSomething) {
            System.out.println("*beep boop* you are trying to add something (participant cnt = "+sdController.sequenceDiagram.numberOfParticipants()+")");
//            UMLParticipant newParticipant = sequenceDiagram.createParticipant("nova:Mamka");
//            if(newParticipant != null) {
//                SDGuiParticipant participant = new SDGuiParticipant(newParticipant);
//                participant.setPreferredSize(new Dimension(100,100));
//                participant.setBorder(BorderFactory.createLineBorder(Color.red));
//                SDViewMainPanel.add((JPanel) participant);
//            }
            SDView.setVisible(true);

            //SDGuiParticipantPopup pop = new SDGuiParticipantPopup(sequenceDiagram);
            //pop.show(this,10,10);
        }

    }
}
