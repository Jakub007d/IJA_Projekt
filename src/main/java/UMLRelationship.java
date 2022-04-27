package main.java;

/**
 * Trieda reprezentuje vzťah, ktorý je medzi dvoma triedami a má príslušné kardinality.
 * Rozširuje triedu UMLCLassifier.
 * Kardinalita je reprezentovaná ako String, zúčastnené triedy su reprezentované triedou UMLClass.
 * Left a right zodpovedá pozícii v textovom vstupe.
 *
 * @author xdrobe01
 */
public class UMLRelationship extends UMLClassifier {
    private String lCardinality;
    private String rCardinality;
    private UMLClass leftClass;
    private UMLClass rightClass;

    /**
     * Konštruktor pre inštanciu UML vzťahu.
     *
     * @param name Názov vzťahu.
     * @param lCardinality Kardinalita ľavej triedy.
     * @param rCardinality Kardinalita pravej triedy.
     * @param leftClass Ľavá trieda.
     * @param rightClass Pravá trieda.
     */
    public UMLRelationship(String name,String lCardinality, String rCardinality,UMLClass leftClass,UMLClass rightClass)
    {
        super(name);
        this.lCardinality=lCardinality;
        this.leftClass=leftClass;
        this.rCardinality=rCardinality;
        this.rightClass=rightClass;
    }

    /**
     * Sprístupní informáciu o L kardinalite.
     *
     * @return Kardinalita vľavo.
     */
    public String getLeftCardinality()
    {
        return this.lCardinality;
    }

    /**
     * Sprístupní informáciu o R kardinalite.
     *
     * @return Kardinalita vpravo.
     */
    public String getRightCardinality()
    {
        return this.rCardinality;
    }

    /**
     * Sprístupní informáciu o triede vpravo.
     *
     * @return Trieda vpravo.
     */
    public UMLClass getRightClass()
    {
        return this.rightClass;
    }

    /**
     * Sprístupní informáciu o triede vľavo.
     *
     * @return Trieda vľavo.
     */
    public UMLClass getLeftClass()
    {
        return this.leftClass;
    }

    /**
     * Testovací výpis vzťahu.
     */
    public void printRelation()
    {
        System.out.println(getLeftClass().getName()+" "+getLeftCardinality()+" "+getName()+" "+getRightCardinality()+" "+getRightClass().getName());
    }

}
