package main.java;

import java.util.List;

public class UMLConstructor extends UMLOperation {
    /**
     * Konštruktor pre vytvorenie metody typu konštruktor v diagrame tried.
     *
     * @param name Názov metódy. (MUSÍ BYŤ TOTOŽNÝ S NÁZVOM TRIEDY)
     * @param accessModifier Modifikátor prístupu.
     */
    public UMLConstructor(String name, UMLClassifier type, String accessModifier)
    {
        super(name, type, accessModifier);
    }

    //tipujem ze toto budes chciet pouzivat v kombinacii so setArgumentList co som definovala v UMLOpertion
    public UMLConstructor(String name, String accessModifier)
    {
        super(name, null, accessModifier);
    }

    //toto sa asi nebude pouzivat idk
    public static UMLConstructor create(String name, String accessModifier, UMLAttribute... args)
    {
        UMLConstructor constructor = new UMLConstructor(name, null, accessModifier);
        constructor.setArgumentList(List.of(args));
        return constructor;
    }

    public String toString()
    {
        String operationName = super.getName();
        String operationArguments = String.valueOf(this.getArguments());
        operationArguments = operationArguments.substring(1,operationArguments.length()-1); //odstrani [ ]
        return operationName+"("+operationArguments+")";
    }
}
