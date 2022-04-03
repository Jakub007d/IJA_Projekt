import java.util.ArrayList;
import java.util.Collections;

/**
 * Třída (její instance) reprezentuje model třídy z jazyka UML.
 * Rozšiřuje třídu UMLClassifier. Obsahuje seznam atributů a operací (metod). Třída může být abstraktní.
 */
public class UMLClass extends UMLClassifier {
    private boolean isAbstract = false;
    private java.util.List<UMLAttribute> attributeList = new ArrayList<UMLAttribute>();

    /**
     * Vytvoří instanci reprezentující model třídy z jazyka UML. Třída není abstraktní.
     * @param name Název třídy (klasifikátoru).
     */
    public UMLClass(String name)
    {
        super(name);
    }

    /**
     * Test, zda objekt reprezentuje model abstraktní třídy.
     * @return Pokud je třída abstraktní, vrací true. Jinak vrací false.
     */
    public boolean isAbstract()
    {
        return isAbstract;
    }

    /**
     * Změní informaci objektu, zda reprezentuje abstraktní třídu.
     * @param isAbstract Zda se jedná o abstraktní třídu nebo ne.
     */
    public void setAbstract(boolean isAbstract)
    {
        this.isAbstract=isAbstract;
    }

    /**
     * Vloží atribut do modelu UML třídy.
     * Atribut se vloží na konec seznamu (poslední položka).
     * Pokud již třída obsahuje atribut stejného jména, nedělá nic.
     * @param attr Vkládaný atribut.
     * @return Úspěch akce (pokud se podařilo vložit, vrací true, jinak false).
     */
    public boolean addAttribute(UMLAttribute attr)
    {
        if(this.attributeList.contains(attr))
        {
            return false;
        } else {
            this.attributeList.add(attr);
            return true;
        }
    }

    /**
     * Vrací pozici atributu v seznamu atributů. Pozice se indexuje od hodnoty 0. Pokud třída daný atribut neobsahuje, vrací -1.
     * @param attr Hledaný atribut.
     * @return Pozice atributu.
     */
    public int getAttrPosition(UMLAttribute attr)
    {
        int listLen = this.attributeList.size();
        for (int i = 0 ; i < listLen ; i++) {
            if(this.attributeList.get(i).equals(attr)) return i;
        }
        return -1;
    }

    /**
     * Přesune pozici atributu na nově zadanou.
     * Pozice se indexuje od hodnoty 0.
     * Pokud třída daný atribut neobsahuje, nic neprovádí a vrací -1.
     * Při přesunu na pozici pos se všechny stávající položky (atributy)
     * od pozice pos (včetně) posunou o jednu pozici doprava.
     *
     * @param attr Přesunovaný atribut.
     * @param pos Nová pozice.
     * @return Úspech operace.
     */
    public int moveAttrAtPosition(UMLAttribute attr, int pos)
    {
        if (this.attributeList.contains(attr)) {
            this.attributeList.remove(attr);
            this.attributeList.add(pos,attr);
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Vrací nemodifikovatelný seznam atributů. Lze využít pro zobrazení atributů třídy.
     * @return Nemodifikovatelný seznam atributů.
     */
    public java.util.List<UMLAttribute> getAttributes()
    {
        return Collections.unmodifiableList(this.attributeList);
    }
}
