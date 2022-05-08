package main.java;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Trieda reprezentuje model triedy z jazyka UML.
 * Rozšiřuje triedu UMLClassifier.
 * Obsahuje zoznam atribútov a operácií (metód). Trieda môže byť abstraktná.
 *
 * @author xstrak38
 */
public class UMLClass extends UMLClassifier {
    private UMLClass parentClass = null;
    private boolean isAbstract = false;
    private java.util.List<UMLAttribute> attributeList = new ArrayList<UMLAttribute>();
    private java.util.List<UMLOperation> operationList = new ArrayList<UMLOperation>();
    /**
     * Vytvorí inštanciu reprezentujúsu model triedy z jazyka UML. Trieda nie je abstraktná.
     *
     * @param name Názov triedy (klasifikátora).
     */
    public UMLClass(String name)
    {
        super(name);
    }

    public UMLClass(String name,UMLClass parentClass)
    {
        super(name);
        this.parentClass = parentClass;

    }

    public UMLClass getParentClass() {
        return this.parentClass;
    }
    public boolean isChild()
    {
        return this.parentClass != null;
    }
    /**
     * Test, či objekt reprezentuje model abstraktnej triedy.
     *
     * @return Ak je trieda abstraktná, vráti true. Inak false.
     */
    public boolean isAbstract()
    {
        return isAbstract;
    }

    /**
     * Zmení informáciu objektu, či reprezentuje abstraktnú triedu.
     *
     * @param isAbstract Či sa jedná o abstraktnú triedu alebo nie.
     */
    public void setAbstract(boolean isAbstract)
    {
        this.isAbstract=isAbstract;
    }

    /**
     * Vloží atribút do modelu UML triedy.
     * Atribút sa vloží na koniec zoznamu (posledná položka).
     * Ak už trieda obsahuje atribút rovnakého mena, nerobí nič.
     *
     * @param attr Vkládaný atribút.
     * @return Úspech akcie (ak se podarilo vložiť, vráti true, inak false).
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
    public boolean addOperation(UMLOperation operation)
    {
        if(this.operationList.contains(operation))
        {
            return false;
        } else {
            this.operationList.add(operation);
            return true;
        }
    }
    public UMLAttribute containAttribute(String name)
    {
        for (UMLAttribute attribute : this.attributeList)
        {
            if(attribute.getName().equals(name))
            {
                return attribute;
            }
        }
        return null;
    }
    public UMLOperation containOperation(String name)
    {
        for (UMLOperation operation : this.operationList)
        {
            if(operation.getName().equals(name))
            {
                return operation;
            }
        }
        return null;
    }
    public UMLConstructor containConstruktor(String name)
    {
        for (UMLOperation operation : this.operationList)
        {
            if(operation.getName().equals(name))
            {
                return (UMLConstructor) operation;
            }
        }
        return null;
    }
    public void removeAttribute(String name)
    {
        this.attributeList.remove(this.containAttribute(name));
    }

    public void removeOperation(String name)
    {
        this.operationList.remove(this.containOperation(name));
    }
    /**
     * Vracia pozíciu v zozname atribútov.
     * Pozícia sa indexuje od hodnoty 0. Ak trieda daný atribút neobsahuje, vráti -1.
     *
     * @param attr Hľadaný atribút.
     * @return Pozícia atribútu.
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
     * Presunie pozíciu atribútu na novo zadanú.
     * Pozícia sa indexuje od hodnoty 0.
     * Ak trieda daný atribút neobsahuje, nerobí nič a vráti -1.
     * Pri presune na pozíciu pos sa všetky položky (atribúty)
     * od pozície pos (vrátane) posunú o jednu pozíciu doparava.
     *
     * @param attr Presúvaný atribút.
     * @param pos Nová pozícia.
     * @return Úspech operácie.
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
     * Vracia nemodifikovateľný zoznam atribútov.
     * Je možné využiť pre zobrazenie atribútov triedy.
     *
     * @return Nemodifikovateľný zoznam atribútov.
     */
    public java.util.List<UMLAttribute> getAttributes()
    {
        return Collections.unmodifiableList(this.attributeList);
    }
    public java.util.List<UMLOperation> getOperations()
    {
        return Collections.unmodifiableList(this.operationList);
    }
    public void deleteAttributes()
    {
        this.attributeList.clear();
    }
    public void deleteOperations()
    {
        this.operationList.clear();
    }

}
