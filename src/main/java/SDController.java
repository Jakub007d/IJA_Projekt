package main.java;

import com.google.gson.Gson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Trieda reprezentuje controller sekvenčného diagramu. Trieda implementuje rozhranie ActionListener.
 *
 * @author xstrak38
 */
public class SDController implements ActionListener {

    SequenceDiagram sequenceDiagram;
    ClassDiagram classDiagram;
    SDView sdView;

    /**
     * Konštruktor triedy SDController.
     */
    SDController() {}

    /**
     * Konštruktor triedy SDController.
     *
     * @param sdView Referencia na triedu SDView.
     */
    SDController(SDView sdView) {
        this.sdView = sdView;
    }

    /**
     * Nastaví referenciu na triedu SequenceDiagram.
     *
     * @param sequenceDiagram Sekvenčný diagram.
     */
    public void setSequenceDiagram(SequenceDiagram sequenceDiagram) {
        this.sequenceDiagram = sequenceDiagram;
    }

    /**
     * Nastavi referenciu na triedu ClassDiagram.
     *
     * @param classDiagram Diagram tried.
     */
    public void setClassDiagram(ClassDiagram classDiagram) {
        this.classDiagram = classDiagram;
    }

    /**
     * Metóda nahrá dáta zo súboru do štruktúry pre sekvenčný diagram.
     *
     * @param cd Diagram tried, ku ktorému sa má zobraziť sekvenčný diagram.
     */
    public void loadSDFile(ClassDiagram cd) {
        JFileChooser fileChooser = new JFileChooser();

        /* otvorí dialog pre vybratie súboru na spracovanie */
        int response = fileChooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            String fileName = fileChooser.getSelectedFile().getName();

            /* Zparsuje obsah súboru do štruktúry pre SequenceDiagram */
            Gson gson = new Gson();
            try {
                SequenceDiagram sd = gson.fromJson(new FileReader(file), SequenceDiagram.class);
                SDView newSDView = new SDView(fileName, sd, cd);
                newSDView.setVisible(true);
            } catch (Exception exception) {
                System.err.println(exception);
            }
        }
    }

    /**
     * Štruktúru pre sekvenčný diagram uloží do súboru vo formáte JSON.
     *
     * @param sd Sekvenčný diagram, ktorý sa má uložiť.
     */
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
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
