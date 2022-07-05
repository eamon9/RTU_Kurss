package RTU_JAVA_kurss.MyExtensions;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // piešķir funkciju pogai 'X'- šajā gadījumā- AIZVĒRT LOGU
        this.setResizable(false); // nevar mainīt izmēru rāmim
        this.setSize(new Dimension(900, 700)); // piešķir konkrētu izmēru rāmim x un y dimensijā
        this.setLocationRelativeTo(null); // rāmis būs pa vidu ekrānam
        this.getContentPane().setBackground(new Color(173, 224, 203)); // nomaina fona krāsu //49, 48, 148 Part1
    }
}
