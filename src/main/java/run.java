package main.java;

import com.google.gson.Gson;

import java.io.FileWriter;

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
        ClassDiagram d = new Parser().parse();

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("data/testClassDiagram.json")) {
            gson.toJson(d, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClassDiagram classs = new JsonParser().parse();

        //TEST KONIEC
        //TEST SD

        SequenceDiagram sd = new SequenceDiagram("My Sequence Diagram");

        sd.createParticipant("tvoja:Mamka");
        sd.createParticipant("moja:Mamka");
        sd.createParticipant("moja:Person");
        sd.checkConsistence(d);

        for (UMLParticipant p : sd.getParticipantList()) {
            System.out.println(p.getName()+p.getPresence());
        }


        //TEST SD KONIEC

        UMLConstructor constructor = UMLConstructor.create("NAZOVTRIEDY", "+", new UMLAttribute("psicek", new UMLClassifier("dunco")));
        System.out.println(constructor);
    }
}

