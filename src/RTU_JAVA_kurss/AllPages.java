package RTU_JAVA_kurss;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

class MainPage implements ActionListener {
    // zemāk deklarējam un inicializējam JFrame, JLabel, JButton utt, lai būtu pieejami arī ārpus MainPage()
    MyFrame mainPageFrame = new MyFrame("Document Solutions Main Page");
    JLabel titleLabel= new JLabel("Document Solutions");
    MyLabel subTitleLabel1 = new MyLabel("Mēs GLABĀJAM", 250, 270, 250, 50, 20);
    MyLabel subTitleLabel2 = new MyLabel("SMALCINĀM,", 400, 290, 250, 50, 20);
    MyLabel subTitleLabel3 = new MyLabel("IZNĪCINĀM.", 520, 310, 250, 50, 20);
    MyButton startBtn= new MyButton("Sākt", 350, 450, 200, 50);

    MainPage() {
        titleLabel.setForeground(new Color(11, 87, 3));
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
        titleLabel.setBounds(200, 100, 600, 300);

        startBtn.addActionListener(this);

        MyLabel[] subLabels= new MyLabel[] {subTitleLabel1, subTitleLabel2, subTitleLabel3};
        for (MyLabel subLabel : subLabels) {
            mainPageFrame.add(subLabel);
        }
        mainPageFrame.add(startBtn);
        mainPageFrame.add(titleLabel);
        mainPageFrame.setLayout(null);
        mainPageFrame.setVisible(true); // padara rāmi redzamu
    } //End MainPage()

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startBtn)) {
            startBtn.setBackground(new Color(141, 210, 93));
            mainPageFrame.dispose();
            new LoginPage();
        } //End if else statement
    } //End actionPerformed()
} //End MainPage class

