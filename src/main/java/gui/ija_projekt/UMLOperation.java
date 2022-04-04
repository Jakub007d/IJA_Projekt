package gui.ija_projekt;/*
 * IJA 2021/22: Aplikace pro zobrazení a editaci diagramů tříd a sekvenčních diagramů
 * Autor:
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Třída reprezentuje operaci, která má své jméno, návratový typ a seznam argumentů.
 * Je odvozena (rozšiřuje) od třídy gui.ija_projekt.UMLAttribute, od které přejímá název a návratový typ.
 * Argument je reprezentován třídou gui.ija_projekt.UMLAttribute.
 * Lze použít jako součást UML klasifikátoru třída nebo rozhraní.
 *
 * @author xz
 */
public class UMLOperation extends UMLAttribute {
    private java.util.List<UMLAttribute> argumentList = new ArrayList<UMLAttribute>();

    /**
     * Konstruktor pro vytvoření operace s daným názvem a návratovým typem.
     * @param name Název operace.
     * @param type Návratový typ operace.
     */
    public UMLOperation(String name, UMLClassifier type)
    {
        super(name, type);
    }

    /**
     * Tovární metoda pro vytvoření instance operace.
     * @param name Název operace.
     * @param type Návratový typ operace.
     * @param args Seznam argumentů operace.
     * @return Objekt reprezentující operaci v diagramu UML.
     */
    public static UMLOperation create(String name, UMLClassifier type, UMLAttribute... args)
    {
        UMLOperation operation = new UMLOperation(name,type);
        operation.argumentList = List.of(args);
        return operation;
    }

    /**
     * Přidá nový argument do seznamu argumentů.
     * Argument se vloží na konec seznamu.
     * Pokud v seznamu již existuje argument stejného názvu, operaci neprovede.
     * @param arg Vkládaný argument.
     * @return Úspěch operace - true, pokud se podařilo vložit, jinak false.
     */
    public boolean addArgument(UMLAttribute arg)
    {
        // TODO Warning:(49, 17) Condition 'attr.getName().equals(arg)' is always 'false'
        // TODO Warning:(49, 32) 'equals' between objects of inconvertible types 'String' and 'gui.ija_projekt.UMLAttribute'
        for (UMLAttribute attr : this.argumentList) {
            if (attr.getName().equals(arg)) {
                return false;
            }
        }
        this.argumentList.add(arg);
        return true;
    }

    /**
     * Vrací nemodifikovatelný seznam argumentů. Lze využít pro zobrazení.
     * @return Nemodifikovatelný seznam argumentů.
     */
    public java.util.List<UMLAttribute> getArguments()
    {
        return Collections.unmodifiableList(this.argumentList);
    }
}
