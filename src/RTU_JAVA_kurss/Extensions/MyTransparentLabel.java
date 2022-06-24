package RTU_JAVA_kurss.Extensions;

import javax.swing.*;
import java.awt.*;

public class MyTransparentLabel extends JLabel {
    public MyTransparentLabel(int x, int y, int width, int height) {
        this.setOpaque(true);
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(46, 149, 169, 255)); //96, 94, 94, 76 part2
    }
}
