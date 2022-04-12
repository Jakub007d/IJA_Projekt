/**
 * Trieda reprezentuje pomenovaný element (thing),
 * ktorý môže byť súčasťou akejkoľvek časti diagrame.
 *
 * @author xstrak38
 */
public class Element {

    private String name;

    /**
     * Vytvorí inštanciu so zadaným názvom.
     *
     * @param name Názov elementu.
     */
    public Element(String name)
    {
        this.name = name;
    }

    /**
     * Vráti názov elementu.
     *
     * @return Názov elementu.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Premenuje element.
     *
     * @param newName Nový názov elementu.
     */
    public void rename(String newName)
    {
        this.name = newName;
    }
}
