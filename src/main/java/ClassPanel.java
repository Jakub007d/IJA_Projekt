package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

/**
 * Trieda dedí z triedy JPanel a stara sa o jednotlive vykreslenie tried a relacie medzi nimi.
 *
 * @author xdrobe01
 */
public class ClassPanel extends JPanel implements MouseListener {
    private java.util.List<UMLClassifier> relationShipList = new ArrayList<UMLClassifier>();
    ClassPanel(java.util.List<UMLClassifier> relationShipList)
    {
        this.addMouseListener(this);
        this.setBackground(Color.gray);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.relationShipList=relationShipList;
    }

    /**
     * Metóda paint sa stará o jednotlivé vykreslenie relácii medzi triedami
     * @param g Graficke rozhranie
     */

    public void paint(Graphics g)
    {
        super.paintComponent(g);
        super.paintChildren(g);
        Component[] conponents = this.getComponents();
        int x1=0;
        int y1=0;
        int x2 =0;
        int y2 = 0;
        if(conponents.length != 0)
        {
            for (Component panel : conponents) {
                for (UMLClassifier relation : this.relationShipList) {
                    UMLRelationship rel = (UMLRelationship) relation;
                    UMLClass leftClass = rel.getLeftClass();
                    UMLClass rightClass = rel.getRightClass();
                    if(leftClass.getName()==null)
                        continue;
                    if(rightClass.getName()==null)
                        continue;
                    if(panel.getName()==null)
                        continue;
                    if (panel.getName().equals(leftClass.getName())) {
                        x1 = panel.getX();
                        y1 = panel.getY();
                        x1 = x1 + panel.getWidth();
                        y1 = y1 + panel.getHeight()/2;
                        for (Component tmp : conponents) {
                            if(tmp.getName() != null)
                            {
                                if (tmp.getName().equals(rightClass.getName())) {
                                    x2 = tmp.getX();
                                    y2 = tmp.getY();
                                    Graphics2D g2D = (Graphics2D) g;
                                    g2D.drawString(rel.getRelationName(),x1,y1);
                                    g2D.drawLine(x1, y1, x2, y2);
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        repaint();

    }


}
