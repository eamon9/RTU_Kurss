package RTU_JAVA_kurss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MainPage implements ActionListener {
    // zemāk deklarējam un inicializējam JFrame, JLabel, JButton utt, lai būtu pieejami arī ārpus MainPage()
    MyFrame mainPage= new MyFrame("Document Solutions Main Page");
    JLabel titleLabel= new JLabel("Document Solutions");
    JLabel subTitleLabel1= new JLabel("Mēs GLABĀJAM,");
    JLabel subTitleLabel2= new JLabel("SMALCINĀM,");
    JLabel subTitleLabel3= new JLabel("IZNĪCINĀM.");
    JButton startBtn= new JButton("Sākt");

    MainPage() {
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

    MyFrame loginPageFrame= new MyFrame("Document Solutions Login Page");
    MyTransparentTextLabel registeredUserLabel= new MyTransparentTextLabel("Esmu reģistrēts lietotājs", 60, 60, 250, 50);
    MyTransparentTextLabel notRegisteredUserLabel= new MyTransparentTextLabel("Jums nav lietotāja profila?", 60, 60, 250, 50);
    MyTransparentLabel leftSideLabel= new MyTransparentLabel(65, 100, 365, 500);
    MyTransparentLabel rightSideLabel= new MyTransparentLabel(480, 100, 350, 500);
    MyTextField mailTextField= new MyTextField("Jūsu e-pasta adrese", 60, 140, 250, 45, true);
    MyPasswordField passwordField= new MyPasswordField(60, 220, 250, 45);
    MyTextField fakePasswordField= new MyTextField("Parole", 60, 220, 250, 45, true);
    MyButton loginBtn= new MyButton("Pieslēgties", 80, 300, 200, 50);
    MyButton registrationBtn= new MyButton("Reģistrēties", 80, 140, 200, 50);
    JTextField textField = new JTextField(); // ??

    LoginPage() {
//Reģistrēta lietoytāja komponentes

        mailTextField.addMouseListener(this);
        loginBtn.addActionListener(this);
        registrationBtn.addActionListener(this);
        fakePasswordField.addMouseListener(this);

        passwordField.setVisible(false);

//left side label
        leftSideLabel.add(loginBtn);
        leftSideLabel.add(mailTextField);
        leftSideLabel.add(fakePasswordField);
        leftSideLabel.add(passwordField);
        leftSideLabel.add(textField);
        leftSideLabel.add(registeredUserLabel);

//right side label
        rightSideLabel.add(registrationBtn);
        rightSideLabel.add(notRegisteredUserLabel);

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
        if(e.getSource().equals(registrationBtn)) {
            loginPageFrame.dispose();
            new RegistrationPage();
        }
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
    MyFrame registrationPageFrame= new MyFrame("Document Solutions registration Form");
    MyTransparentLabel mainLabel= new MyTransparentLabel(65, 100, 770, 500);
    MyRadioButton individualRadioBtn= new MyRadioButton("Privātpersona", 50, 10, 200, 50);
    MyRadioButton juridicalRadioBtn= new MyRadioButton("Juridiska persona", 50, 70, 200, 50);
    ButtonGroup personGroup;
    MyButton backBtn= new MyButton("< Atpakaļ", 290, 50, 200, 50);
    MyButton nextBtn= new MyButton(" Turpināt >", 510, 50, 200, 50);
    MyLabel nameLabel= new MyLabel("Vārds", 60, 120, 200, 50);
    MyLabel surnameLabel= new MyLabel("Uzvārds", 220, 120, 200, 50);
    MyLabel mailLabel= new MyLabel("E-pasts", 380, 120, 300, 50);
    MyLabel mobNrLabel= new MyLabel("Mob. Tālrunis", 60, 200, 200, 50);
    MyLabel passwordLabel= new MyLabel("Parole", 280, 200, 200, 50);
    MyLabel repeatPasswordLabel= new MyLabel("Atkārtojiet Paroli", 500, 200, 200, 50);

    MyLabel companyNameLabel= new MyLabel("Uzņēmuma nosaukums", 60, 280, 200, 50);
    MyLabel companyRegLabel= new MyLabel("Uzņēmuma reģ. Nr.", 380, 280, 200, 50);
    MyLabel pvnLabel= new MyLabel("PVN reģ. NR.", 540, 280, 200, 50);
    MyLabel bankLabel= new MyLabel("Banka, kods(SWIFT)", 540, 360, 200, 50);
    MyLabel companyAdressLabel= new MyLabel("Uzņēmuma adrese", 60, 360, 200, 50);
    MyLabel ibanLabel= new MyLabel("IBAN konts",  380, 360, 200, 50);

    MyTextField nameTF= new MyTextField(60, 160, 150, 40);
    MyTextField surnameTF= new MyTextField(220, 160, 150, 40);
    MyTextField mailTF= new MyTextField(380, 160, 330, 40);
    MyTextField mobNrTF= new MyTextField(60, 240, 210, 40);

    MyPasswordField passwordTF= new MyPasswordField(280, 240, 210, 40);
    MyPasswordField repeatPasswordTF= new MyPasswordField(500, 240, 210, 40);

    MyTextField companyNameTF= new MyTextField(60, 320, 310, 40);
    MyTextField companyRegTF= new MyTextField(380, 320, 150, 40);
    MyTextField pvnTF= new MyTextField(540, 320, 170, 40);

    MyTextField companyAdressTF= new MyTextField(60, 400, 310, 40);
    MyTextField ibanTF= new MyTextField(380, 400, 150, 40);
    MyTextField bankTF= new MyTextField(540, 400, 170, 40);


    RegistrationPage() {
        personGroup= new ButtonGroup(); // grupēšana nepieciešama, lai varētu atzīmēt tikai vienu no izvēlētajiem variantiem
        personGroup.add(individualRadioBtn);
        personGroup.add(juridicalRadioBtn);

        companyNameLabel.setVisible(false);
        companyNameTF.setVisible(false);
        companyRegLabel.setVisible(false);
        companyRegTF.setVisible(false);
        pvnLabel.setVisible(false);
        pvnTF.setVisible(false);
        bankLabel.setVisible(false);
        bankTF.setVisible(false);
        companyAdressLabel.setVisible(false);
        companyAdressTF.setVisible(false);
        ibanLabel.setVisible(false);
        ibanTF.setVisible(false);

        individualRadioBtn.addActionListener(this);
        juridicalRadioBtn.addActionListener(this);

        mainLabel.add(bankTF);
        mainLabel.add(ibanTF);
        mainLabel.add(companyAdressTF);
        mainLabel.add(pvnTF);
        mainLabel.add(companyRegTF);
        mainLabel.add(companyNameTF);
        mainLabel.add(repeatPasswordTF);
        mainLabel.add(passwordTF);
        mainLabel.add(mobNrTF);
        mainLabel.add(mailTF);
        mainLabel.add(surnameTF);
        mainLabel.add(nameTF);
        mainLabel.add(bankLabel);
        mainLabel.add(ibanLabel);
        mainLabel.add(pvnLabel);
        mainLabel.add(companyAdressLabel);
        mainLabel.add(companyRegLabel);
        mainLabel.add(companyNameLabel);
        mainLabel.add(repeatPasswordLabel);
        mainLabel.add(passwordLabel);
        mainLabel.add(mailLabel);
        mainLabel.add(mobNrLabel);
        mainLabel.add(surnameLabel);
        mainLabel.add(nameLabel);
        mainLabel.add(nextBtn);
        mainLabel.add(backBtn);
        mainLabel.add(individualRadioBtn);
        mainLabel.add(juridicalRadioBtn);

        registrationPageFrame.add(mainLabel);
        registrationPageFrame.setLayout(null);
        registrationPageFrame.setVisible(true);
    } //End RegistrationPage()

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(backBtn)) {
            registrationPageFrame.dispose();
            new LoginPage();
        }
        if(e.getSource().equals(nextBtn)) {
            // jāpārbauda vai visi lauki ir aizpildīti un vai atbilst rakstzīmes norādītajam,
            // lai varētu turpināt..
        }
        if(e.getSource().equals(individualRadioBtn)) {
            companyNameLabel.setVisible(false);
            companyNameTF.setVisible(false);
            companyRegLabel.setVisible(false);
            companyRegTF.setVisible(false);
            pvnLabel.setVisible(false);
            pvnTF.setVisible(false);
            bankLabel.setVisible(false);
            bankTF.setVisible(false);
            companyAdressLabel.setVisible(false);
            companyAdressTF.setVisible(false);
            ibanLabel.setVisible(false);
            ibanTF.setVisible(false);
        }

        if(e.getSource().equals((juridicalRadioBtn))) {
            companyNameLabel.setVisible(true);
            companyNameTF.setVisible(true);
            companyRegLabel.setVisible(true);
            companyRegTF.setVisible(true);
            pvnLabel.setVisible(true);
            pvnTF.setVisible(true);
            bankLabel.setVisible(true);
            bankTF.setVisible(true);
            companyAdressLabel.setVisible(true);
            companyAdressTF.setVisible(true);
            ibanLabel.setVisible(true);
            ibanTF.setVisible(true);
        }
    } //End actionPerformed()
} //End RegistrationPage class

class OrderePageSelectItem implements ActionListener {

    OrderePageSelectItem() {

    } //End OrderePageSelectItem()

    @Override
    public void actionPerformed(ActionEvent e) {

    } //End actionPerformed()
} //End OrderePageSelectItem class


