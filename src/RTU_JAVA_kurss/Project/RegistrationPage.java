package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.Extensions.*;
import RTU_JAVA_kurss.User;
import RTU_JAVA_kurss.YouNeedThis.MySQLConnection.GetUsersCount;
import RTU_JAVA_kurss.YouNeedThis.TxtFileConnection.GetTextFromFile;
import RTU_JAVA_kurss.YouNeedThis.TxtFileConnection.WriteTextToFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationPage extends Component implements ActionListener {
    GetUsersCount guc= new GetUsersCount();
    GetTextFromFile gtff = new GetTextFromFile();
    WriteTextToFile wttf = new WriteTextToFile();

    MyFrame registrationPageFrame = new MyFrame("Document Solutions registration Form");
    MyTransparentLabel mainLabel = new MyTransparentLabel(65, 100, 770, 500);
    MyRadioButton individualRadioBtn = new MyRadioButton("Privātpersona", 50, 10, 200, 50);
    MyRadioButton juridicalRadioBtn = new MyRadioButton("Juridiska persona", 50, 70, 200, 50);
    ButtonGroup personGroup;
    MyButton backBtn = new MyButton("< Atpakaļ", 290, 50, 200, 50);
    MyButton nextBtn = new MyButton(" Turpināt >", 510, 50, 200, 50);
    MyLabel nameLabel = new MyLabel("Vārds", 60, 120, 200, 50);
    MyLabel surnameLabel = new MyLabel("Uzvārds", 220, 120, 200, 50);
    MyLabel mailLabel = new MyLabel("E-pasts", 380, 120, 300, 50);
    MyLabel mobNrLabel = new MyLabel("Mob. Tālrunis", 60, 200, 200, 50);
    MyLabel passwordLabel = new MyLabel("Parole", 280, 200, 200, 50);
    MyLabel repeatPasswordLabel = new MyLabel("Atkārtojiet Paroli", 500, 200, 200, 50);

    MyLabel companyNameLabel = new MyLabel("Uzņēmuma nosaukums", 60, 280, 200, 50);
    MyLabel companyRegLabel = new MyLabel("Uzņēmuma reģ. Nr.", 380, 280, 200, 50);
    MyLabel pvnLabel = new MyLabel("PVN reģ. NR.", 540, 280, 200, 50);
    MyLabel bankLabel = new MyLabel("Banka, kods(SWIFT)", 540, 360, 200, 50);
    MyLabel companyAdressLabel = new MyLabel("Uzņēmuma adrese", 60, 360, 200, 50);
    MyLabel ibanLabel = new MyLabel("IBAN konts", 380, 360, 200, 50);

    MyTextField nameTF = new MyTextField(60, 160, 150, 40);
    MyTextField surnameTF = new MyTextField(220, 160, 150, 40);
    MyTextField mailTF = new MyTextField(380, 160, 330, 40);
    MyTextField mobNrTF = new MyTextField(60, 240, 210, 40);

    MyPasswordField passwordTF = new MyPasswordField(280, 240, 210, 40);
    MyPasswordField repeatPasswordTF = new MyPasswordField(500, 240, 210, 40);

    MyTextField companyNameTF = new MyTextField(60, 320, 310, 40);
    MyTextField companyRegTF = new MyTextField(380, 320, 150, 40);
    MyTextField pvnTF = new MyTextField(540, 320, 170, 40);

    MyTextField companyAdressTF = new MyTextField(60, 400, 310, 40);
    MyTextField ibanTF = new MyTextField(380, 400, 150, 40);
    MyTextField bankTF = new MyTextField(540, 400, 170, 40);

    MyLabel[] labelsForJuridical = new MyLabel[]{companyNameLabel, companyRegLabel, pvnLabel, bankLabel, companyAdressLabel, ibanLabel};
    MyTextField[] juridicalTF = new MyTextField[]{companyNameTF, companyRegTF, pvnTF, bankTF, companyAdressTF, ibanTF};

    String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);

    public RegistrationPage() {
        personGroup = new ButtonGroup(); // grupēšana nepieciešama, lai varētu atzīmēt tikai vienu no izvēlētajiem variantiem
        personGroup.add(individualRadioBtn);
        personGroup.add(juridicalRadioBtn);
        // visi 'label' priekš juridiskās daļas, kam noklusējumā jābūt slēptiem
        for (int i = 0; i < labelsForJuridical.length; i++) {
            labelsForJuridical[i].setVisible(false);
            mainLabel.add(labelsForJuridical[i]);
        }
        // visi 'label' priekš privātpersonas daļas, kam noklusējumā jābūt redzamiem
        MyLabel[] labelsForIndividual = new MyLabel[]{mailLabel, mobNrLabel, surnameLabel, nameLabel};
        for (int i = 0; i < labelsForIndividual.length; i++) {
            mainLabel.add(labelsForIndividual[i]);
        }
        // visi 'TextField' priekš juridiskās daļas, kam noklusējumā jābūt slēptiem un tiek pievienoti mainLabel klasei ar .add() Metodi
        for (int i = 0; i < juridicalTF.length; i++) {
            juridicalTF[i].setVisible(false);
            mainLabel.add(juridicalTF[i]);
        }
        // visi 'TextField' priekš privātpersonu daļas tiek pievienoti mainLabel klasei ar Metodi .add();
        MyTextField[] individualTF = new MyTextField[]{mobNrTF, mailTF, surnameTF, nameTF};
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
        if (e.getSource().equals(backBtn)) {
            backBtn.setBackground(new Color(141, 210, 93));
            nextBtn.setBackground(new Color(184, 229, 154));
            registrationPageFrame.dispose();
            new LoginPage();
        }
        if (e.getSource().equals(nextBtn)) {
            nextBtn.setBackground(new Color(141, 210, 93));
            backBtn.setBackground(new Color(184, 229, 154));
            wttf.writeTextToFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/uEmail.txt", mailTF.getText());
            registerUser();
        }
        if (e.getSource().equals(individualRadioBtn)) {
            for (int i = 0; i < labelsForJuridical.length; i++) {
                labelsForJuridical[i].setVisible(false);
            }
            for (int i = 0; i < juridicalTF.length; i++) {
                juridicalTF[i].setVisible(false);
            }
        }
        if (e.getSource().equals((juridicalRadioBtn))) {
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
        String name = nameTF.getText().substring(0,1).toUpperCase()+nameTF.getText().substring(1);
        String surname = surnameTF.getText().substring(0,1).toUpperCase()+surnameTF.getText().substring(1);
        String mail = mailTF.getText();
        String mobile = mobNrTF.getText();
        String password = String.valueOf(passwordTF.getPassword());
        String repeatPassword= String.valueOf(repeatPasswordTF.getPassword());

        Matcher matcher= pattern.matcher(mail);

        if (name.isEmpty() || surname.isEmpty() || mail.isEmpty() || mobile.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Lūdzu aizpildiet visus laukus", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (matcher.matches()!=true) {
            JOptionPane.showMessageDialog(this, "Ievadiet derīgu e-pastu!", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(this, "Paroles nesakrīt!", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Jūsu izveidotā parole ir par mazu" + "\n" + "MIN 6simboli", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!mobile.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Mobīlais telefons jānorāda skaitļos!", "Mēģini vēlreiz!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!mobile.substring(0,1).equals("2") && !mobile.substring(0,1).equals("6")) {
            JOptionPane.showMessageDialog(this, "Telefona nr. jābūt reģistrētam LV", "Mēģini vēlreiz!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mobile.length()!=8) {
            JOptionPane.showMessageDialog(this, "Telefona nr. jābūt 8ciparu garam!", "Mēģini vēlreiz!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(name, surname, mail, password, mobile);
        if (user != null) {
            registrationPageFrame.dispose();
            LoginPage loginPage = new LoginPage();
            loginPage.mailTextField.setText(gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/uEmail.txt")); //ievada esošo reģistrēto e-pastu
            loginPage.mailTextField.setEditable(true);
            loginPage.fakePasswordField.setVisible(false);
            loginPage.passwordField.setVisible(true);
            wttf.writeTextToFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/userTableSize.txt", guc.getUsersCount());

        } else {
            JOptionPane.showMessageDialog(this, "Neizdevās izveidot jaunu lietotāju", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
        }
    }

    private User addUserToDatabase(String name, String surname, String mail, String password, String mobile) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/JAVA_IT"; //jānorāda datubāzes lokācija, kas jau iepriekš ir izveidota
        final String USERNAME = "root"; // šis ir noklusējuma username
        final String PASSWORD = "e6127609-"; // šī ir izveidotā parole iekš MySQL

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "INSERT INTO users (name, surname, mail, password, mobile) " + "VALUES (?, ?, ?, ?, ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, mail);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, mobile);

            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.surname = surname;
                user.mail = mail;
                user.password = password;
                user.mobile = mobile;
            }
            statement.close();
            connection.close();
        } catch (SQLException s) {
            if (s.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(this, "Šāds telefons/ e-pasts jau eksitē!", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

} //End RegistrationPage class
