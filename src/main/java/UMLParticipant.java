package main.java;

/**
 * Trieda reprezentujúca účastníka sekvenčného diagramu.
 * Rozširuje triedu UMLClassifier.
 *
 * @author xstrak38
 */
public class UMLParticipant extends UMLClassifier {
    private String originalName;
    private boolean isActive = false; /* aktivace objektu */
    private String objectName; /* nazov objektu */
    private String className;  /* nazov triedy  */
    private boolean isPresentInCD; /* inicializovat na flase? */
    private UMLClass umlClass;

    public String getOriginalName() {
        return this.originalName;
    }

    /**
     * Vytrvorí inštanciu reprezentujúcu účastníka sekvenčného diagramu.
     *
     * @param name Názov účastníka.
     */
    public UMLParticipant(String name)
    {
        super(name);
        System.err.println(originalName);
        try {
            String[] tmp = name.split(":", 2);
            this.objectName = tmp[0];
            this.className = tmp[1];
            System.out.println(this.objectName);
        } catch (Exception e) {
            this.className = name;
            System.err.println(e);
        }
    }
    public UMLParticipant(String objectName, String className)
    {
        super(objectName+":"+className);
        this.objectName = objectName;
        this.className = className;
    }

    /**
     * Zmení informáciu o prítomnosti ekvivalentného objektu v diagrame tried.
     *
     * @param presence Prítomnosť v diagrame tried.
     */
    public void setPresence(boolean presence)
    {
        this.isPresentInCD = presence;
    }

    /**
     * Test, či je účastník prítomny v diagrame tried.
     *
     * @return Ak je účastník prítomný v diagrame tried, vracia true. Inak false.
     */
    public boolean getPresence() /*renamed from isPresentInCD()*/
    {
        return isPresentInCD;
    }

    /**
     * Zmení informáciu o tom, či je účastník aktívny
     *
     * @param active Informácia o tom, či je účastník aktívny.
     */
    public void setActive(boolean active)
    {
        this.isActive = active;
    }

    /**
     * Test, či je účastník aktívny.
     *
     * @return Ak je účastník aktívny, vracia true. Inak false.
     */
    public boolean isActive()
    {
        return isActive;
    }

    /**
     * Sprístupní informáciu o názve triedy daného účastníka.
     *
     * @return Názov triedy (className).
     */
    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setName(String name) {
        this.rename(name);
        if (name.contains(":")) {
            String[] tmp = name.split(":", 2);
            this.objectName = tmp[0];
            this.className = tmp[1];
        } else {
            this.objectName = "";
            this.className = name;
        }
    }

    public void setUmlClass(UMLClass umlClass) {
        this.originalName = super.getName();
        this.umlClass = umlClass;
        this.className = umlClass.getName();
    }

    public void actualization()
    {
        if (this.umlClass != null)
            this.className = this.umlClass.getName();
            super.rename(this.className);
            System.out.println("Premenuvavam na "+super.getName());
        if (this.objectName != null || !this.objectName.equals(""))
        {
            super.rename(this.objectName+":"+this.className);
        }
        else
        {
            super.rename(":"+this.className);
        }
        System.err.println(this.className);
    }
}
