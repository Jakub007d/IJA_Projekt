package gui.ija_projekt;

/**
 * Třída reprezentuje atribut, který má své jméno a typ.
 * Je odvozena (rozšiřuje) od třídy gui.ija_projekt.Element.
 * Typ atributu je reprezentován třidou gui.ija_projekt.UMLClassifier.
 * Lze použít jako atribut UML třídy nebo argument operace.
 */
public class UMLAttribute extends Element {
    private UMLClassifier type;

    /**
     * Vytvoří instanci atributu.
     * @param name Název atributu.
     * @param type Typ atributu.
     */
    public UMLAttribute(String name, UMLClassifier type)
    {
        super(name);
        this.type=type;
    }

    /**
     * Poskytuje informaci o typu atributu.
     * @return Typ atributu.
     */
    public UMLClassifier getType()
    {
        return this.type;
    }

    /**
     * Vrací řetězec reprezentující stav atributu v podobě "nazev:typ".
     * @return Řetězec reprezentující atribut.
     */
    public String toString()
    {
        String nazev = super.getName();
        String typ = String.valueOf(this.type);
        return nazev+":"+typ;
    }
}
