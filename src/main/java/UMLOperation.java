package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Trieda reprezentuje operáciu, ktorá má svoje meno, návratový typ a zoznam argumentov.
 * Je odvodená (rozširuje) od triedy UMLAttribute, od ktorej preberá názov a návratový typ.
 * Agrument je reprezentovaný triedou UMLAttribute.
 * Je možné použiť ako súčasť UML klasifikátora trieda alebo rozhranie.
 *
 * @author xstrak38
 */
public class UMLOperation extends UMLAttribute {
    private java.util.List<UMLAttribute> argumentList = new ArrayList<UMLAttribute>();

    /**
     * Konštruktor pre vytvorenie operácie s daným názvom a návratovým typom.
     *
     * @param name Názov operácie.
     * @param type Návratový typ operácie.
     */
    public UMLOperation(String name, UMLClassifier type, String accesModifier)
    {
        super(name, type,accesModifier);
    }
    public UMLOperation(String name, UMLClassifier type)
    {
        super(name, type);
    }
    /**
     * Továrna metóda pre vytvorenie inštancie operácie.
     *
     * @param name Názov operácie.
     * @param type Návratový typ operácie.
     * @param args Zoznam argumentov operácie.
     * @return Objekt reprezentujúci operáciu v diagrame UML.
     */
    public static UMLOperation create(String name, UMLClassifier type, UMLAttribute... args)
    {
        UMLOperation operation = new UMLOperation(name,type);
        operation.argumentList = List.of(args);
        return operation;
    }

    /**
     * Pridá nový argument do zoznamu argumentov.
     * Argument sa vloží na koniec zoznamu.
     * Ak v zozname už existuje argument rovnakého názvu, operáciu nevykoná.
     *
     * @param arg Vkladaný argument.
     * @return Úspech operácie - true, ak sa podarilo vložiť, inak false.
     */
    public boolean addArgument(UMLAttribute arg)
    {
        for (UMLAttribute attr : this.argumentList) {
            if (attr.getName().equals(arg.getName())) {
                return false;
            }
        }
        this.argumentList.add(arg);
        return true;
    }

    /**
     * Vráti nemodifikovateľný zoznam argumentov.
     * Je možné využiť pre zobrazenie.
     *
     * @return Nemodifikovateľný zoznam argumentov.
     */
    public java.util.List<UMLAttribute> getArguments()
    {
        return Collections.unmodifiableList(this.argumentList);
    }

    /**
     * Vráti zoznam typov argumentov operácie.
     * Možné využiť v diagrame tried.
     *
     * @return Zoznam typov argumentov.
     */
    public java.util.List<UMLClassifier> getArgumentTypes()
    {
        java.util.List<UMLClassifier> argumentTypes = new ArrayList<>();
        for (UMLAttribute attr : this.argumentList) {
            UMLClassifier type = attr.getType();
            argumentTypes.add(type);
        }
        return argumentTypes;
    }

    /**
     * Vráti reťazec reprezentujúci operáciu v tvare "nazov_operacie(argumenty_operacie) : navratovy_typ"
     *
     * @return Reťazec reprezentujúci operáciu.
     */
    public String toString()
    {
        String operationName = super.getName();
        String operationReturnType = String.valueOf(this.getType());
        String operationArguments = String.valueOf(this.argumentList);
        operationArguments = operationArguments.substring(1,operationArguments.length()-1); //odstrani [ ]
        return operationName+"("+operationArguments+") : "+operationReturnType;
    }
}
