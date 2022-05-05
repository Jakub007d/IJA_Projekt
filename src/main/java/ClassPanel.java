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
    private java.util.List<UMLRelationship> relationShipList = new ArrayList<UMLRelationship>();
    private ClassDiagram classDiagram;
    ClassPanel(ClassDiagram classDiagram)
    {
        this.classDiagram = classDiagram;
        this.addMouseListener(this);
        this.setBackground(Color.gray);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.relationShipList=classDiagram.getRelationShipList();
    }
    public void updateRelList(java.util.List<UMLRelationship> relationShipList)
    {
        this.relationShipList = relationShipList;
    }
    private Point cardinalityTextPos(int x,int y,Point firstPoint)
    {
        Point secondPoint = new Point(x, y);
        int newX= this.textPosition((int)firstPoint.getX(),(int)secondPoint.getX());
        int newY= this.textPosition((int)firstPoint.getY(),(int)secondPoint.getY());
        return new Point(newX,newY);
    }
    private int textPosition(int firstValue,int secondValue)
    {
        int retVal;
        retVal=firstValue+secondValue;
        float tx = (float)retVal/2;
        retVal=(int)tx;
        return retVal;
    }
    private void drawTriangle(Graphics g, int x,int y)
    {
        int bottomY = y+10;
        int bottomX1 = x-10;
        int bottomX2 = x+10;
        int [] xs = {x,bottomX1,bottomX2};
        int [] ys = {y,bottomY,bottomY};
        g.drawPolygon(xs,ys,3);
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
                    System.out.println(leftClass.getName());

                    if(leftClass.getName()==null)
                        continue;
                    if(rightClass.getName()==null)
                        continue;
                    if(panel.getName()==null)
                        continue;
                    if (((PanelForClass)panel).getClassName().equals(leftClass.getName())) {
                        x1 = panel.getX();
                        y1 = panel.getY();
                        x1 = x1 + panel.getWidth();
                        y1 = y1 + panel.getHeight()/2;
                        for (Component tmp : conponents) {

                            if(tmp.getName() != null)
                            {
                                if (((PanelForClass) tmp).getClassName().equals(rightClass.getName())) {
                                    rel.printRelation();
                                    x2 = tmp.getX();
                                    y2 = tmp.getY();
                                    Point textPosition = new Point(this.textPosition(x1,x2),this.textPosition(y1,y2));
                                    Point lCardinalityPosition = this.cardinalityTextPos(x1,y1,textPosition);
                                    Point rCardinalityPosition = this.cardinalityTextPos(x2,y2,textPosition);
                                    Graphics2D g2D = (Graphics2D) g;
                                    g2D.drawString(rel.getRelationName(),(int)textPosition.getX(),(int)textPosition.getY());
                                    g2D.drawString(rel.getLeftCardinality(),(int)lCardinalityPosition.getX(),(int)lCardinalityPosition.getY());
                                    g2D.drawString(rel.getRightCardinality(),(int)rCardinalityPosition.getX(),(int)rCardinalityPosition.getY());
                                    g2D.drawLine(x1, y1, x2, y2);
                                }
                            }

                        }
                    }
                }
                if (panel.getName() != null)
                {
                    UMLClass childClass = ((PanelForClass) panel).getClassReference();
                    UMLClass parentClass = childClass.getParentClass();
                    x1 = panel.getX()+ panel.getWidth()/2;
                    y1 = panel.getY();
                    if (parentClass != null) {
                        for (Component secondClass : conponents) {
                            if (secondClass.getName() != null) {
                                if (((PanelForClass) secondClass).getClassName().equals(parentClass.getName())) {
                                    x2 = secondClass.getX() +secondClass.getWidth()/2;
                                    y2 = secondClass.getY() + secondClass.getHeight();
                                    Graphics2D g2D = (Graphics2D) g;
                                    g2D.drawLine(x1, y1, x2, y2);
                                    drawTriangle(g,x2,y2);
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
