package ija.homework1.uml;

public class UMLAttribute extends Element {
    private UMLClassifier type;
    public UMLAttribute(String name, UMLClassifier type)
    {
        super(name);
        this.type=type;
    }

    public UMLClassifier getType()
    {
        return this.type;
    }
    public String toString()
    {
        String nazev = super.getName();
        String typ = String.valueOf(this.type);
        return nazev+":"+typ;
    }
}
