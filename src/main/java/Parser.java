import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class Parser
{
   public static void main(String[] args)
   {
      try {
         boolean umlStarted = false;
         File inData = new File("data/cdNew.in");
         Scanner dataScanner = new Scanner(inData);
         while (!umlStarted) {
            String data = dataScanner.nextLine();
            if (data.equals("@startuml"))
            {
               umlStarted= true;
               break;
            }
            if(!data.equals(""))
            {
               System.err.println("Chybne zadané dáta");
               exit(41);
            }
         }
         while (dataScanner.hasNextLine()) {
            String data = dataScanner.nextLine();
            System.out.println(data);
         }

      }
      catch(FileNotFoundException e)
      {
         System.out.println("Nastala chyba pri nacitavani súboru !");
         e.printStackTrace();
      }
   }



}
