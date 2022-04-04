package gui.ija_projekt;

import java.util.ArrayList;

/**
 * Třída reprezentuje diagram tříd.
 * Je odvozen od třídy gui.ija_projekt.Element (má název).
 * Obsahuje seznam tříd (instance třídy gui.ija_projekt.UMLClass) příp. klasifikátorů
 * pro uživatelsky nedefinované typy (instance třídy gui.ija_projekt.UMLClassifier).
 */
public class ClassDiagram extends Element {
    private java.util.List<UMLClassifier> classList = new ArrayList<UMLClassifier>();

    /**
     * Konstruktor pro vytvoření instance diagramu. Každý diagram má svůj název.
     * @param name Název diagramu.
     */
    public ClassDiagram(String name)
    {
        super(name);
    }

    /**
     * Vytvoří instanci UML třídy a vloží ji do diagramu. Pokud v diagramu již existuje třída stejného názvu, nedělá nic.
     * @param name Název vytvářené třídy.
     * @return Objekt (instance) reprezentující třídu. Pokud třída s daným názvem již existuje, vrací null.
     */
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

    /**
     * Vyhledá v diagramu klasifikátor podle názvu.
     * Pokud neexistuje, vytvoří instanci třídy Classifier
     * reprezentující klasifikátor, který není v diagramu zachycen
     * (viz gui.ija_projekt.UMLClassifier.forName(java.lang.String)); využito
     * např. pro modelování typu proměnné, který v diagramu není.
     * Tato instance je zařazena do struktur diagramu, tzn. že při dalším pokusu o vyhledání se použije tato již vytvořená instance
     * @param name Název klasifikátoru.
     * @return Nalezený, příp. vytvořený, klasifikátor.
     */
    public UMLClassifier classifierForName(String name)
    {
        UMLClassifier cls = findClassifier(name);
        if(cls == null) {
            cls = UMLClassifier.forName(name);
            this.classList.add(cls);
        }
        return cls;
    }

    /**
     * Vyhledá v diagramu klasifikátor podle názvu.
     * @param name Název klasifikátoru.
     * @return Nalezený klasifikátor. Pokud v diagramu neexistuje klasifikátor daného jména, vrací null.
     */
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
