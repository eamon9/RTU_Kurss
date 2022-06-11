package RTU_JAVA_kurss;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    //MyPanel panel;
    MyFrame() {
        //panel= new MyPanel();
        //panel.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // piešķir funkciju pogai 'X'- šajā gadījumā- AIZVĒRT LOGU
        this.setResizable(false); // nevar mainīt izmēru rāmim
        this.setSize(new Dimension(900, 700)); // piešķir konkrētu izmēru rāmim x un y dimensijā
        this.setLocationRelativeTo(null); // rāmis būs pa vidu ekrānam
        this.getContentPane().setBackground(new Color(173, 224, 203)); // nomaina fona krāsu //49, 48, 148 Part1
        //this.add(panel);
        //this.setVisible(true); // padara rāmi redzamu
    }
}
