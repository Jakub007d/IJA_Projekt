package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;

/**
 * Trieda dedí z triedy JPanel a implementuje špecialny panel používaný pre vykreslenie triedy
 *
 * @author xdrobe01
 */
public class PanelForClass extends JPanel implements MouseListener {
    private UMLClass classReference;
    private ClassDiagram classDiagram;
    private String nameOfPanel;
    private JPanel operationsPanel = new JPanel();
    private JPanel attributesPanel = new JPanel();
    int width;
    int height;
    Point classCorner;
    Point prevPoint;
    PanelForClass(UMLClass umlClass, ClassDiagram classDiagram)
    {
        this.classDiagram = classDiagram;
        this.addMouseListener(this);
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

    /**
     * Metóda vráti referenciu triedy
     * @return referencia na triedu
     */
    public UMLClass getClassReference() {
        return classReference;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3)
        {
            ClassDiagramPopUp pop = new ClassDiagramPopUp("AddAttr",this);
            pop.show(this,e.getX(),e.getY());
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
     * Metóda pridá do panela attribúty
     *
     * @param name Názov atribútu
     * @param type Typ atribútu
     * @param attrName Názov atribútu
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

    /**
     * Metóda pridá do panela operácie
     * @param name názov operácie
     * @param type návratový typ
     * @param operationName názov operácie
     */
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
     * Metóda vráti názov triedy
     * @return
     */
    public String getClassName()
    {
        String returnValue = classReference.getName();
        return Objects.requireNonNullElse(returnValue, "");
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

    /**
     * Pridá attribút vytvorený uživatelom v popup okne
     * @param toBeAdded pridávaný attribút
     */
    public void addAttributeTextField(String toBeAdded)
    {
        if(toBeAdded != null && !toBeAdded.equals("")) {
            String[] TokensToName = toBeAdded.split(" ");
            String name = TokensToName[0].substring(1);
            name = name.replace(":","");
            JTextField attr = new JTextField(toBeAdded);
            attr.setName(name);
            this.attributesPanel.add(attr);
            this.revalidate();
            this.repaint();
        }
    }
    /**
     * Pridá operáciu vytvorenú uživatelom v popup okne
     * @param toBeAdded pridávaná operácia
     */
    public void addOperationTextField(String toBeAdded)
    {
        if(toBeAdded != null && !toBeAdded.equals("")) {
            String[] TokensToName = toBeAdded.split(" ");
            String name = TokensToName[0].substring(1);
            JTextField operation = new JTextField(toBeAdded);
            name = name.replace(":","");
            operation.setName(name);
            this.operationsPanel.add(operation);
            this.revalidate();
            this.repaint();
        }
    }

    /**
     * Vymaže triedu z class diagramu
     */
    public void deleteClass()
    {
        classDiagram.deleteClass(classReference.getName());
        this.setVisible(false);
    }
}
