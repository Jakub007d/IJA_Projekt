/**
 * Trieda reprezentujúca účastníka sekvenčného diagramu.
 * Rozširuje triedu UMLClassifier.
 */
public class UMLParticipant extends UMLClassifier {
    private boolean isActive = false;
    private boolean isPresentInCD; /* inicializovat na flase? */

    /**
     * Vytrvorí inštanciu reprezentujúcu účastníka sekvenčného diagramu.
     * @param name Názov účastníka.
     */
    public UMLParticipant(String name)
    {
        super(name);
    }

    /**
     * Zmení informáciu o prítomnosti ekvivalentného objektu v diagrame tried.
     * @param presence Prítomnosť v diagrame tried.
     */
    public void setPresence(boolean presence)
    {
        this.isPresentInCD = presence;
    }

    /**
     * Test, či je účastník prítomny v diagrame tried.
     * @return Ak je účastník prítomný v diagrame tried, vracia true. Inak false.
     */
    public boolean isPresentInCD()
    {
        return isPresentInCD;
    }

    /**
     * Zmení informáciu o tom, či je účastník aktívny
     * @param active Informácia o tom, či je účastník aktívny. (TODO neviem ako inak to pomenovat)
     */
    public void setActive(boolean active)
    {
        this.isActive = active;
    }

    /**
     * Test, či je účastník aktívny.
     * @return Ak je účastník aktívny, vracia true. Inak false.
     */
    public boolean isActive()
    {
        return isActive;
    }
}
