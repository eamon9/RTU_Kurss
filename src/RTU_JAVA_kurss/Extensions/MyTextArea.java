package RTU_JAVA_kurss.Extensions;

import javax.swing.*;
import java.awt.*;

public class MyTextArea extends JTextArea {
    /*JScrollPane jScrollPane= new JScrollPane();*/
    public MyTextArea(int x, int y, int width, int height) {
        /*jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(x, y);*/
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.setLineWrap(true); // texts automātiski pāriet nākamajā rindā, ja saskaras ar malu
        this.setWrapStyleWord(true);
        this.setOpaque(true);
    }
}
