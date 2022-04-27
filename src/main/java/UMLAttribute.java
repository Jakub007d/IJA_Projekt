package main.java;

/**
 * Trieda reprezentuje atribút, ktorý má svoje meno a typ.
 * Je odvodený od triedy Element.
 * Typ atribútu je reprezentovaný triedou UMLClassifier.
 * Je možné použiť ako atribút UML triedy alebo argument operácie.
 *
 * @author xstrak38
 */
public class UMLAttribute extends Element {
    private UMLClassifier type;

    /**
     * Vytvorí inštanciu atribútu.
     *
     * @param name Názov atribútu.
     * @param type Typ atribútu.
     */
    public UMLAttribute(String name, UMLClassifier type)
    {
        super(name);
        this.type=type;
    }

    /**
     * Poskytuje informáciu o type atribútu.
     *
     * @return Typ atribútu.
     */
    public UMLClassifier getType()
    {
        return this.type;
    }

    /**
     * Vráti reťazec reprezentujúci stav atribútu v podobe "nazov:typ".
     *
     * @return Reťazaec reprezentujúci atribút.
     */
    public String toString()
    {
        String nazev = super.getName();
        String typ = String.valueOf(this.type);
        return nazev+":"+typ;
    }
}
