package RTU_JAVA_kurss.Extensions;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    public MyLabel(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        //this.setForeground(new Color(7, 51, 51, 205));
        this.setBounds(x, y, width, height);
    }

    public MyLabel(String text, int x, int y, int weight, int height, int letterSize) {
        this.setText(text);
        this.setForeground(new Color(7, 105, 64, 190));
        this.setFont(new Font("Times New Roman", Font.BOLD, letterSize));
        this.setBounds(x, y, weight, height);
    }
}
