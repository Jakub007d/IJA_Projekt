package main.java;

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
               if(!tokens[2].equals("{"))
               {
                  tmpCls = new UMLClass(tokens[1],(UMLClass) parsed.findClassifier(tokens[3]));
                  if(tmpCls.getParentClass() == null)
                  System.err.println("Dedí z null!");
               }
               else
               {
                  tmpCls = new UMLClass(tokens[1]);
               }
               String classAttrRead = dataScanner.nextLine();
               while (!classAttrRead.equals("}")) {
                  if (!classAttrRead.equals("")) {
                     String[] tokens1 = classAttrRead.split(" ");
                     if (tokens1[1].equals("op")) {

                        if (tokens1[4].equals("."))
                        {
                           UMLOperation op = new UMLOperation(tokens1[3], new UMLClassifier(tokens1[2]),tokens1[0]);
                           tmpCls.addOperation(op);
                        }
                        else
                        {
                           UMLOperation op = new UMLOperation(tokens1[3], new UMLClassifier(tokens1[2]),tokens1[0]);
                           tokens1 = classAttrRead.split("<-");
                           tokens1 = tokens1[1].split(" ");
                           for (int i = 0; i <= tokens1.length-1;i=i+2)
                           {
                              if(!tokens1[i].equals("_") && !tokens1[i].equals(""))
                              {
                                 UMLAttribute arg = new UMLAttribute(tokens1[i+1],new UMLClassifier(tokens1[i]));
                                 op.addArgument(arg);
                              }


                           }
                           tmpCls.addOperation(op);
                        }

                     }
                     if (tokens1.length == 3) {
                        UMLAttribute attr = new UMLAttribute(tokens1[2], new UMLClassifier(tokens1[1]),tokens1[0]);
                        tmpCls.addAttribute(attr);
                        classAttrRead = dataScanner.nextLine();
                     } else
                        classAttrRead = dataScanner.nextLine();
                  }

               }
               parsed.addClass(tmpCls);
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
