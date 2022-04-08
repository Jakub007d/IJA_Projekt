import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

public class Parser
{
   public ClassDiagram parse ()
   {
      ClassDiagram parsed = new ClassDiagram("UML");
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
            String[] line = data.split(" ");
            if(line[0].equals("class"))
            {
               UMLClass tmpCls;
               tmpCls = parsed.createClass(line[1]);
               String classAttrRead = dataScanner.nextLine();
               while(!classAttrRead.equals("}"))
               {
                  if(!classAttrRead.equals(""))
                  {
                     String[] line1 = classAttrRead.split(" ");
                     System.out.println(classAttrRead);
                     if(line1.length == 2) {
                        System.out.println("SOMTU");
                        UMLAttribute attr = new UMLAttribute(line1[1], new UMLClassifier(line1[0]));
                        tmpCls.addAttribute(attr);
                        classAttrRead = dataScanner.nextLine();
                     }
                     else
                        classAttrRead = dataScanner.nextLine();
                  }

               }
            }

         }

      }
      catch(FileNotFoundException e)
      {
         System.out.println("Nastala chyba pri nacitavani súboru !");
         e.printStackTrace();
         parsed=null;
      }
      return parsed;
   }



}
