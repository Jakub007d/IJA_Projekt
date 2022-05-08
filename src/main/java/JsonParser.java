package main.java;

import com.google.gson.Gson;

import java.io.FileReader;

/**
 * Trieda načíta z json suboru danný Class Diagram
 */
public class JsonParser {
    public ClassDiagram parse()
    {
        Gson gson = new Gson();
        try
        {
            return gson.fromJson(new FileReader("data/ClassDiagram.json"),ClassDiagram.class);
        }
        catch (Exception exception)
        {
            System.err.println("Chyba ! Súbor nejestvuje");
            return null;
        }


    }
}