class LoginPage extends Component implements ActionListener, MouseListener {
    WriteTextToFile wttf= new WriteTextToFile();
    GetTextFromFile gtff= new GetTextFromFile();
    GetUserInfo gui= new GetUserInfo();
    GetLastOrdersInfo gloi= new GetLastOrdersInfo();
    MyFrame loginPageFrame= new MyFrame("Document Solutions Login Page");
    MyTransparentTextLabel registeredUserLabel= new MyTransparentTextLabel("Esmu reģistrēts lietotājs", 85, 60, 250, 50);
    MyTransparentTextLabel notRegisteredUserLabel= new MyTransparentTextLabel("Jums nav lietotāja profila?", 75, 60, 250, 50);
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
        if(e.getSource().equals(loginBtn)) {
            String mail= mailTextField.getText();
            String password= String.valueOf(passwordField.getPassword());

            user = getAuthenticUser(mail, password);

            if(user != null) {
                String sourceFolder= "/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/";
                System.out.println("Sveiks "+user.name+"!");
                wttf.writeTextToFile(sourceFolder+"users_ID.txt", user.UserID);
                String userIDs= gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/users_ID.txt");
                //izvadīt vārdu, kas ir esošā lietotāja ID
                System.out.println(gui.getUsersInfo(user.UserID, "name"));

                //izvadīt- šķirojot pasītījumus pēc (OrderID), pēdējā lietotāja ID(userID)
                //System.out.println(gloi.getLastOrdersInfo("orderID", "userID"));

                //izvadīt- starp lietotājiem(userID), šķirojot pēc lietotājiem(userID), kas ir pēdējais pasūtījuma nr(OrderID)
                //System.out.println(gloi.getLastOrdersInfo("userID", "OrderId", "userId"));

                //      Izvadīt esošā lietotāja pēdējo pasūtījuma orderID,
                //kā pirmo parametru vadot (kurā sarakstā jāmeklē, piem. esošā userId),
                //otro elementu (pēc kā sortējam, piem. orderID, jo viņi neatkārtojas un iet pēc kārtas),
                //trešo elementu (ko izvadīt konsolē, piem. orderID)
                System.out.println("Pēdējā pasūtījuma ID: "+gloi.getLastOrdersInfo(userIDs, "orderID", "orderID"));

                loginPageFrame.dispose();
                new OrderPageSelectItem();
            }
            else {
                JOptionPane.showMessageDialog(LoginPage.this, "Lietotājvārds un/vai parole nepareiza", "Mēģiniet vēlreiz!", JOptionPane.ERROR_MESSAGE);
            }
        }
        // !!!!!!!  Projektam turbojet beigām, šeit jāsasaista ar datubāzi,
        // kas pārbauda, vai ir šāds lietotājvārds ar attiecīgo paroli
        // !!!!!!!                                     !!!!!!!!!!!!!!!!!!!!!!
        if(e.getSource().equals(loginBtn)) {
            loginBtn.setBackground(new Color(141, 210, 93));
            registrationBtn.setBackground(new Color(184, 229, 154));
        } //End if else statement
        if(e.getSource().equals(registrationBtn)) {
            registrationBtn.setBackground(new Color(141, 210, 93));
            loginBtn.setBackground(new Color(184, 229, 154));
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
            fakePasswordField.setVisible(false);
            passwordField.setVisible(true);
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

    public User user;
    private User getAuthenticUser(String mail, String password) {
        User user= null;
        final String DB_URL= "jdbc:mysql://localhost:3306/JAVA_IT";
        final String USERNAME= "root";
        final String PASSWORD= "e6127609-";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            connection.createStatement();
            String sql= "SELECT * FROM users WHERE mail=? AND password=?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);

            ResultSet resultSet= preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new User();
                user.UserID= resultSet.getString("UserID");
                user.name= resultSet.getString("name");
                user.surname= resultSet.getString("surname");
                user.mail= resultSet.getString("mail");
                user.password= resultSet.getString("password");
                user.mobile= resultSet.getString("mobile");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

} //End LoginPage class

class RegistrationPage extends Component implements ActionListener {
    GetTextFromFile gtff= new GetTextFromFile();
    WriteTextToFile wttf= new WriteTextToFile();

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

    MyLabel[] labelsForJuridical= new MyLabel[] {companyNameLabel, companyRegLabel, pvnLabel, bankLabel, companyAdressLabel, ibanLabel};
    MyTextField[] juridicalTF= new MyTextField[] {companyNameTF, companyRegTF, pvnTF, bankTF, companyAdressTF, ibanTF};


    RegistrationPage() {
        personGroup= new ButtonGroup(); // grupēšana nepieciešama, lai varētu atzīmēt tikai vienu no izvēlētajiem variantiem
        personGroup.add(individualRadioBtn);
        personGroup.add(juridicalRadioBtn);
// visi 'label' priekš juridiskās daļas, kam noklusējumā jābūt slēptiem
        for (int i = 0; i < labelsForJuridical.length; i++) {
            labelsForJuridical[i].setVisible(false);
            mainLabel.add(labelsForJuridical[i]);
        }
// visi 'label' priekš privātpersonas daļas, kam noklusējumā jābūt redzamiem
        MyLabel[] labelsForIndividual= new MyLabel[] {mailLabel, mobNrLabel, surnameLabel, nameLabel};
        for (int i = 0; i < labelsForIndividual.length; i++) {
            mainLabel.add(labelsForIndividual[i]);
        }
// visi 'TextField' priekš juridiskās daļas, kam noklusējumā jābūt slēptiem un tiek pievienoti mainLabel klasei ar .add() Metodi
        for (int i = 0; i < juridicalTF.length; i++) {
            juridicalTF[i].setVisible(false);
            mainLabel.add(juridicalTF[i]);
        }
// visi 'TextField' priekš privātpersonu daļas tiek pievienoti mainLabel klasei ar Metodi .add();
        MyTextField[] individualTF= new MyTextField[] {mobNrTF, mailTF, surnameTF, nameTF};
        for (int i = 0; i < individualTF.length; i++) {
            mainLabel.add(individualTF[i]);
        }

        individualRadioBtn.addActionListener(this);
        juridicalRadioBtn.addActionListener(this);
        backBtn.addActionListener(this);
        nextBtn.addActionListener(this);

        mainLabel.add(repeatPasswordTF);
        mainLabel.add(passwordTF);
        mainLabel.add(repeatPasswordLabel);
        mainLabel.add(passwordLabel);

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
            backBtn.setBackground(new Color(141, 210, 93));
            nextBtn.setBackground(new Color(184, 229, 154));
            registrationPageFrame.dispose();
            new LoginPage();
        }
        if(e.getSource().equals(nextBtn)) {
            nextBtn.setBackground(new Color(141, 210, 93));
            backBtn.setBackground(new Color(184, 229, 154));
            wttf.writeTextToFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/uEmail.txt", mailTF.getText());
            registerUser();
        }
        if(e.getSource().equals(individualRadioBtn)) {
            for (int i = 0; i < labelsForJuridical.length; i++) {
                labelsForJuridical[i].setVisible(false);
            }
            for (int i = 0; i < juridicalTF.length; i++) {
                juridicalTF[i].setVisible(false);
            }
        }
        if(e.getSource().equals((juridicalRadioBtn))) {
            for (int i = 0; i < labelsForJuridical.length; i++) {
                labelsForJuridical[i].setVisible(true);
            }
            for (int i = 0; i < juridicalTF.length; i++) {
                juridicalTF[i].setVisible(true);
            }
        }
    } //End actionPerformed()

    User user;
    private void registerUser() {
        String name= nameTF.getText();
        String surname= surnameTF.getText();
        String mail= mailTF.getText();
        String password= String.valueOf(passwordTF.getPassword());
        String mobile= mobNrTF.getText();

        if(name.isEmpty() || surname.isEmpty() || mail.isEmpty() || mobile.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lūdzu aizpildiet visus laukus", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Jūsu izveidotā parole ir par mazu"+"\n"+"MIN 6simboli", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!mobile.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Mobīlais telefons jānorāda skaitļos!", "Mēģini vēlreiz!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user= addUserToDatabase(name, surname, mail, password, mobile);
        if(user != null) {
            registrationPageFrame.dispose();
            LoginPage loginPage= new LoginPage();
            loginPage.mailTextField.setText(gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/uEmail.txt")); //ievada esošo reģistrēto e-pastu
            loginPage.mailTextField.setEditable(true);
            loginPage.fakePasswordField.setVisible(false);
            loginPage.passwordField.setVisible(true);

        }
        else {
            JOptionPane.showMessageDialog(this, "Neizdevās izveidot jaunu lietotāju", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
        }
    }

    private User addUserToDatabase(String name, String surname, String mail, String password,  String mobile) {
        User user= null;
        final String DB_URL= "jdbc:mysql://localhost:3306/JAVA_IT"; //jānorāda datubāzes lokācija, kas jau iepriekš ir izveidota
        final String USERNAME= "root"; // šis ir noklusējuma username
        final String PASSWORD= "e6127609-"; // šī ir izveidotā parole iekš MySQL

        try{
            Connection connection= DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql= "INSERT INTO users (name, surname, mail, password, mobile) "+ "VALUES (?, ?, ?, ?, ?) ";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, mobile);

            int addedRows= preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user= new User();
                user.name= name;
                user.surname= surname;
                user.mail= mail;
                user.password= password;
                user.mobile= mobile;
            }
            statement.close();
            connection.close();
        } catch (SQLException s) {
            if(s.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(this, "Šāds telefons/ e-pasts jau eksitē!", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

} //End RegistrationPage class

class OrderPageSelectItem implements ActionListener, MouseListener {
    MyFrame orderPageFrame= new MyFrame("Document Solutions Order Page");
    MyTransparentLabel sideLabel1= new MyTransparentLabel(65, 100, 365, 500);
    MyTransparentLabel sideLabel2= new MyTransparentLabel(480, 100, 350, 500);
    MyTransparentLabel leftSideLabel= new MyTransparentLabel(65, 100, 240, 500);
    MyTransparentLabel middleSideLabel= new MyTransparentLabel(330, 100, 240, 500);
    MyTransparentLabel rightSideLabel= new MyTransparentLabel(600, 100, 240, 500);
    MyTransparentTextLabel servicesTextLabel = new MyTransparentTextLabel("Pakalpojumi", 135, 60, 250, 50);
    MyTransparentTextLabel previousOrdersTextLabel= new MyTransparentTextLabel("Iepriekšējie pasūtījumi", 90, 60, 250, 50);
    MyTransparentTextLabel leftSideTextLabel= new MyTransparentTextLabel("Dokumentu glabāšana", 30, 20, 240, 100);
    MyTransparentTextLabel middleSideTextLabel= new MyTransparentTextLabel("Smalcināšana / iznīcināšana", 10, 20, 240, 100);
    MyTransparentTextLabel rightSideTextLabel= new MyTransparentTextLabel("Arhīva sakārtošana", 40, 20, 240, 100);

    OrderPageSelectItem() {
        sideLabel2.add(previousOrdersTextLabel);
        sideLabel1.add(servicesTextLabel);
        leftSideLabel.add(leftSideTextLabel);
        middleSideLabel.add(middleSideTextLabel);
        rightSideLabel.add(rightSideTextLabel);

        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            labels[i].setVisible(false);
            orderPageFrame.add(labels[i]);
            labels[i].addMouseListener(this);
        }

        sideLabel1.addMouseListener(this);
        sideLabel2.addMouseListener(this);

        orderPageFrame.add(sideLabel2);
        orderPageFrame.add(sideLabel1);
        orderPageFrame.setLayout(null);
        orderPageFrame.setVisible(true);
    } //End OrderPageSelectItem()

    @Override
    public void actionPerformed(ActionEvent e) {
    } //End actionPerformed()

    @Override
    public void mouseClicked(MouseEvent e) {
            if(e.getSource().equals(leftSideLabel)) {
                orderPageFrame.dispose();
                new OrderPage_StoreDocuments();
            }
            if(e.getSource().equals(middleSideLabel)) {
                orderPageFrame.dispose();
                new OrderPage_ShreddingDocuments();
            }
            if(e.getSource().equals(rightSideLabel)) {
                orderPageFrame.dispose();
                new OrderPage_ArchiveDocuments();
            }
        MyTransparentLabel[] labels2= new MyTransparentLabel[] {sideLabel1, sideLabel2};
        for (int i = 0; i < labels2.length; i++) {
            if(e.getSource().equals(sideLabel1)) {
                labels2[i].setVisible(false);
                leftSideLabel.setVisible(true);
                middleSideLabel.setVisible(true);
                rightSideLabel.setVisible(true);
            }
        }
        if(e.getSource().equals(sideLabel2)) {
            orderPageFrame.dispose();
            new PreviousOrders();
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
        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            if(e.getSource().equals(labels[i])) {
                labels[i].setBackground(new Color(184, 229, 154)); //new Color(46, 149, 169, 255) color before entered
            }
        }
        MyTransparentLabel[] labels2= new MyTransparentLabel[] {sideLabel1, sideLabel2};
        for (int i = 0; i < labels2.length; i++) {
            if(e.getSource().equals(labels2[i])) {
                labels2[i].setBackground(new Color(184, 229, 154)); //new Color(46, 149, 169, 255) color before entered
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MyTransparentLabel[] labels= new MyTransparentLabel[] {leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            if(e.getSource().equals(labels[i])) {
                labels[i].setBackground(new Color(46, 149, 169, 255));
            }
        }
        MyTransparentLabel[] labels2= new MyTransparentLabel[] {sideLabel1, sideLabel2};
        for (int i = 0; i < labels2.length; i++) {
            if(e.getSource().equals(labels2[i])) {
                labels2[i].setBackground(new Color(46, 149, 169, 255));
            }
        }
    }
} //End OrderPageSelectItem class

class OrderPage_StoreDocuments extends Component implements ActionListener, MouseListener {
    GetCurrentTime gct= new GetCurrentTime();
    GetTextFromFile gtff= new GetTextFromFile();
    String elevatorIs= "1"; // apzīmē vai ir lifts(0- nav lifta, 1- ir lifts)
    String floorA= "0";
    String currentTime= gct.getCurrentTime();
    String user_ID= gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/users_ID.txt");
    MyFrame storeDocumentsFrame= new MyFrame("Document Solutions Store Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

// components #1
    MyTransparentTextLabel rightSLabel1Text= new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea addressTextArea = new MyTextArea(20, 40, 200, 160);

// components #2
    MyTransparentTextLabel leftSLabel2Text= new MyTransparentTextLabel("Darba laiks", 60, 0, 240, 50);
    MyTextField workingTimeTextF= new MyTextField(40, 70, 160, 40);

// components #3
    MyTransparentLabel helpLabel1= new MyTransparentLabel(40, 40, 160, 70);
    MyTransparentTextLabel leftSLabel1Text= new MyTransparentTextLabel("Daudzums kastēs *", 40, 0, 240, 50);
    MyLabel helpTextLabel1= new MyLabel("VADOTIES PĒC TĀ, KA", 5, 10, 160, 15, 12);
    MyLabel helpTextLabel2= new MyLabel("VIENĀ KASTĒ SALIEN", 5, 30, 160, 15, 12);
    MyLabel helpTextLabel3= new MyLabel("5 REĢISTRA MAPES", 5, 50, 160, 15, 12);
    MyTextField boxTextF= new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText= new MyTransparentTextLabel("(- kastes)", 150, 160, 100, 40);

// components #4
    MyTransparentTextLabel middleSLabel2Text= new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea notesTextArea= new MyTextArea(20, 40, 200, 160);

// components #5
    MyTransparentTextLabel middleSLabel1Text= new MyTransparentTextLabel("Stāvs / Pagrabs", 60, 120, 240, 50);
    MyTransparentTextLabel middleSLabel1Text2= new MyTransparentTextLabel("Lifts", 100, 0, 240, 50);
    MyButton yesBtn= new MyButton("IR", 20, 40, 90, 50);
    MyButton noBtn= new MyButton("NAV", 130, 40, 90, 50);
    MyTextField floorTextF = new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2= new MyTransparentTextLabel("(- stāvi)", 150, 160, 100, 40);

// components #6
    MyButton nextBtn= new MyButton("Iesniegt", 20, 100, 200, 50);
    OrderPage_StoreDocuments() {
        System.out.println("UserIDs: ID"+ user_ID+"#001S");
// components ################################################### #1
        addressTextArea.setText("Pilsēta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        addressTextArea.setForeground(Color.GRAY);
        addressTextArea.setEditable(false);

        addressTextArea.addMouseListener(this);

        label_1.add(addressTextArea);
        label_1.add(rightSLabel1Text);
// ############################################################### #1

// components ################################################### #2
        workingTimeTextF.setText("09:00 - 17:00");
        workingTimeTextF.setEditable(false);
        workingTimeTextF.setForeground(Color.GRAY);
        workingTimeTextF.addMouseListener(this);

        label_2.add(workingTimeTextF);
        label_2.add(leftSLabel2Text);
// ############################################################### #2

// components #######§########################################### #3
// Change color of helpText
        helpTextLabel1.setForeground(Color.BLACK);
        helpTextLabel2.setForeground(Color.BLACK);
        helpTextLabel3.setForeground(Color.BLACK);
// leftSLabel1Text add Mouse Listener
        leftSLabel1Text.addMouseListener(this);
// helpLabel1 setUp
        helpLabel1.setBackground(new Color(173, 224, 203));
        helpLabel1.add(helpTextLabel1);
        helpLabel1.add(helpTextLabel2);
        helpLabel1.add(helpTextLabel3);
        helpLabel1.setVisible(false);
// leftSideLabel1 setUp
        label_3.add(boxSideText);
        label_3.add(boxTextF);
        label_3.add(leftSLabel1Text);
        label_3.add(helpLabel1);
// ############################################################### #3

// components ################################################### #4
        label_4.add(notesTextArea);
        label_4.add(middleSLabel2Text);
// ############################################################### #4

// components ################################################### #5
        floorTextF.setVisible(false);
        boxSideText2.setVisible(false);

        yesBtn.addActionListener(this);
        noBtn.addActionListener(this);

        label_5.add(floorTextF);
        label_5.add(boxSideText2);
        label_5.add(noBtn);
        label_5.add(yesBtn);
        label_5.add(middleSLabel1Text);
        label_5.add(middleSLabel1Text2);
// ############################################################### #5

// components ################################################### #6
        nextBtn.addActionListener(this);

        label_6.add(nextBtn);
// ############################################################### #6
// All labels added to storeDocumentsFrame
        MyTransparentLabel[] labels= new MyTransparentLabel[] {label_3, label_5, label_1, label_2, label_4, label_6};
        for (int i = 0; i < labels.length; i++) {
            storeDocumentsFrame.add(labels[i]);
        }
        storeDocumentsFrame.setLayout(null);
        storeDocumentsFrame.setVisible(true);
    } // End OrderPage_StoreDocuments()
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(yesBtn)) {
            floorA= "0";
            elevatorIs= "1";
            yesBtn.setBackground(new Color(141, 210, 93));
            noBtn.setBackground(new Color(184, 229, 154));
            floorTextF.setVisible(false);
            boxSideText2.setVisible(false);
        }
        if(e.getSource().equals(noBtn)) {
            elevatorIs= "0";
            floorA= floorTextF.getText();
            yesBtn.setBackground(new Color(184, 229, 154));
            noBtn.setBackground(new Color(141, 210, 93));
            floorTextF.setVisible(true);
            boxSideText2.setVisible(true);
        }
        if(e.getSource().equals(nextBtn)) {
            registerOrderP();
            new PreviousOrders();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(addressTextArea)) {
            addressTextArea.setText("");
            addressTextArea.setForeground(Color.BLACK);
            addressTextArea.setEditable(true);
        }
        if(e.getSource().equals(workingTimeTextF)) {
            workingTimeTextF.setText("");
            workingTimeTextF.setEditable(true);
            workingTimeTextF.setForeground(Color.BLACK);
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
        if(e.getSource().equals(leftSLabel1Text)) {
            helpLabel1.setVisible(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(leftSLabel1Text)) {
            helpLabel1.setVisible(false);
        }
    }

    OrderG orderG;
    private void registerOrderP() {
        String address= addressTextArea.getText();
        String notes= notesTextArea.getText();
        String time= workingTimeTextF.getText();
        String boxes= boxTextF.getText();
        String elevator= elevatorIs;
        String floor= "-1";
        String currentTime= gct.getCurrentTime();
        String userID= user_ID;

        if(elevatorIs.equals("1")) {
            floor= "0";
        } else if(elevatorIs.equals("0")) {
            floor= floorTextF.getText();
        }

        if(address.isEmpty() || boxes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adresei un Kastu skaitam jābūt aizpildītiem", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // zemāk var izveidot validācijas piem. adresē jābūt skaidram LV-0000
        orderG = addOrderSToDatabase(address, notes, time, boxes, elevator, floor, currentTime, userID);
        System.out.println("Stāvi: "+floor+" vs StāviA: "+floorA+" vs Stāvi.getText(): "+floorTextF.getText());
        if(orderG != null) {
            storeDocumentsFrame.dispose();
            new PreviousOrders();
            System.out.println("Adr: "+address+"|| Notes: "+notes+"|| WorkingTime: "+time+"|| Boxes: "+boxes+"|| Lifts: "+elevator+"|| Stāvs:  "+floor+"|| ID: "+userID+"|| Time: "+currentTime);

        }
        else {
            System.out.println(orderG);
            System.out.println("Adr: "+address+"|| Notes: "+notes+"|| WorkingTime: "+time+"|| Boxes: "+boxes+"|| Lifts: "+elevator+"|| Stāvs:  "+floor+"|| ID: "+userID+"|| Time: "+currentTime);
            JOptionPane.showMessageDialog(this, "Neizdevās izveidot pasūtījumu", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
        }
    }

    private OrderG addOrderSToDatabase(String address, String notes, String time, String boxes, String elevator, String floor, String currentTime, String userID) {
        OrderG orderG = null;
        final String DB_URL= "jdbc:mysql://localhost:3306/JAVA_IT"; //jānorāda datubāzes lokācija, kas jau iepriekš ir izveidota
        final String USERNAME= "root"; // šis ir noklusējuma username
        final String PASSWORD= "e6127609-"; // šī ir izveidotā parole iekš MySQL

        try{
            Connection connection= DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql= "INSERT INTO orders (address, notes, time, boxes, elevator, floor, date_time, userID) "+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, notes);
            preparedStatement.setString(3, time);
            preparedStatement.setString(4, boxes);
            preparedStatement.setString(5, elevator);
            preparedStatement.setString(6, floor);
            preparedStatement.setString(7, currentTime);
            preparedStatement.setString(8, userID);
//līdz šim strādā
            int addedRows= preparedStatement.executeUpdate();
            if (addedRows > 0) {
                System.out.println("Rows added strādā");
                orderG = new OrderG();
                orderG.address= address;
                orderG.notes= notes;
                orderG.time= time;
                orderG.boxes= boxes;
                orderG.elevator= elevator;
                orderG.floor= floor;
                orderG.currentTime= currentTime;
                orderG.userID= userID;
            }
            statement.close();
            connection.close();
        } catch (SQLException s) {
            if(s.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(this, "Šāds pasūtījums jau eksitē!", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderG;
    }
} //End class OrderPage_StoreDocuments

class OrderPage_ShreddingDocuments implements ActionListener, MouseListener {
    MyFrame shredDocumentsFrame= new MyFrame("Document Solutions Shredding Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5_0 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

// components #1
    MyTransparentTextLabel rightSLabel1Text= new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea boxTextArea= new MyTextArea(20, 40, 200, 160);

// components #2
    MyTransparentTextLabel needTransportText= new MyTransparentTextLabel("Nepieciešama aizvešana?", 20, 0, 240, 50);
    MyButton yesBtn1= new MyButton("JĀ!", 20, 40, 90, 50);
    MyButton noBtn1= new MyButton("NĒ!", 130, 40, 90, 50);

// components #3
    MyTransparentTextLabel leftSLabel1Text= new MyTransparentTextLabel("Aptuvenais svars", 40, 0, 240, 50);
    MyTextField boxTextF= new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText= new MyTransparentTextLabel("KG", 150, 160, 100, 40);

// components #4
    MyTransparentTextLabel middleSLabel2Text= new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea boxTextArea2= new MyTextArea(20, 40, 200, 160);

// components #5
    MyTransparentTextLabel middleSLabel1Text= new MyTransparentTextLabel("Stāvs / Pagrabs", 60, 120, 240, 50);
    MyTransparentTextLabel middleSLabel1Text2= new MyTransparentTextLabel("Lifts", 100, 0, 240, 50);
    MyButton yesBtn= new MyButton("IR", 20, 40, 90, 50);
    MyButton noBtn= new MyButton("NAV", 130, 40, 90, 50);
    MyTextField boxTextF2= new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2= new MyTransparentTextLabel("(- stāvi)", 150, 160, 100, 40);

// components #6
    MyButton nextBtn= new MyButton("Iesniegt", 20, 100, 200, 50);


    OrderPage_ShreddingDocuments() {
// components ################################################### #1
        boxTextArea.setText("Pilsēta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        boxTextArea.setForeground(Color.GRAY);
        boxTextArea.setEditable(false);

        boxTextArea.addMouseListener(this);

        label_1.add(boxTextArea);
        label_1.add(rightSLabel1Text);
// ############################################################### #1

// components ################################################### #2
        yesBtn1.addActionListener(this);
        noBtn1.addActionListener(this);

        label_2.add(noBtn1);
        label_2.add(yesBtn1);
        label_2.add(needTransportText);
// ############################################################### #2

// components ################################################### #3
// leftSLabel1Text add Mouse Listener
        leftSLabel1Text.addMouseListener(this);
// leftSideLabel1 setUp
        label_3.add(boxSideText);
        label_3.add(boxTextF);
        label_3.add(leftSLabel1Text);
// ############################################################### #3

// components ################################################### #4
        label_4.add(boxTextArea2);
        label_4.add(middleSLabel2Text);
// ############################################################### #4

// components ################################################### #5
        label_5.setVisible(false);
        boxTextF2.setVisible(false);
        boxSideText2.setVisible(false);

        yesBtn.addActionListener(this);
        noBtn.addActionListener(this);

        label_5.add(boxTextF2);
        label_5.add(boxSideText2);
        label_5.add(noBtn);
        label_5.add(yesBtn);
        label_5.add(middleSLabel1Text);
        label_5.add(middleSLabel1Text2);
// ############################################################### #5

// components ################################################### #6
        nextBtn.addActionListener(this);

        label_6.add(nextBtn);
// ############################################################### #6

        MyTransparentLabel[] labels= new MyTransparentLabel[] {label_1, label_2, label_3, label_4, label_5_0, label_5, label_6};
        for (int i = 0; i < labels.length; i++) {
            shredDocumentsFrame.add(labels[i]);
        }

        shredDocumentsFrame.setLayout(null);
        shredDocumentsFrame.setVisible(true);
    } //End OrderPage_ShreddingDocuments()
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(nextBtn)) {
            // šeit ir nepieciešams kods, kas aptver visu ievadīto info un nogādā tālāk
            shredDocumentsFrame.dispose();
            new PreviousOrders();
        }
        if(e.getSource().equals(yesBtn1)) {
            yesBtn1.setBackground(new Color(141, 210, 93));
            noBtn1.setBackground(new Color(184, 229, 154));
            label_5_0.setVisible(false);
            label_5.setVisible(true);
        }
        if(e.getSource().equals(noBtn1)) {
            yesBtn1.setBackground(new Color(184, 229, 154));
            noBtn1.setBackground(new Color(141, 210, 93));
            label_5.setVisible(false);
            label_5_0.setVisible(true);
        }
        if(e.getSource().equals(yesBtn)) {
            yesBtn.setBackground(new Color(141, 210, 93));
            noBtn.setBackground(new Color(184, 229, 154));
            boxTextF2.setVisible(false);
            boxSideText2.setVisible(false);
        }
        if(e.getSource().equals(noBtn)) {
            yesBtn.setBackground(new Color(184, 229, 154));
            noBtn.setBackground(new Color(141, 210, 93));
            boxTextF2.setVisible(true);
            boxSideText2.setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
} //End class OrderPage_ShreddingDocuments

class OrderPage_ArchiveDocuments implements ActionListener, MouseListener {
    MyFrame archiveDocumentsFrame= new MyFrame("Document Solutions Archive Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

// components #1
    MyTransparentTextLabel rightSLabel1Text= new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea boxTextArea= new MyTextArea(20, 40, 200, 160);

// components #2
    MyTransparentTextLabel leftSLabel2Text= new MyTransparentTextLabel("Darba laiks", 60, 0, 240, 50);
    MyTextField workingTimeTextF= new MyTextField(40, 70, 160, 40);

// components #3
    MyTransparentTextLabel middleSLabel1Text2= new MyTransparentTextLabel("Telpas izmēri", 50, 0, 240, 50);
    MyTextField boxTextF2= new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2= new MyTransparentTextLabel("(- m2)", 150, 160, 100, 40);

// components #4
    MyTransparentTextLabel middleSLabel2Text= new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea boxTextArea2= new MyTextArea(20, 40, 200, 160);

// components #6
    MyButton nextBtn= new MyButton("Iesniegt", 20, 100, 200, 50);
    OrderPage_ArchiveDocuments() {

// components ################################################### #1
        boxTextArea.setText("Pilsēta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        boxTextArea.setForeground(Color.GRAY);
        boxTextArea.setEditable(false);

        boxTextArea.addMouseListener(this);

        label_1.add(boxTextArea);
        label_1.add(rightSLabel1Text);
// ############################################################### #1

// components ################################################### #2
        workingTimeTextF.setText("09:00 - 17:00");
        workingTimeTextF.setEditable(false);
        workingTimeTextF.setForeground(Color.GRAY);
        workingTimeTextF.addMouseListener(this);

        label_2.add(workingTimeTextF);
        label_2.add(leftSLabel2Text);
// ############################################################### #2

// components ################################################### #5
        label_3.add(boxTextF2);
        label_3.add(boxSideText2);
        label_3.add(middleSLabel1Text2);
// ############################################################### #5

// components ################################################### #4
        label_4.add(boxTextArea2);
        label_4.add(middleSLabel2Text);
// ############################################################### #4

// components ################################################### #6
        nextBtn.addActionListener(this);

        label_6.add(nextBtn);
// ############################################################### #6


        MyTransparentLabel[] labels= new MyTransparentLabel[] {label_3, label_5, label_1, label_2, label_4, label_6};
        for (int i = 0; i < labels.length; i++) {
            archiveDocumentsFrame.add(labels[i]);
        }
        archiveDocumentsFrame.setLayout(null);
        archiveDocumentsFrame.setVisible(true);
    } //End OrderPage_ArchiveDocuments()
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(nextBtn)) {
            // šeit ir nepieciešams kods, kas aptver visu ievadīto info un nogādā tālāk
            archiveDocumentsFrame.dispose();
            new PreviousOrders();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

} //End class OrderPage_ArchiveDocuments

class PreviousOrders implements ActionListener, MouseListener {
    GetTextFromFile gtff= new GetTextFromFile();
    GetLastOrdersInfo gloi= new GetLastOrdersInfo();
    GetOrdersInfo goi= new GetOrdersInfo();
    GetOrderPrice gop= new GetOrderPrice();
    String currentTime, address, time, boxes, elevator, floor, notes, orderType, currentUser, currentOrderID;

    MyFrame previousOrdersFrame= new MyFrame("Document Solutions Previous Orders Page");
    MyLabel topTextLabel= new MyLabel("*TEST* 23.06.2022 (Glabāšana)", 300, 10, 400, 30, 30);
    MyLabel addressTextLabel= new MyLabel("*TEST* Adrese: "+"Rīga, Jasmuižas iela 3, 3, LV-1004", 200, 60, 500, 20, 18);
    MyLabel addressTextLabel2= new MyLabel("", 200, 90, 500, 20, 18);
    MyLabel timeTextLabel= new MyLabel("*TEST* Darba laiks: "+"09:00- 19:30", 200, 120, 500, 20, 18);
    MyLabel boxesTextLabel= new MyLabel("*TEST* Kastes: "+"400"+" GB", 200, 150, 500, 20, 18);
    MyLabel lvlTextLabel= new MyLabel("*TEST* Stāvs: "+"0", 200, 180, 500, 20, 18);
    MyLabel notesTextLabel= new MyLabel("*TEST* Piezīmes: "+"Piebtraukt var no Cēsu ielas......", 200, 210, 550, 20, 18);
    MyLabel notesTextLabel2= new MyLabel("", 200, 240, 550, 20, 18);
    MyLabel notesTextLabel3= new MyLabel("", 200, 280, 550, 20, 18);
    MyLabel statussTextLabel= new MyLabel("*TEST* Statuss : "+"GAIDA", 200, 320, 500, 20, 18);
    MyLabel totalTextLabel= new MyLabel("*TEST* Kopā: "+"235,0 Eiro + PVN 21%", 200, 360, 500, 30, 30);
    MyLabel monthTextLabel= new MyLabel("*TEST* *Mēneša maksa: "+"90,0 Eiro + PVN 21%", 200, 400, 500, 20, 18);
    PreviousOrders() {
        currentUser= gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/users_ID.txt");
        currentOrderID= gloi.getLastOrdersInfo(currentUser, "orderID", "orderID");
        currentTime= goi.getOrdersInfo(currentOrderID, "date_time");
        address= goi.getOrdersInfo(currentOrderID, "address");
        time= goi.getOrdersInfo(currentOrderID, "time");
        boxes= goi.getOrdersInfo(currentOrderID, "boxes");
        elevator= goi.getOrdersInfo(currentOrderID, "elevator");
        floor= goi.getOrdersInfo(currentOrderID, "floor");
        notes= goi.getOrdersInfo(currentOrderID, "notes");
        orderType= " (Glabāšana)";

        topTextLabel.setText(currentTime+orderType);
        if(address.length() > 50 && address.length() <= 100) {
            addressTextLabel.setText("Adrese: "+address.substring(0, 50));
            addressTextLabel2.setText("            "+address.substring(50, address.length()-1));
        } else if(address.length() <= 50) {
            addressTextLabel.setText("Adrese: "+address);
        }
        timeTextLabel.setText("Darba laiks: "+time);
        boxesTextLabel.setText("Kastes(gb): "+boxes);
        if(elevator.equals("1")) {
            lvlTextLabel.setText("Lifts: IR");
        } else if(elevator.equals("0")) {
            lvlTextLabel.setText("Stāvs: "+floor+" (Lifta NAV!)");
        }

        if(notes.length() > 50 && notes.length() <= 100) {
            notesTextLabel.setText("Piezīmes: "+notes.substring(0, 50));
            notesTextLabel2.setText("                "+notes.substring(50, notes.length()-1));
        } else if(notes.length() > 100) {
            notesTextLabel.setText("Piezīmes: "+notes.substring(0, 50));
            notesTextLabel2.setText("                "+notes.substring(50, 100));
            notesTextLabel3.setText("                "+notes.substring(100, notes.length()-1));
        } else if(notes.length() <= 50){
            notesTextLabel.setText("Piezīmes: "+notes);
        }
        totalTextLabel.setText("Kopā: "+gop.getOrderPrice(boxes, floor)+"€ + PVN 21%");
        monthTextLabel.setText("*Mēneša maksa: "+Integer.parseInt(boxes)* 0.6+"€ + PVN 21%");

        previousOrdersFrame.add(monthTextLabel);
        previousOrdersFrame.add(totalTextLabel);
        previousOrdersFrame.add(statussTextLabel);
        previousOrdersFrame.add(notesTextLabel3);
        previousOrdersFrame.add(notesTextLabel2);
        previousOrdersFrame.add(notesTextLabel);
        previousOrdersFrame.add(lvlTextLabel);
        previousOrdersFrame.add(boxesTextLabel);
        previousOrdersFrame.add(timeTextLabel);
        previousOrdersFrame.add(addressTextLabel2);
        previousOrdersFrame.add(addressTextLabel);
        previousOrdersFrame.add(topTextLabel);
        previousOrdersFrame.setLayout(null);
        previousOrdersFrame.setVisible(true);
    } //End PreviousOrders()

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
} //End class PreviousOrders

class AdministratorPage implements ActionListener {
    WriteTextToFile wttf= new WriteTextToFile();
    GetTextFromFile gtff= new GetTextFromFile();
    GetOrderPrice gop= new GetOrderPrice();

    MyLabel topTextLabel= new MyLabel("", 300, 10, 400, 30, 20);
    MyLabel addressTextLabel= new MyLabel("Adrese: ", 250, 50, 500, 20, 16);
    MyLabel addressTextLabel2= new MyLabel("", 250, 70, 500, 20, 16);
    MyLabel timeTextLabel= new MyLabel("Darba laiks: ", 250, 90, 500, 20, 16);
    MyLabel boxesTextLabel= new MyLabel("Kastes: ", 250, 110, 500, 20, 16);
    MyLabel lvlTextLabel= new MyLabel("Stāvs: ", 250, 130, 500, 20, 16);
    MyLabel notesTextLabel= new MyLabel("Piezīmes: ", 250, 150, 550, 20, 16);
    MyLabel notesTextLabel2= new MyLabel("", 250, 170, 550, 20, 16);
    MyLabel notesTextLabel3= new MyLabel("", 250, 190, 550, 20, 16);
    MyLabel totalTextLabel= new MyLabel("Kopā: ", 250, 250, 500, 30, 20);
    MyLabel monthTextLabel= new MyLabel("*Mēneša maksa: ", 250, 290, 500, 20, 16);

    MyFrame adminPageFrame= new MyFrame("Administrator Page");
    MyTransparentLabel mainLabel= new MyTransparentLabel(65, 100, 770, 500);

    MyComboBox usersBox= new MyComboBox("UserID", 50, 20, 150, 50);
    MyComboBox ordersBox= new MyComboBox("OrderID", 50, 145, 150, 50);

    MyButton setUserBtn= new MyButton("ORDERS", 25, 80, 200, 50);
    MyButton showOrderBtn= new MyButton("SHOW", 25, 205, 200, 50);


    String orderNr= "", userNr= "", orderFromList= "", addressFromList= "", notesFromList= "",
            timeFromList= "", boxesFromList= "", elevatorFromList= "", floorFromList= "", dateFromList= "", userID= "";

    String[] listsArray= new String[] {orderFromList, addressFromList, notesFromList, timeFromList, boxesFromList, elevatorFromList, floorFromList, dateFromList, userID};
    String[] databaseTitlesArray = new String[] {"OrderID", "address", "notes", "time", "boxes", "elevator", "floor", "date_time", "UserID"};
    int tableSize= Integer.parseInt(gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/userTableSize.txt"));
    ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
    String ordersArray[] = new String[tableSize]; // glabās visus lietotāja ID
    AdministratorPage() {

        showOrderBtn.addActionListener(this);
        setUserBtn.addActionListener(this);
        ordersBox.addActionListener(this);
        usersBox.addActionListener(this);
        ordersBox.setVisible(false);
        showOrderBtn.setVisible(false);
        setUserBtn.setVisible(false);
        mainLabel.add(ordersBox);
        mainLabel.add(usersBox);
        mainLabel.add(setUserBtn);
        mainLabel.add(showOrderBtn);
        mainLabel.add(topTextLabel);
        mainLabel.add(addressTextLabel);
        mainLabel.add(addressTextLabel2);
        mainLabel.add(timeTextLabel);
        mainLabel.add(boxesTextLabel);
        mainLabel.add(lvlTextLabel);
        mainLabel.add(notesTextLabel);
        mainLabel.add(notesTextLabel2);
        mainLabel.add(notesTextLabel3);
        mainLabel.add(totalTextLabel);
        mainLabel.add(monthTextLabel);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT userId FROM users ORDER BY userID");
            while (resultSet.next()) {
                usersBox.addItem(resultSet.getString("userID"));
                ArrayList<String> inner = new ArrayList<String>();
                inner.add(resultSet.getString("userID"));
                outer.add(inner);
            }
            // saliekam visu parastajā Array
            for(int j =0;j<outer.size();j++){
                ordersArray[j] = String.valueOf(outer.get(j));
            }
            System.out.println(Arrays.toString(ordersArray));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        adminPageFrame.add(mainLabel);
        adminPageFrame.setLayout(null);
        adminPageFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(usersBox)) {
            usersBox.removeItem("UserID");
            setUserBtn.setVisible(true);
            ordersBox.removeAllItems();

            try { // saskaita cik kopā ir klientu ir saglabāti datubāzē
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
                Statement statement = connection.createStatement();
                ResultSet getColumnSize = statement.executeQuery("SELECT COUNT(*) FROM users");
                while (getColumnSize.next()) {
                    tableSize= Integer.parseInt(getColumnSize.getString("COUNT(*)"));
                    wttf.writeTextToFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/userTableSize.txt", String.valueOf(tableSize));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource().equals(setUserBtn)) { // uzspiežot ORDERS pogu...
            userNr= (String) usersBox.getSelectedItem(); // userNr glabā izvēlētā lietotāja UserID
            System.out.println("ID= "+userNr);

            try { //izveido dropdown sarakstu ar orderiem pēc lietotāja ID
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT orderId FROM orders WHERE UserID="+userNr+" ORDER BY orderID");
                while (resultSet.next()) {
                    ordersBox.addItem(resultSet.getString("orderID"));
                    /*if(resultSet.getString("orderID").length() == 1) {
                        ordersBox.addItem("# = 00" + resultSet.getString("orderID"));
                    } else if(resultSet.getString("orderID").length() == 2) {
                        ordersBox.addItem("# = 0" + resultSet.getString("orderID"));
                    } else if(resultSet.getString("orderID").length() == 3) {
                        ordersBox.addItem("# = " + resultSet.getString("orderID"));
                    }*/
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ordersBox.setVisible(true);
            showOrderBtn.setVisible(true);

        }
        if(e.getSource().equals(showOrderBtn)) { //uzspieržot SHOW pogu...
            orderNr= (String) ordersBox.getSelectedItem(); // orderNr glabā izvēlētā lietotāja OrderID
            System.out.println("#"+orderNr);

            try { // piepilda listsArray ar vērtībām
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE OrderID="+orderNr);
                while (resultSet.next()) {
                    for (int i = 0; i < listsArray.length; i++) {
                        listsArray[i]= resultSet.getString(databaseTitlesArray[i]);
                    }
                    orderFromList= listsArray[0];
                    addressFromList= listsArray[1];
                    notesFromList= listsArray[2];
                    timeFromList= listsArray[3];
                    boxesFromList= listsArray[4];
                    elevatorFromList= listsArray[5];
                    floorFromList= listsArray[6];
                    dateFromList= listsArray[7];
                    userID= listsArray[8];
                    System.out.println(Arrays.toString(listsArray));
                    topTextLabel.setText("ID= "+userID+" #00"+orderNr+" (Glabāšana) "+dateFromList);
                    if(addressFromList.length() > 60 && addressFromList.length() <= 120) {
                        addressTextLabel.setText("Adrese: "+addressFromList.substring(0, 60));
                        addressTextLabel2.setText("            "+addressFromList.substring(60, addressFromList.length()-1));
                    } else if(addressFromList.length() <= 60) {
                        addressTextLabel.setText("Adrese: "+addressFromList);
                        addressTextLabel2.setText("");
                    }
                    timeTextLabel.setText("Darba laiks: "+timeFromList);
                    boxesTextLabel.setText("Kastes(gb): "+boxesFromList);
                    if(elevatorFromList.equals("1")) {
                        lvlTextLabel.setText("Lifts: IR");
                    } else if(elevatorFromList.equals("0")) {
                        lvlTextLabel.setText("Stāvs: "+floorFromList+" (Lifta NAV!)");
                    }

                    if(notesFromList.length() > 60 && notesFromList.length() <= 120) {
                        notesTextLabel.setText("Piezīmes: "+notesFromList.substring(0, 60));
                        notesTextLabel2.setText("                "+notesFromList.substring(60, notesFromList.length()-1));
                        notesTextLabel3.setText("");
                    } else if(notesFromList.length() > 120) {
                        notesTextLabel.setText("Piezīmes: "+notesFromList.substring(0, 60));
                        notesTextLabel2.setText("                "+notesFromList.substring(60, 120));
                        notesTextLabel3.setText("                "+notesFromList.substring(120, notesFromList.length()-1));
                    } else if(notesFromList.length() <= 60){
                        notesTextLabel.setText("Piezīmes: "+notesFromList);
                        notesTextLabel2.setText("");
                        notesTextLabel3.setText("");
                    }
                    totalTextLabel.setText("Kopā: "+gop.getOrderPrice(boxesFromList, floorFromList)+"€ + PVN 21%");
                    monthTextLabel.setText("*Mēneša maksa: "+Integer.parseInt(boxesFromList)* 0.6+"€ + PVN 21%");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource().equals(ordersBox)) {
            ordersBox.removeItem("OrderID");
            /*try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT orderId FROM orders ORDER BY orderID");
                while (resultSet.next()) {
                    ordersBox.addItem(resultSet.getString("orderID"));
                    *//*if(resultSet.getString("orderID").length() == 1) {
                        ordersBox.addItem("# = 00" + resultSet.getString("orderID"));
                    } else if(resultSet.getString("orderID").length() == 2) {
                        ordersBox.addItem("# = 0" + resultSet.getString("orderID"));
                    } else if(resultSet.getString("orderID").length() == 3) {
                        ordersBox.addItem("# = " + resultSet.getString("orderID"));
                    }*//*
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }*/
        }
    }
}
