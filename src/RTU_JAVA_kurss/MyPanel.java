package RTU_JAVA_kurss;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MyPanel extends JPanel {
    Image img;

    MyPanel() {
        //img = new ImageIcon("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/IMAGES/logo_apple.png").getImage();
        this.setPreferredSize(new Dimension(400, 400));
        this.setBounds(0, 0, 400, 400);
        //this.setBackground(new Color(6, 51, 229));
        this.setVisible(true);

    }
    /*public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        //g2D.setStroke(new BasicStroke(25));

        GradientPaint gPaint= new GradientPaint(50, 50, new Color(173, 224, 203), 300, 100, new Color(143, 210, 205));
        g2D.setPaint(gPaint);
        //g2D.drawString("U R A WINNER! =D",50, 50);
        g2D.fill(new Rectangle2D.Double(50, 50, 300, 100));


        //g2D.drawImage(img, 0, 0, null);
        *//*g2D.drawLine(60, 60, 120, 120);
        g2D.drawLine(160, 160, 300, 300);*//*

    }*/
}
