package RTU_JAVA_kurss;

import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {
    //MyPanel panel;
    MyFrame(String title) {
        //panel= new MyPanel();
        //panel.setVisible(true);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // piešķir funkciju pogai 'X'- šajā gadījumā- AIZVĒRT LOGU
        this.setResizable(false); // nevar mainīt izmēru rāmim
        this.setSize(new Dimension(900, 700)); // piešķir konkrētu izmēru rāmim x un y dimensijā
        this.setLocationRelativeTo(null); // rāmis būs pa vidu ekrānam
        this.getContentPane().setBackground(new Color(173, 224, 203)); // nomaina fona krāsu //49, 48, 148 Part1
        //this.add(panel);
    }
}

class MyPanel extends JPanel {
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

class MyLabel extends JLabel {
    MyLabel(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        this.setForeground(new Color(7, 51, 51, 205));
        this.setBounds(x, y, width, height);
    }
}

class MyTransparentLabel extends  JLabel {
    MyTransparentLabel(int x, int y, int width, int height) {
        this.setOpaque(true);
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(96, 94, 94, 76));
    }
}

class MyTransparentTextLabel extends JLabel {
    MyTransparentTextLabel(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        this.setForeground(new Color(7, 51, 51, 205));
    }
}

class MyTextField extends JTextField {
    MyTextField(String text, int x, int y, int width, int height, boolean showBtn) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        this.setForeground(Color.GRAY);
        this.setOpaque(true);
        this.setText(text);
        this.setEditable(false);
        this.setVisible(showBtn);
    }
}

class MyButton extends JButton {
    MyButton(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 20));
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setBackground(new Color(184, 229, 154));
        this.setFocusable(false);
    }
}

class MyRadioButton extends JRadioButton {
    MyRadioButton(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        this.setForeground(new Color(7, 51, 51, 205));
        this.setOpaque(true);
        this.setBackground(new Color(89, 141, 117, 255));
    }
}

