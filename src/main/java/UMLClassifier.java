/**
 * Trieda reprezentuje klasifikátor v diagrame.
 * Odvodené triedy reprezentujú konkrétne
 * podoby klasifikátora (trieda, rozhranie, atribút, apod.)
 *
 * @author xstrak38
 */
public class UMLClassifier extends Element {
    private boolean isUserDefined;

    /**
     * Vytvorí inštanciu treidy Classifier.
     *
     * @param name Názov klasifikátora.
     * @param isUserDefined Užívateľsky definovaný (súčasťou diagramu).
     */
    public UMLClassifier(String name, boolean isUserDefined)
    {
        super(name);
        this.isUserDefined=isUserDefined;
    }

    /**
     * Vytvorí inštanciu treidy Classifier.
     * Inštancia je užívateľsky definovaná (súčasťou diagramu).
     *
     * @param name Názov klasifikátora.
     */
    public UMLClassifier(String name)
    {
        super(name);
        this.isUserDefined=true;
    }

    /**
     * Továrna metóda pre vytvorenie inštancie triedy Classifier pre zadané meno.
     * Inštancia reprezentuje klasifikátor, ktorý nie je v diagrame modelovaný.
     *
     * @param name Názov klasifikátora.
     * @return Vytvorený klasifikátor.
     */
    public static UMLClassifier forName(String name)
    {
        return new UMLClassifier(name, false);
    }

    /**
     * Zisťuje, či objekt reprezentuje klasifikátor,
     * ktorý je modelovaný užívateľom v diagrame.
     *
     * @return Ak je klasifikátor užívateľsky definovaný (je priamo súčasťou diagramu), vráti true, inak false.
     */
    public boolean isUserDefined()
    {
        return this.isUserDefined;
    }

    /**
     * Vracia reťazec reprezentujúci klasifikátor
     * v podobe "nazev(userDefined)", kde userDefined je true alebo false.
     *
     * @return Reťazec reprezentujúci klasifikátor.
     */
    public String toString()
    {
        String nazev = super.getName();
        String userDefined = String.valueOf(this.isUserDefined);
        return nazev+"("+userDefined+")";
    }
}
