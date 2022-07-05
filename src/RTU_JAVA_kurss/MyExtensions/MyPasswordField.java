package RTU_JAVA_kurss.MyExtensions;

import javax.swing.*;
import java.awt.*;

public class MyPasswordField extends JPasswordField {
    public MyPasswordField(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 24));
        this.setOpaque(true);
    }
}
