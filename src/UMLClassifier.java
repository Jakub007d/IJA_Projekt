package ija.homework1.uml;

import org.junit.platform.engine.support.hierarchical.ThrowableCollector;

public class UMLClassifier extends Element {
    private boolean isUserDefined;
    public UMLClassifier(String name, boolean isUserDefined)
    {
        super(name);
        this.isUserDefined=isUserDefined;
    }
    public UMLClassifier(String name)
    {
        super(name);
        this.isUserDefined=true;
    }
    public static UMLClassifier forName(String name)
    {
        UMLClassifier cls = new UMLClassifier(name,false);
        return cls;
    }
    public boolean isUserDefined()
    {
        return this.isUserDefined;
    }
    public String toString()
    {
        String nazev = super.getName();
        String userDefined = String.valueOf(this.isUserDefined);
        return nazev+"("+userDefined+")";
    }
}
