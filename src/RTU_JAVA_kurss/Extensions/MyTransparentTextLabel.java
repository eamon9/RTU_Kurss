package RTU_JAVA_kurss.Extensions;

import javax.swing.*;
import java.awt.*;

public class MyTransparentTextLabel extends JLabel {
    public MyTransparentTextLabel(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        this.setForeground(new Color(7, 51, 51, 205));
    }
}
