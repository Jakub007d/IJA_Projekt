package main.java;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SDController implements ActionListener {

    SequenceDiagram sequenceDiagram;
    ClassDiagram classDiagram;
    SDView sdView;

    SDController() {}

    SDController(SDView sdView) {
        this.sdView = sdView;
    }

    public void setSequenceDiagram(SequenceDiagram sequenceDiagram) {
        this.sequenceDiagram = sequenceDiagram;
    }

    public void setClassDiagram(ClassDiagram classDiagram) {
        this.classDiagram = classDiagram;
    }

    public void loadSDFile(ClassDiagram cd) {
        //this.classDiagram = classDiagram;
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            String fileName = fileChooser.getSelectedFile().getName();
            System.out.println("*beep boop* with controller you loaded a file "+file);

            Gson gson = new Gson();
            try {
                SequenceDiagram sd = gson.fromJson(new FileReader(file), SequenceDiagram.class);
                SDView newSDView = new SDView(fileName,sd,cd);
                newSDView.setVisible(true);
            } catch (Exception exception) {
                System.err.println(exception);
            }
        } else System.out.println("you didn't load a file :/");
    }

    public void saveSDFile(SequenceDiagram sd) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

            Gson gson = new Gson();
            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(sd, writer);
            } catch (Exception e) {
                System.err.println(e);
            }

            System.out.println("*beep boop* you saved a file "+file);
        } else System.out.println("*beep boop* you didn't save a file");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
