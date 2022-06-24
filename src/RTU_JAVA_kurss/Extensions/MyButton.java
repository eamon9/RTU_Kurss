package RTU_JAVA_kurss.Extensions;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setBackground(new Color(184, 229, 154));
        this.setFocusable(false);
    }
}
