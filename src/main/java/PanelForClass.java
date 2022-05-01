package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Trieda dedí z triedy JPanel a implementuje špecialny panel používaný pre vykreslenie triedy
 *
 * @author xdrobe01
 */
public class PanelForClass extends JPanel {
    private String nameOfPanel;
    int width;
    int height;
    Point classCorner;
    Point prevPoint;
    PanelForClass(String name)
    {
        classCorner = new Point(this.getX(),this.getY());
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
        this.nameOfPanel=name;
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(Color.yellow);
        JLabel className = new JLabel(name);
        className.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.add(className);
        this.width=this.getWidth();
        this.height=this.getHeight();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        super.paintChildren(g);
        this.setLocation((int)this.classCorner.getX(),(int)this.classCorner.getY());
    }

    private class ClickListener extends MouseAdapter{
        public void mousePressed(MouseEvent e)
        {
            prevPoint = e.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter{
        public void mouseDragged(MouseEvent e)
        {
            Point currentPt = e.getPoint();

            classCorner.translate(
                    (int)(currentPt.getX()),
                    (int)(currentPt.getY())
            );
            prevPoint = currentPt;
            repaint();
        }

    }
    /**
     * Metóda pridá do panela attribúty a operácie
     *
     * @param name Názov atribútu
     * @param type Typ atribútu
     */
    public void addAttribute(String name, String type)
    {
        this.add(new JLabel(type+" "+name));
        this.width=this.getWidth();
        this.height=this.getHeight();
    }

    /**
     * Sprístupní informáciu o názve Panela.
     *
     * @return Názov panela.
     */
    public String getName()
    {
        return this.nameOfPanel;
    }
}
