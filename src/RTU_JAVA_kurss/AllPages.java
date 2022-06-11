package RTU_JAVA_kurss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainPage implements ActionListener {
    MyFrame mainPage= new MyFrame();
    JLabel titleLabel= new JLabel("Document Solutions");
    JLabel subTitleLabel1= new JLabel("Mēs GLABĀJAM,");
    JLabel subTitleLabel2= new JLabel("SMALCINAM,");
    JLabel subTitleLabel3= new JLabel("IZNĪCINAM.");
    JButton startBtn= new JButton("Sākt");

    MainPage() {
        mainPage.setTitle("Document Solutions Main Page");

        titleLabel.setForeground(new Color(11, 87, 3));
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
        titleLabel.setBounds(200, 100, 600, 300);

        subTitleLabel1.setForeground(new Color(7, 105, 64, 190));
        subTitleLabel1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        subTitleLabel1.setBounds(250, 270, 250, 50);

        subTitleLabel2.setForeground(new Color(7, 105, 64, 190));
        subTitleLabel2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        subTitleLabel2.setBounds(400, 290, 250, 50);

        subTitleLabel3.setForeground(new Color(7, 105, 64, 190));
        subTitleLabel3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        subTitleLabel3.setBounds(520, 310, 250, 50);

        startBtn.setBounds(350, 450, 200, 50);
        startBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        startBtn.setOpaque(true);
        startBtn.setBorderPainted(false);
        startBtn.setBackground(new Color(184, 229, 154));
        startBtn.setFocusable(false);
        startBtn.addActionListener(this);


        mainPage.add(startBtn);
        mainPage.add(subTitleLabel3);
        mainPage.add(subTitleLabel2);
        mainPage.add(subTitleLabel1);
        mainPage.add(titleLabel);
        mainPage.setLayout(null);
        mainPage.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startBtn)) {
            mainPage.dispose();
            new LoginPage();
        }
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


