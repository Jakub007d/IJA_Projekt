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
                     if (line1[0].equals("op"))
                     {
                        UMLOperation op = new UMLOperation(line1[2],new UMLClassifier(line1[1]));
                        tmpCls.addAttribute(op);
                     }
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
               if(line[0].equals("relation"))
               {
                  //prerobit poradie zle je
                  
                  parsed.createRelation(line[1],line[2],line[3],(UMLClass) parsed.findClassifier(line[4]),(UMLClass) parsed.findClassifier(line[5]));
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
