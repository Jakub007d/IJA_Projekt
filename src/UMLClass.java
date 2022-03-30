

import java.util.ArrayList;
import java.util.Collections;

public class UMLClass extends UMLClassifier {
    private boolean isAbstract = false;
    private java.util.List<UMLAttribute> attributeList = new ArrayList<UMLAttribute>();

    public UMLClass(String name)
    {
        super(name);
    }

    public boolean isAbstract()
    {
        return isAbstract;
    }

    public void setAbstract(boolean isAbstract)
    {
        this.isAbstract=isAbstract;
    }

    // Vloží atribut do modelu UML třídy.
    // Atribut se vloží na konec seznamu (poslední položka).
    // Pokud již třída obsahuje atribut stejného jména, nedělá nic.
    // Úspěch akce (pokud se podařilo vložit, vrací true, jinak false).
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

    public int getAttrPosition(UMLAttribute attr)
    {
        int listLen = this.attributeList.size();
        for (int i = 0 ; i < listLen ; i++) {
            if(this.attributeList.get(i).equals(attr)) return i;
        }
        return -1;
    }

    // Přesune pozici atributu na nově zadanou.
    // Pozice se indexuje od hodnoty 0.
    // Pokud třída daný atribut neobsahuje, nic neprovádí a vrací -1.
    // Při přesunu na pozici pos se všechny stávající položky (atributy)
    // od pozice pos (včetně) posunou o jednu pozici doprava.
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

    public java.util.List<UMLAttribute> getAttributes()
    {
        return Collections.unmodifiableList(this.attributeList);
    }
}
