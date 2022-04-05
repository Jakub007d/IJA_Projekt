import javax.swing.*;
import java.awt.*;

/**
 * trieda run
 */
public class run {
    /**
     * metoda main
     * @param args argumenty
     */
    public static void main(String[] args) {
        System.out.println("Tu bude onedlho kód :D");
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);
        panel.setBounds(0,0,100,100);
        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JLabel label1 = new JLabel("LOL IM BLUE");
        label.setText("IJA PROJEKT VERZIA ALPHA 1.0");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("IJA_Projekt");
        frame.setSize(500,500);
        frame.setVisible(true);
        panel.add(label1);
        frame.add(panel);
        frame.add(label);

    }
}

