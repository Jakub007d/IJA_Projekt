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

//        SequenceDiagram sd = new SequenceDiagram("My Sequence Diagram");
//
//        UMLParticipant participant1 = sd.createParticipant("tvoja:Mamka");
//        UMLParticipant participant2 = sd.createParticipant("moja:Mamka");
//        UMLParticipant participant3 = sd.createParticipant("moja:Person");
//        UMLParticipant participant4 = sd.createParticipant(":Element");
//
//        sd.addMessage(participant1,participant2, UMLMessage.UMLMessageType.SYN, "jebem()");
//        sd.addMessage(participant2,participant3, UMLMessage.UMLMessageType.ASYN, "jebem()");
//        sd.addMessage(participant1,participant3, UMLMessage.UMLMessageType.ASYN, "jebem()");
//        sd.addMessage(participant3,participant2, UMLMessage.UMLMessageType.SYN, "jebem()");
//        sd.addMessage(participant3,participant4, UMLMessage.UMLMessageType.SYN, "getName()");
//
//        sd.checkConsistence(d);
//
//        Gson gejson = new Gson();
//
//        try (FileWriter writer = new FileWriter("C:\\Users\\terezka\\Documents\\testMamaSD.json")) {
//            gejson.toJson(sd, writer);
//        } catch (Exception i) {
//            i.printStackTrace();
//        }
//
//
//        for (UMLParticipant p : sd.getParticipantList()) {
//            System.out.println(p.getName()+p.getPresence());
//        }


        //TEST SD KONIEC

        UMLConstructor constructor = UMLConstructor.create("NAZOVTRIEDY", "+", new UMLAttribute("psicek", new UMLClassifier("dunco")));
        System.out.println(constructor);
    }
}

