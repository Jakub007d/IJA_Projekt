package main.java;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Trieda implementuje Controller, ktorý sa stará o dáta načítané cez parser a stará sa o zmeny v zobrazení
 *
 * @author xdrobe01
 */
public class Controller implements ActionListener {
    private ClassDiagram classDiagram;
    private View view;
    /**
     * Konštruktor triedy Controller.
     *
     * @param view Referencia na triedu View
     */
    public Controller(View view){
        this.classDiagram = new JsonParser().parse();
        this.view=view;
        this.view.classPanel = new ClassPanel(classDiagram);
    }

    /**
     * Metóda, ktorá aktualizuje View po stlačení tlčítka.
     *
     * @param e Event stlacenia tlačítka
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.button)
        {
            view.classPanel.removeAll();

            for(int pos = 0 ;pos<= classDiagram.numberOfClasses() - 1; pos++)
            {
                System.out.println(pos);
                UMLClass tmpCLassReference = this.classDiagram.returnClassAtPos(pos);
                PanelForClass classPanel = new PanelForClass(tmpCLassReference,classDiagram);
                for(UMLAttribute attribute : tmpCLassReference.getAttributes())
                {
                    classPanel.addAttribute(attribute.toString(),attribute.getAccessModifier(),attribute.getName());
                }
                for(UMLAttribute operation : tmpCLassReference.getOperations())
                {
                    classPanel.addOperation(operation.toString(),operation.getAccessModifier(),operation.getName());
                }
                view.classPanel.add((JPanel)classPanel);
                view.classPanel.add(Box.createRigidArea(new Dimension(100,0)));

            }
            view.setVisible(true);
        }
        if (e.getSource() == view.saveButton)
        {

            for (Component component : view.classPanel.getComponents())
            {
                if (component.getName() != null)
                {
                    UMLClass classReference = ((PanelForClass) component).getClassReference();
                    Container container = (Container) component;
                    boolean classNameDone = false;
                    boolean attributesDone = false;
                    for (Component innerComponent : container.getComponents())
                    {
                        if(!classNameDone)
                        {
                            classDiagram.UpdateRelationShip(classReference);
                            classReference.rename(((JTextField) innerComponent).getText());
                            classNameDone=true;
                            component.setName(((JTextField) innerComponent).getText());
                        }
                         else if (!attributesDone)
                        {
                            Container attributes = (Container) innerComponent;
                            for (Component attribute : attributes.getComponents()) {
                                try {
                                    if (((JTextField)attribute).getText().equals(""))
                                    {
                                        classReference.removeAttribute(attribute.getName());
                                    }
                                    else {
                                        String[] readyToAttr = ((JTextField) attribute).getText().split(" ");
                                        String accesibility = Character.toString(readyToAttr[0].charAt(0));
                                        String attributeName = readyToAttr[0].substring(1).replace(":", "");
                                        String attributeType = readyToAttr[1];
                                        UMLAttribute attributeReference = classReference.containAttribute(attributeName);
                                        if (attributeReference != null) {
                                            if (!(attributeReference.getType()).getName().equals(attributeType))
                                                (attributeReference.getType()).rename(attributeType);
                                            if (!attributeReference.getAccessModifier().equals(accesibility))
                                                attributeReference.setAccessModifier(accesibility);
                                        } else {
                                            attributeReference = new UMLAttribute(attributeName, new UMLClassifier(attributeType), accesibility);
                                            classReference.addAttribute(attributeReference);
                                        }
                                    }

                                    attribute.setBackground(Color.white);
                                } catch (Exception exception) {
                                    ((JTextField)attribute).setText("Zle zadaný atribút");
                                    attribute.setBackground(Color.red);
                                    classReference.removeAttribute(attribute.getName());
                                }
                            }
                            attributesDone = true;
                        }
                         else
                        {
                            Container operations = (Container) innerComponent;
                            for (Component operation : operations.getComponents()) {
                                try {
                                    if (((JTextField)operation).getText().equals(""))
                                    {
                                        classReference.removeOperation(operation.getName());
                                    }
                                    else {
                                        String fromTextField = ((JTextField) operation).getText();
                                        String oldName = (((JTextField) operation).getName());
                                        System.out.println(fromTextField);
                                        String operationArgs = fromTextField.substring(fromTextField.indexOf("(") + 1, fromTextField.indexOf(")"));
                                        operationArgs = operationArgs.replace(" ","");
                                        operationArgs = operationArgs.replace(","," ");
                                        String[] operationArgTokens = operationArgs.split(" ");
                                        String toRemoveSubstring = fromTextField.substring(fromTextField.indexOf("("), fromTextField.indexOf(")")+1);
                                        fromTextField = fromTextField.replace(toRemoveSubstring,"");
                                        fromTextField = fromTextField.replace(" ","");
                                        fromTextField = fromTextField.replace(":"," ");
                                        String[] readyToOperation = fromTextField.split(" ");
                                        String accesibility = Character.toString(readyToOperation[0].charAt(0));
                                        String operationName = readyToOperation[0].substring(1);
                                        String operationType = readyToOperation[1];
                                        UMLOperation operationReference = classReference.containOperation(operationName);
                                        if (operationReference != null) {
                                            if (!(operationReference.getType()).getName().equals(operationType))
                                                (operationReference.getType()).rename(operationType);
                                            if (!operationReference.getAccessModifier().equals(accesibility))
                                                operationReference.setAccessModifier(accesibility);
                                            operationReference.deleteArguments();
                                            for(String argument : operationArgTokens)
                                            {
                                                String[] argumentTokens = argument.split(":");
                                                System.out.println(argumentTokens[0]+argumentTokens[1]+"________________________LOL________");
                                                operationReference.addArgument(new UMLAttribute(argumentTokens[0],new UMLClassifier(argumentTokens[1])));
                                            }
                                        } else {
                                            operationReference = new UMLOperation(operationName, new UMLClassifier(operationType), accesibility);
                                            for(String argument : operationArgTokens)
                                            {
                                                String[] argumentTokens = argument.split(":");
                                                operationReference.addArgument(new UMLAttribute(argumentTokens[0],new UMLClassifier(argumentTokens[1])));
                                            }
                                            classReference.removeOperation(oldName);
                                            classReference.addOperation(operationReference);
                                            System.out.println(operationReference.toString());
                                        }

                                    }

                                    operation.setBackground(Color.white);
                                } catch (Exception exception) {
                                    System.err.println(exception);
                                }

                            }
                        }
                    }
                }
            }
            Gson gson = new Gson();
            try (FileWriter writer = new FileWriter("data/testClassDiagramSave.json")) {
                gson.toJson(classDiagram, writer);
            } catch (Exception i) {
                i.printStackTrace();
            }
        }
        if (e.getSource() == view.loadSDButton) { /* nacitanie sekvencneho diagramu zo suboru */
//            JFileChooser fileChooser = new JFileChooser();
//            int response = fileChooser.showOpenDialog(null);
//
//            if (response == JFileChooser.APPROVE_OPTION) {
//                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
//                String fileName = fileChooser.getSelectedFile().getName();
//                System.out.println("*beep boop* with controller you loaded a file "+file);
//
//                Gson gson = new Gson();
//                try {
//                    SequenceDiagram sd = gson.fromJson(new FileReader(file), SequenceDiagram.class);
//                    SDView newSDView = new SDView(fileName,sd);
//                    newSDView.setVisible(true);
//                } catch (Exception exception) {
//                    System.err.println(exception);
//                }
//            } else System.out.println("you didn't load a file :/");
            SDController sdController = new SDController();
            sdController.loadSDFile(classDiagram);
        }

    }


}
