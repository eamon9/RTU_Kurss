package RTU_JAVA_kurss;

import javax.swing.*;
import java.awt.*;

class MyFrame extends JFrame {
    MyFrame(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // piešķir funkciju pogai 'X'- šajā gadījumā- AIZVĒRT LOGU
        this.setResizable(false); // nevar mainīt izmēru rāmim
        this.setSize(new Dimension(900, 700)); // piešķir konkrētu izmēru rāmim x un y dimensijā
        this.setLocationRelativeTo(null); // rāmis būs pa vidu ekrānam
        this.getContentPane().setBackground(new Color(173, 224, 203)); // nomaina fona krāsu //49, 48, 148 Part1
    }
}

class MyLabel extends JLabel {
    MyLabel(String text, int x, int y, int width, int height) {
        this.setText(text);
        this.setFont(new Font("Times New Roman", Font.BOLD, 18));
        //this.setForeground(new Color(7, 51, 51, 205));
        this.setBounds(x, y, width, height);
    }

    MyLabel(String text, int x, int y, int weight, int height, int letterSize) {
        this.setText(text);
        this.setForeground(new Color(7, 105, 64, 190));
        this.setFont(new Font("Times New Roman", Font.BOLD, letterSize));
        this.setBounds(x, y, weight, height);
    }
}

class MyTransparentLabel extends  JLabel {
    MyTransparentLabel(int x, int y, int width, int height) {
        this.setOpaque(true);
        this.setBounds(x, y, width, height);
        this.setBackground(new Color(46, 149, 169, 255)); //96, 94, 94, 76 part2
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

    MyTextField(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        this.setOpaque(true);
    }

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

class MyTextArea extends JTextArea {

    MyTextArea(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        this.setLineWrap(true); // texts automātiski pāriet nākamajā rindā, ja saskaras ar malu
        this.setOpaque(true);
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
        this.setBackground(new Color(46, 149, 169, 255)); //89, 141, 117, 255 part2
    }
}

class MyPasswordField extends JPasswordField {
    MyPasswordField(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.BOLD, 24));
        this.setOpaque(true);
    }
}

/*class MyComboBox extends JComboBox {
    MyComboBox(String[] list, int x, int y, int width, int height) {
        this.setEditable(true);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 20));
    }
}*/

