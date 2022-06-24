package RTU_JAVA_kurss.Extensions;

import javax.swing.*;
import java.awt.*;

public class MyRadioButton extends JRadioButton {
    public MyRadioButton(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        this.setForeground(new Color(7, 51, 51, 205));
        this.setOpaque(true);
        this.setBackground(new Color(46, 149, 169, 255)); //89, 141, 117, 255 part2
    }
}
