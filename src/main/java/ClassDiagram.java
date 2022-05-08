package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Trieda reprezentuje diagram tried.
 * Je odvodená od triedy Element (má názov).
 * Obsahuje zoznam tried (inštancie triedy UMLClass) príp. klasifikátorov
 * pre užívateľsky nedefinované typy (inštancie triedy UMLClassifier).
 *
 * @author xstrak38, xdrobe01
 */
public class ClassDiagram extends Element{
    private java.util.List<UMLClass> classList = new ArrayList<UMLClass>();
    private java.util.List<UMLRelationship> relationShipList = new ArrayList<UMLRelationship>();

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
    public void updateInheritance(UMLClass editedClass)
    {
        for(UMLClass umlClass : this.classList)
        {
            if(umlClass.getParentClass() != null) {
                if (umlClass.getParentClass().getName().equals(editedClass.getName())) {
                    umlClass.setParentClass(editedClass);
                }
            }
        }
    }


    /**
     * Pridá do class diagramu triedu umlClass
     * @param umlClass pridávaná trieda
     */
    public void addClass (UMLClass umlClass)
    {
        this.classList.add(umlClass);
    }
    public UMLClass getClassByName(String name)
    {
        for (UMLClass umlCLass : this.classList)
        {
            if (umlCLass.getName() == name)
                return umlCLass;
        }
        return null;
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
     * Vyhľadá v diagrame klasifikátor podľa názvu.
     * Ak neexistuje, vytvorí inštanciu triedy Classifier
     * reprezentujúcu klasifikátor, ktorý nie je v diagrame zachytený
     * (viz UMLClassifier.forName(java.lang.String)); využité
     * napr. pre modelovanie typu premennej, ktorá v diagrame nie je.
     * Táto inštancia je zaradená do štruktúr diagramu,
     * tzn. že pri dalšom pokuse o vyhľadanie sa použije táto už vytvorená inštancia.
     *
     * @param name Názov klasifikátora.
     * @return Nájdený, príp. vytvorený klasifikátor.
     */
    /*
    public UMLClassifier classifierForName(String name)
    {
        UMLClassifier cls = findClassifier(name);
        if(cls == null) {
            cls = UMLClassifier.forName(name);
            this.classList.add(cls);
        }
        return cls;
    }
*/
    /**
     * Vyhľadá v diagrame klasifikátor podľa názvu.
     *
     * @param name Názov klasifikátora.
     * @return Nájdený klasifikátor. Ak v diagrame neexistuje klasifikátor daného mena, vráti null.
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
     *
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


    /**
     * Vyhľadá v diagrame triedu podľa pozície v zozname.
     *
     * @param pos pozícia v zozname.
     * @return Trieda na danej pozícii. Ak pozícia mimo zoznamu, vráti null.
     */
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

    /**
     * Poskytuje zoznam vzťahov v diagrame.
     *
     * @return Zoznam vzťahov v diagrame.
     */
    public List<UMLRelationship> getRelationShipList()
    {
        return relationShipList;
    }

    /**
     * Zisťuje počet tried v diagrame.
     *
     * @return počet tried v diagrame.
     */
    public int numberOfClasses()
    {
        return this.classList.size();
    }

    /**
     * Aktualizácia Relácií
     * @param umlClass
     */
    public void UpdateRelationShip(UMLClass umlClass)
    {
        for (UMLRelationship relation : this.relationShipList)
        {
            if (relation.getLeftClass().getName().equals(umlClass.getName()))
            {
                relation.setLeftClass(umlClass);
            }
            if (relation.getRightClass().getName().equals(umlClass.getName()))
            {
                relation.setRightClass(umlClass);
            }

        }
    }

    /**
     * Vymaže z class diagramu triedu s názvom name
     * @param name názov triedy
     */
    public void deleteClass(String name)
    {
        this.classList.removeIf(umlClass -> umlClass.getName().equals(name));
    }
}
