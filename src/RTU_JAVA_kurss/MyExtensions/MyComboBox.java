package RTU_JAVA_kurss.MyExtensions;

import javax.swing.*;
import java.awt.*;

public class MyComboBox extends JComboBox {
    public MyComboBox(String text, int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.addItem(text);
    }
}

