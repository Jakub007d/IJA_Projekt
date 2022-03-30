package ija.homework1.uml;

import java.util.ArrayList;

public class ClassDiagram extends Element{
    private java.util.List<UMLClassifier> classList = new ArrayList<UMLClassifier>();

    public ClassDiagram(String name)
    {
        super(name);
    }

    public UMLClass createClass(String name)
    {
        for (UMLClassifier cls : this.classList) {
            if (cls.getName().equals(name)) {
                return null;
            }
        }
        UMLClass newClass = new UMLClass(name);
        this.classList.add(newClass);
        return newClass;
    }

    public UMLClassifier classifierForName(String name)
    {
        UMLClassifier cls = findClassifier(name);
        if(cls == null) {
            cls = UMLClassifier.forName(name);
            this.classList.add(cls);
        }
        return cls;
    }

    public UMLClassifier findClassifier(String name)
    {
        for (UMLClassifier cls : this.classList) {
            if (cls.getName().equals(name)) {
                return cls;
            }
        }
        return null;
    }
}
