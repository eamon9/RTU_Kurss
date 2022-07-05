package RTU_JAVA_kurss.MyExtensions;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField {
    public MyTextField(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        this.setOpaque(true);
    }

    public MyTextField(String text, int x, int y, int width, int height, boolean showBtn) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        this.setForeground(Color.GRAY);
        this.setOpaque(true);
        this.setText(text);
        this.setEditable(false);
        this.setVisible(showBtn);
    }
}
