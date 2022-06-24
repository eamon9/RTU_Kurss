package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.*;
import RTU_JAVA_kurss.Extensions.*;
import RTU_JAVA_kurss.YouNeedThis.GetLastOrdersInfo;
import RTU_JAVA_kurss.YouNeedThis.GetTextFromFile;
import RTU_JAVA_kurss.YouNeedThis.GetUserInfo;
import RTU_JAVA_kurss.YouNeedThis.WriteTextToFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage extends Component implements ActionListener, MouseListener {
    WriteTextToFile wttf = new WriteTextToFile();
    GetTextFromFile gtff = new GetTextFromFile();
    GetUserInfo gui = new GetUserInfo();
    GetLastOrdersInfo gloi = new GetLastOrdersInfo();
    MyFrame loginPageFrame = new MyFrame("Document Solutions Login Page");
    MyTransparentTextLabel registeredUserLabel = new MyTransparentTextLabel("Esmu reģistrēts lietotājs", 85, 60, 250, 50);
    MyTransparentTextLabel notRegisteredUserLabel = new MyTransparentTextLabel("Jums nav lietotāja profila?", 75, 60, 250, 50);
    MyTransparentLabel leftSideLabel = new MyTransparentLabel(65, 100, 365, 500);
    MyTransparentLabel rightSideLabel = new MyTransparentLabel(480, 100, 350, 500);
    MyTextField mailTextField = new MyTextField("Jūsu e-pasta adrese", 60, 140, 250, 45, true);
    MyPasswordField passwordField = new MyPasswordField(60, 220, 250, 45);
    MyTextField fakePasswordField = new MyTextField("Parole", 60, 220, 250, 45, true);
    MyButton loginBtn = new MyButton("Pieslēgties", 80, 300, 200, 50);
    MyButton registrationBtn = new MyButton("Reģistrēties", 80, 140, 200, 50);
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
        if (e.getSource().equals(loginBtn)) {
            String mail = mailTextField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (mail.equals("info@documentsolutions.lv") && password.equals("administrator")) {
                loginPageFrame.dispose();
                new AdministratorPage();
            } else {

                user = getAuthenticUser(mail, password);

                if (user != null) {
                    String sourceFolder = "/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/";
                    System.out.println("Sveiks " + user.name + "!");
                    wttf.writeTextToFile(sourceFolder + "users_ID.txt", user.UserID);
                    String userIDs = gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/users_ID.txt");
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
                    System.out.println("Pēdējā pasūtījuma ID: " + gloi.getLastOrdersInfo(userIDs, "orderID", "orderID"));

                    loginPageFrame.dispose();
                    new OrderPageSelectItem();
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Lietotājvārds un/vai parole nepareiza", "Mēģiniet vēlreiz!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        // !!!!!!!  Projektam turbojet beigām, šeit jāsasaista ar datubāzi,
        // kas pārbauda, vai ir šāds lietotājvārds ar attiecīgo paroli
        // !!!!!!!                                     !!!!!!!!!!!!!!!!!!!!!!
        if (e.getSource().equals(loginBtn)) {
            loginBtn.setBackground(new Color(141, 210, 93));
            registrationBtn.setBackground(new Color(184, 229, 154));
        } //End if else statement
        if (e.getSource().equals(registrationBtn)) {
            registrationBtn.setBackground(new Color(141, 210, 93));
            loginBtn.setBackground(new Color(184, 229, 154));
            loginPageFrame.dispose();
            new RegistrationPage();
        }

    } //End actionPerformed()

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(mailTextField) && (mailTextField.getText().equals("Jūsu e-pasta adrese"))) {
            mailTextField.setEditable(true);
            mailTextField.setForeground(Color.BLACK);
            mailTextField.setText("");
            fakePasswordField.setVisible(false);
            passwordField.setVisible(true);
        }
        if (e.getSource().equals(fakePasswordField)) {
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
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/JAVA_IT";
        final String USERNAME = "root";
        final String PASSWORD = "e6127609-";

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            connection.createStatement();
            String sql = "SELECT * FROM users WHERE mail=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, mail);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.UserID = resultSet.getString("UserID");
                user.name = resultSet.getString("name");
                user.surname = resultSet.getString("surname");
                user.mail = resultSet.getString("mail");
                user.password = resultSet.getString("password");
                user.mobile = resultSet.getString("mobile");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

} //End LoginPage class
