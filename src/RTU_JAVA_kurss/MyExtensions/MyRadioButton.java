package RTU_JAVA_kurss.MyExtensions;

import javax.swing.*;
import java.awt.*;

public class MyRadioButton extends JRadioButton {
    MyColor myColor = new MyColor();
    public MyRadioButton(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        this.setForeground(myColor.TXT_DARK_GREEN);
        this.setOpaque(true);
        this.setBackground(myColor.LABEL_TRANSPARENT); //89, 141, 117, 255 part2
    }
}
