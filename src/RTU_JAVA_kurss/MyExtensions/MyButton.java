package RTU_JAVA_kurss.MyExtensions;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    MyColor myColor = new MyColor();
    public MyButton(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setBackground(myColor.BTN);
        this.setFocusable(false);
    }
}
