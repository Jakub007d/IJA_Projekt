import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Trieda slúži na parsovanie vstupných dát.
 *
 * @author xdrobe01
 */
public class Parser
{
   /** Metóda načída zo súboru testClassDiagram a nasledne prevedie parsovanie.
    *
    *
    * @return Diagram tried načítaný zo súboru.
    */
   public ClassDiagram parse ()
   {
      ClassDiagram parsed = new ClassDiagram("UML");
      try {
         File inData = new File("data/testClassDiagram.in"); /* example file */
         Scanner dataScanner = new Scanner(inData,"UTF-8");
         boolean umlStarted = false; /* flag - true when "@startuml" line is read */
         while (!umlStarted) {       /* while loop - skips empty lines */
            String data = dataScanner.nextLine();
            if (data.equals("@startuml"))
            {
               umlStarted = true;
               break;
            }
            if(!data.equals(""))
            {
               System.err.println("Chybne zadané dáta");
               exit(42); /* magic number */
            }
         }
         while (dataScanner.hasNextLine()) {
            String data = dataScanner.nextLine();
            String[] tokens = data.split(" ");   /* by som premenovala line na napr tokens, */
            if(tokens[0].equals("class"))              /* lebo na prve precitanie to bolo confusing */ {
               UMLClass tmpCls;
               tmpCls = parsed.createClass(tokens[1]);
               String classAttrRead = dataScanner.nextLine();
               while (!classAttrRead.equals("}")) {
                  if (!classAttrRead.equals("")) {
                     String[] tokens1 = classAttrRead.split(" ");
                     if (tokens1[0].equals("op")) {
                        UMLOperation op = new UMLOperation(tokens1[2], new UMLClassifier(tokens1[1]));
                        tmpCls.addAttribute(op);
                     }
                     if (tokens1.length == 2) {
                        UMLAttribute attr = new UMLAttribute(tokens1[1], new UMLClassifier(tokens1[0]));
                        tmpCls.addAttribute(attr);
                        classAttrRead = dataScanner.nextLine();
                     } else
                        classAttrRead = dataScanner.nextLine();
                  }

               }
            }
            if(tokens[0].equals("relation"))
            {
               parsed.createRelation(tokens[5],tokens[2],tokens[3],(UMLClass) parsed.findClassifier(tokens[1]),(UMLClass) parsed.findClassifier(tokens[4]));
            }
         }




      }
      catch(FileNotFoundException e)
      {
         System.err.println("Nastala chyba pri nacitavani súboru !");
         e.printStackTrace();
         parsed=null;
      }
      return parsed;
   }



}