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
         File inData = new File("data/cdNew.in"); /* example file */
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
            // TODO: regex - nech to splituje po whitespace
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
                     System.out.println(classAttrRead);
                     if (tokens1.length == 2) {
                        System.out.println("SOMTU");
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
               //prerobit poradie zle je
               System.out.println("Zadavam");
               parsed.createRelation(tokens[5],tokens[2],tokens[3],(UMLClass) parsed.findClassifier(tokens[1]),(UMLClass) parsed.findClassifier(tokens[4]));
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
