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
        /*
        //TEST
        ClassDiagram d;
        try{
            d = new Parser().parse();
        }
        catch (Exception lol)
        {
            d = new JsonParser().parse();
        }

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("data/ClassDiagram.json")) {
            gson.toJson(d, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ClassDiagram classs = new JsonParser().parse();
    */
        new View();
        //TEST KONIEC

//        SequenceDiagram sd = new SequenceDiagram("obhajoba");
//
//        UMLParticipant pMain = sd.createParticipant(":Main");
//        UMLParticipant pClassDiagram = sd.createParticipant(":ClassDiagram");
//        UMLParticipant c1UMLClass = sd.createParticipant("c1:UMLClass");
//        UMLParticipant a1UMLAttribute = sd.createParticipant("a1:UMLAttribute");
//
//        sd.addMessage(pMain, pClassDiagram, UMLMessage.UMLMessageType.SYN, "createClass(\"c1\"");
//        sd.addMessage(pClassDiagram, c1UMLClass, UMLMessage.UMLMessageType.SYNCREATE, "<<create>>(\"c1\")");
//        sd.addMessage(pClassDiagram, pMain, UMLMessage.UMLMessageType.RETURN, "c1");
//
//        sd.checkConsistence(d);
//
//        Gson gejson = new Gson();
//
//        try (FileWriter writer = new FileWriter("C:\\Users\\terezka\\Documents\\obhajobaSD.json")) {
//            gejson.toJson(sd, writer);
//        } catch (Exception i) {
//            i.printStackTrace();
//        }
    }
}

