package RTU_JAVA_kurss.Extensions;

import javax.swing.*;
import java.awt.*;

public class MyTextArea extends JTextArea {

    public MyTextArea(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.setLineWrap(true); // texts automātiski pāriet nākamajā rindā, ja saskaras ar malu
        this.setOpaque(true);
    }
}
