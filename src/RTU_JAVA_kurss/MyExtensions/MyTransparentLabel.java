package RTU_JAVA_kurss.MyExtensions;

import javax.swing.*;

public class MyTransparentLabel extends JLabel {
    MyColor myColor = new MyColor();
    public MyTransparentLabel(int x, int y, int width, int height) {
        this.setOpaque(true);
        this.setBounds(x, y, width, height);
        this.setBackground(myColor.LABEL_TRANSPARENT); //96, 94, 94, 76 part2
    }
}
