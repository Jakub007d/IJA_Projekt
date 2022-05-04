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
    private UMLClass classReference;
    private String nameOfPanel;
    private JPanel operationsPanel = new JPanel();
    private JPanel attributesPanel = new JPanel();
    int width;
    int height;
    Point classCorner;
    Point prevPoint;
    PanelForClass(UMLClass umlClass)
    {
        this.classReference = umlClass;
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        JTextField className = new JTextField(this.classReference.getName());

        classCorner = new Point(this.getX(),this.getY());
        operationsPanel.setLayout(new BoxLayout(operationsPanel,BoxLayout.Y_AXIS));
        operationsPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        attributesPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        attributesPanel.setLayout(new BoxLayout(attributesPanel,BoxLayout.Y_AXIS));
        className.setBorder(BorderFactory.createLineBorder(Color.black, 3));

        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
        this.nameOfPanel=this.classReference.getName();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        this.setBorder(BorderFactory.createLineBorder(Color.black, 4));
        this.add(className);
        this.add(this.attributesPanel);
        this.add(this.operationsPanel);
        this.width=this.getWidth();
        this.height=this.getHeight();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        super.paintChildren(g);
        this.setLocation((int)this.classCorner.getX(),(int)this.classCorner.getY());
    }

    public UMLClass getClassReference() {
        return classReference;
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
    public void addAttribute(String name, String type,String attrName)
    {
        JTextField attr = new JTextField(type+name);
        attr.setName(attrName);
        this.attributesPanel.add(attr);
        this.width=this.getWidth();
        this.height=this.getHeight();
        this.revalidate();
        this.repaint();
    }

    public void addOperation(String name, String type, String operationName)
    {
        JTextField op = new JTextField(type+name);
        op.setName(operationName);
        this.operationsPanel.add(op);
        this.width=this.getWidth();
        this.height=this.getHeight();
        this.revalidate();
        this.repaint();
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
