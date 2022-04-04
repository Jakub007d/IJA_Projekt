package gui.ija_projekt;

/**
 * Třída reprezentuje klasifikátor v diagramu.
 * Odvozené třídy reprezentují konkrétní
 * podoby klasifikátoru (třída, rozhraní, atribut, apod.)
 */
public class UMLClassifier extends Element {
    private boolean isUserDefined;

    /**
     * Vytvoří instanci třídy Classifier.
     * @param name Název klasifikátoru.
     * @param isUserDefined Uživatelsky definován (součástí diagramu).
     */
    public UMLClassifier(String name, boolean isUserDefined)
    {
        super(name);
        this.isUserDefined=isUserDefined;
    }

    /**
     * Vytvoří instanci třídy Classifier. Instance je uživatelsky definována (je součástí diagramu).
     * @param name Název klasifikátoru.
     */
    public UMLClassifier(String name)
    {
        super(name);
        this.isUserDefined=true;
    }

    /**
     * Tovární metoda pro vytvoření instance třídy Classifier pro zadané jméno.
     * Instance reprezentuje klasifikátor, který není v diagramu modelován.
     * @param name Název klasifikátoru.
     * @return Vytvořený klasifikátor.
     */
    public static UMLClassifier forName(String name)
    {
        return new UMLClassifier(name, false);
    }

    /**
     * Zjišťuje, zda objekt reprezentuje klasifikátor, který je modelován uživatelem v diagramu nebo ne.
     * @return Pokud je klasifikátor uživatelsky definován (je přímo součástí diagramu), vrací true. Jinak false.
     */
    public boolean isUserDefined()
    {
        return this.isUserDefined;
    }

    /**
     * Vrací řetězec reprezentující klasifikátor
     * v podobě "nazev(userDefined)", kde userDefined je true nebo false.
     * @return Řetězec reprezentující klasifikátor.
     */
    public String toString()
    {
        String nazev = super.getName();
        String userDefined = String.valueOf(this.isUserDefined);
        return nazev+"("+userDefined+")";
    }
}
