import java.util.ArrayList;
import java.util.List;

/**
 * Trieda reprezentuje diagram tried.
 * Je odvodená od triedy Element (má názov).
 * Obsahuje zoznam tried (inštancie triedy UMLClass) príp. klasifikátorov
 * pre užívateľsky nedefinované typy (inštancie triedy UMLClassifier).
 */
public class ClassDiagram extends Element{
    private java.util.List<UMLClassifier> classList = new ArrayList<UMLClassifier>();
    private java.util.List<UMLClassifier> relationShipList = new ArrayList<UMLClassifier>();

    /**
     * Konštruktor pre vytvorenie inštancie diagramu. Každý diagram má svoj názov.
     *
     * @param name Názov diagramu.
     */
    public ClassDiagram(String name)
    {
        super(name);
    }

    /**
     * Vytvorí inštanciu UML triedy a vloží ju do diagramu.
     * Ak v diagrame už existuje trieda s rovnakým názvom, nerobí nič.
     *
     * @param name Názov vytváranej triedy.
     * @return Objekt (inštancia) reprezentujúca triedu. Ak trieda s daným názvom už existuje, vráti null.
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
     * Vytvorí inštanciu UML vzťahu a vloží do diagramu.
     * Left a right zodpovedá pozícii v textovom vstupe.
     *
     * @param name Názov vzťahu.
     * @param lCardinality Kardinalita ľavej triedy.
     * @param rCardinality Kardinalita pravej triedy.
     * @param leftClass Ľavá trieda.
     * @param rightClass Pravá trieda.
     */
    public void createRelation(String name,String lCardinality, String rCardinality, UMLClass leftClass, UMLClass rightClass)
    {
        this.relationShipList.add(new UMLRelationship(name,lCardinality,rCardinality,leftClass,rightClass));
    }

    /**
     * Vyhledá v diagramu klasifikátor podle názvu.
     * Pokud neexistuje, vytvoří instanci třídy Classifier
     * reprezentující klasifikátor, který není v diagramu zachycen
     * (viz UMLClassifier.forName(java.lang.String)); využito
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

    /**
     * Vyhľadá v diagrame klasifikátor podľa názvu.
     * @param name Názov klasifikaátora.
     * @return True, ak diagram obsahuje daný klasifikátor, inak false.
     */
    public boolean checkClassifierPresence(String name)
    {   /* mozeme pouzit pri rieseni nekonzistencii */
        for (UMLClassifier cls : this.classList) {
            if (cls.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void debugUMLRelationsVypis()
    {
        for (UMLClassifier rel : this.relationShipList)
        {
            UMLRelationship rsReference = (UMLRelationship) rel;
            rsReference.printRelation();
        }
    }

    public UMLClass returnClassAtPos(int pos)
    {
        if(pos-1 > numberOfClasses())
        {
            System.err.println("Out of bounds in CLassList");
            return null;
        }
        else
        {
            return (UMLClass) this.classList.get(pos);
        }
    }

    public List<UMLClassifier> getRelationShipList() {
        return relationShipList;
    }

    public int numberOfClasses()
    {
        return this.classList.size();
    }

}
