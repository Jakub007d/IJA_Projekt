package main.java;

import com.google.gson.Gson;
/**
 * Hlavná trieda programu.
 * Obsahuje metódu main.
 *
 * @author xdrobe01
 */
public class run {
    /**
     * Metóda main vytvorí nový View;
     *
     * @param args argumenty
     */
    public static void main(String[] args) {
        new View();
        new SDView("moj-diagram-name-example.txt");

        //TEST
        ClassDiagram d = new ClassDiagram("My Class Diagram");

        UMLClassifier clsInt = d.classifierForName("int");
        UMLClassifier clsString = d.classifierForName("String");

        UMLAttribute atribute1 = new UMLAttribute("arg1", clsInt);
        UMLAttribute atribute2 = new UMLAttribute("arg2",clsString);

        UMLOperation op1 = UMLOperation.create("operaciaNazov",clsInt,atribute1,atribute2);
        System.out.println(op1);
        System.out.println(op1.getArguments());
        System.out.println(op1.getArgumentTypes());
        System.out.println(atribute1);
        System.out.println(atribute2);

        Gson gson = new Gson();
        String classDiagramAsJson = gson.toJson(d);
        System.out.println("class diagram JSON: "+classDiagramAsJson);

        //TEST KONIEC
        //TEST SD

        SequenceDiagram sd = new SequenceDiagram("My Sequence Diagram");



        //TEST SD KONIEC

    }
}

