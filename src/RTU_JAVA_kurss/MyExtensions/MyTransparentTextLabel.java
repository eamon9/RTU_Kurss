package RTU_JAVA_kurss.MyExtensions;

import javax.swing.*;
import java.awt.*;

public class MyTransparentTextLabel extends JLabel {
    MyColor myColor = new MyColor();
    public MyTransparentTextLabel(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        this.setForeground(myColor.TXT_DARK_GREEN);
    }
}
