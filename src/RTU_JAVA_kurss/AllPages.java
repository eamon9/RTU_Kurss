package RTU_JAVA_kurss;

import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

class MainPage implements ActionListener {
    // zemāk deklarējam un inicializējam JFrame, JLabel, JButton utt, lai būtu pieejami arī ārpus MainPage()
    MyFrame mainPage= new MyFrame();
    JLabel titleLabel= new JLabel("Document Solutions");
    JLabel subTitleLabel1= new JLabel("Mēs GLABĀJAM,");
    JLabel subTitleLabel2= new JLabel("SMALCINĀM,");
    JLabel subTitleLabel3= new JLabel("IZNĪCINĀM.");
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
        mainPage.setVisible(true); // padara rāmi redzamu
    } //End MainPage()

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startBtn)) {
            mainPage.dispose();
            new LoginPage();
        } //End if else statement
    } //End actionPerformed()
} //End MainPage class

class LoginPage implements ActionListener, MouseListener {

    MyFrame loginPageFrame= new MyFrame();
    JLabel registeredUserLabel= new JLabel("Esmu reģistrēts lietotājs");
    JLabel notRegisteredUserLabel= new JLabel("Jums nav lietotāja profila?");
    JLabel leftSideLabel= new JLabel();
    JLabel rightSideLabel= new JLabel();
    JTextField mailTextField= new JTextField();
    JPasswordField passwordField= new JPasswordField();
    JTextField fakePasswordField= new JTextField();
    JButton loginBtn= new JButton("Pieslēgties");
    JButton registrationBtn= new JButton("Reģistrēties");

    LoginPage() {
        loginPageFrame.setTitle("Document Solutions Login Page"); // izveido virsrakstu
        JTextField textField = new JTextField();
//Ja lietotājs reģistrēts, label un to komponentes
        registeredUserLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        registeredUserLabel.setForeground(new Color(7, 51, 51, 205));
        registeredUserLabel.setBounds(60, 60, 250, 50);

        mailTextField.setBounds(60, 140, 250, 45);
        mailTextField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        mailTextField.setForeground(Color.GRAY);
        mailTextField.setOpaque(true);
        mailTextField.setText("Jūsu e-pasta adrese");
        mailTextField.setEditable(false);
        mailTextField.addMouseListener(this);

        fakePasswordField.setBounds(60, 220, 250, 45);
        fakePasswordField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        fakePasswordField.setForeground(Color.GRAY);
        fakePasswordField.setOpaque(true);
        fakePasswordField.setText("Parole");
        fakePasswordField.setEditable(false);
        fakePasswordField.addMouseListener(this);

        passwordField.setBounds(60, 220, 250, 45);
        passwordField.setFont(new Font("Times New Roman", Font.BOLD, 24));
        passwordField.setOpaque(true);
        passwordField.setVisible(false);

        loginBtn.setBounds(80, 300, 200, 50);
        loginBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        loginBtn.setOpaque(true);
        loginBtn.setBorderPainted(false);
        loginBtn.setBackground(new Color(184, 229, 154));
        loginBtn.setFocusable(false);
        loginBtn.addActionListener(this);

//Ja lietotājs NAV reģistrēts, label un to komponentes
        notRegisteredUserLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        notRegisteredUserLabel.setForeground(new Color(7, 51, 51, 205));
        notRegisteredUserLabel.setBounds(60, 60, 250, 50);

        registrationBtn.setBounds(80, 140, 200, 50);
        registrationBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        registrationBtn.setOpaque(true);
        registrationBtn.setBorderPainted(false);
        registrationBtn.setBackground(new Color(184, 229, 154));
        registrationBtn.setFocusable(false);
        registrationBtn.addActionListener(this);

//left side label
        leftSideLabel.add(loginBtn);
        leftSideLabel.add(mailTextField);
        leftSideLabel.add(fakePasswordField);
        leftSideLabel.add(passwordField);
        leftSideLabel.add(textField);
        leftSideLabel.add(registeredUserLabel);
        leftSideLabel.setOpaque(true);
        leftSideLabel.setBounds(65, 100, 365, 500);
        leftSideLabel.setBackground(new Color(96, 94, 94, 76)); // 96, 94, 94, 76 Part1

//right side label
        rightSideLabel.add(registrationBtn);
        rightSideLabel.add(notRegisteredUserLabel);
        rightSideLabel.setOpaque(true);
        rightSideLabel.setBounds(480, 100, 350, 500);
        rightSideLabel.setBackground(new Color(96, 94, 94, 76)); // 105, 53, 53, 76 Part1

// loginPage nobeiguma daļa, gan .add, gan Layout un Visible set
        loginPageFrame.add(leftSideLabel); // pievieno iekavās norādīto pie galvenā rāmja- loginPageFrame
        loginPageFrame.add(rightSideLabel);
        loginPageFrame.setLayout(null);
        loginPageFrame.setVisible(true);
    } //End LoginPage()

    @Override
    public void actionPerformed(ActionEvent e) {
        // !!!!!!!  Projektam tuvojoties beigām, šeit jāsasaista ar datubāzi,
        // kas pārbauda, vai ir šāds lietotājvārds ar attiecīgo paroli
        // !!!!!!!                                     !!!!!!!!!!!!!!!!!!!!!!
        if(e.getSource().equals(loginBtn)) {
            String pas= String.valueOf(passwordField.getPassword());
            boolean isAlpha= CheckIfFieldsCorrect(mailTextField.getText());
            System.out.println("CheckFieldCorrect= "+isAlpha);
            if(isAlpha && pas.equals("qwerty")) {
                loginPageFrame.dispose();
                new OrderePageSelectItem();
            } //End inner if else statement
        } //End if else statement
    } //End actionPerformed()

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(mailTextField) && (mailTextField.getText().equals("Jūsu e-pasta adrese"))) {
            mailTextField.setEditable(true);
            mailTextField.setForeground(Color.BLACK);
            mailTextField.setText("");
        }
        if(e.getSource().equals(fakePasswordField)) {
            fakePasswordField.setVisible(false);
            passwordField.setVisible(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    boolean CheckIfFieldsCorrect(String name) {
        if(name.contains("@") && name.contains(".")) {
            System.out.println("Yes "+name+" contains '@' and '.'");
            return true;
        } else{
            System.out.println("No "+name+" doesn't contains '@' and '.'");
            return false;
        }

    }
} //End LoginPage class

class RegistrationPage implements ActionListener {
    RegistrationPage() {

    } //End RegistrationPage()

    @Override
    public void actionPerformed(ActionEvent e) {

    } //End actionPerformed()
} //End RegistrationPage class

class OrderePageSelectItem implements ActionListener {

    OrderePageSelectItem() {

    } //End OrderePageSelectItem()

    @Override
    public void actionPerformed(ActionEvent e) {

    } //End actionPerformed()
} //End OrderePageSelectItem class


