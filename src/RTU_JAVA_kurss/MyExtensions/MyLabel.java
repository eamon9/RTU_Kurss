package RTU_JAVA_kurss.MyExtensions;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    MyColor c= new MyColor();
    public MyLabel(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        //this.setForeground(c.myColor("TXT_DARK_GREEN"));
        this.setBounds(x, y, width, height);
    }

    public MyLabel(String text, int x, int y, int weight, int height, int letterSize) {
        this.setText(text);
        this.setForeground(c.TXT_GREEN);
        this.setFont(new Font("Times New Roman", Font.BOLD, letterSize));
        this.setBounds(x, y, weight, height);
    }
}
