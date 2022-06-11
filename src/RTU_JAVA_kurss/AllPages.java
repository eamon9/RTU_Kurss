package RTU_JAVA_kurss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainPage implements ActionListener {

    MainPage() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class LoginPage implements ActionListener {

    LoginPage() {
        MyFrame loginPageFrame= new MyFrame();
        loginPageFrame.setTitle("Document Solutions Login Page"); // izveido virsrakstu
        JTextField textField = new JTextField();

        JLabel registeredUserLabel= new JLabel("Esmu reģistrēts lietotājs");
        registeredUserLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        registeredUserLabel.setForeground(new Color(7, 51, 51, 205));
        registeredUserLabel.setBounds(60, 60, 250, 50);

        JLabel notRegisteredUserLabel= new JLabel("Jums nav lietotāja profila?");
        notRegisteredUserLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        notRegisteredUserLabel.setForeground(new Color(7, 51, 51, 205));
        notRegisteredUserLabel.setBounds(60, 60, 250, 50);

        JLabel leftSideLabel= new JLabel();
        leftSideLabel.add(textField);
        leftSideLabel.add(registeredUserLabel);
        leftSideLabel.setOpaque(true);
        leftSideLabel.setBounds(80, 100, 350, 500);
        leftSideLabel.setBackground(new Color(96, 94, 94, 76)); // 96, 94, 94, 76 Part1


        JLabel rightSideLabel= new JLabel();
        rightSideLabel.add(notRegisteredUserLabel);
        rightSideLabel.setOpaque(true);
        rightSideLabel.setBounds(480, 100, 350, 500);
        rightSideLabel.setBackground(new Color(96, 94, 94, 76)); // 105, 53, 53, 76 Part1

        loginPageFrame.add(leftSideLabel); // pievieno iekavās norādīto pie galvenā rāmja- loginPageFrame
        loginPageFrame.add(rightSideLabel);
        loginPageFrame.setLayout(null);
        loginPageFrame.setVisible(true);
        //coments
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class OrderePageSelectItem implements ActionListener {

    OrderePageSelectItem() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


