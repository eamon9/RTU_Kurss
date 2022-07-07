package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.MyExtensions.*;
import RTU_JAVA_kurss.YouNeedThis.GetMonthPrise;
import RTU_JAVA_kurss.YouNeedThis.GetOrderPrice;
import RTU_JAVA_kurss.YouNeedThis.MySQLConnection.GetColumnsTextFromOrder;
import RTU_JAVA_kurss.YouNeedThis.MySQLConnection.GetUsersCount;
import RTU_JAVA_kurss.YouNeedThis.MySQLConnection.UpdateDatabase;
import RTU_JAVA_kurss.YouNeedThis.TxtFileConnection.WriteTextToFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class AdministratorPage implements ActionListener {
    MyColor myColor = new MyColor();
    GetUsersCount guc = new GetUsersCount();
    GetMonthPrise gmp = new GetMonthPrise();
    GetColumnsTextFromOrder gcto = new GetColumnsTextFromOrder();
    UpdateDatabase ud = new UpdateDatabase();
    WriteTextToFile wttf = new WriteTextToFile();
    GetOrderPrice gop = new GetOrderPrice();

    MyFrame adminPageFrame = new MyFrame("Administrator Page");
    MyTransparentLabel mainLabel = new MyTransparentLabel(65, 100, 770, 500);

    MyLabel topTextLabel = new MyLabel("", 300, 10, 400, 30, 20);
    MyTextArea addressTextArea = new MyTextArea(530, 40, 220, 150);
    MyLabel workingHoursTextLabel = new MyLabel("Darba laiks: ", 250, 40, 500, 20, 16);
    MyLabel boxesTextLabel = new MyLabel("Kastes: ", 250, 60, 500, 20, 16);
    MyLabel lvlTextLabel = new MyLabel("Stāvs: ", 250, 80, 500, 20, 16);
    MyTextArea notesTextArea = new MyTextArea(530, 200, 220, 150);
    MyLabel totalTextLabel = new MyLabel("Kopā: ", 250, 250, 500, 30, 20);
    MyLabel monthTextLabel = new MyLabel("*Mēneša maksa: ", 250, 290, 500, 20, 16);
    MyLabel statussTextLabel = new MyLabel("Statuss: ", 250, 310, 500, 20, 16);

    MyComboBox usersBox = new MyComboBox("UserID", 50, 20, 150, 50);
    MyComboBox ordersBox = new MyComboBox("OrderID", 50, 145, 150, 50);

    MyButton setUserBtn = new MyButton("Pieteikumi", 25, 80, 200, 50);
    MyButton showOrderBtn = new MyButton("Parādīt", 25, 205, 200, 50);
    MyButton acceptBtn = new MyButton("Apstiprināt", 25, 305, 200, 50);
    MyButton declineBtn = new MyButton("Atteikt", 25, 405, 200, 50);
    MyButton submitBtn = new MyButton("Sūtīt", 545, 405, 200, 50);

    MyTextArea noteTxt = new MyTextArea(245, 355, 280, 100);
    MyTextArea noteTxtAreaFromDB = new MyTextArea(245, 355, 280, 100);

    boolean statuss;
    String orderNr = "", userNr = "", orderFromList = "", addressFromList = "", notesFromList = "", statussFromDB = "", noteFromDB = "",
            timeFromList = "", boxesFromList = "", elevatorFromList = "", floorFromList = "", dateFromList = "", userID = "", addressText = "Adrese: ", notesText = "Piezīmes: ";

    String[] listsArray = new String[]{orderFromList, addressFromList, notesFromList, timeFromList, boxesFromList, elevatorFromList, floorFromList, dateFromList, userID};
    String[] databaseTitlesArray = new String[]{"OrderID", "address", "notes", "time", "boxes", "elevator", "floor", "date_time", "UserID"};
    int tableSize = Integer.parseInt(guc.getUsersCount());

    ArrayList<ArrayList<String>> outer = new ArrayList<>();
    String[] ordersArray = new String[tableSize]; // glabās visus lietotāja ID

    public AdministratorPage() {
        submitBtn.addActionListener(this);
        declineBtn.addActionListener(this);
        acceptBtn.addActionListener(this);
        showOrderBtn.addActionListener(this);
        setUserBtn.addActionListener(this);
        ordersBox.addActionListener(this);
        usersBox.addActionListener(this);

        ordersBox.setVisible(false);
        showOrderBtn.setVisible(false);
        setUserBtn.setVisible(false);
        acceptBtn.setVisible(false);
        declineBtn.setVisible(false);
        submitBtn.setVisible(false);
        noteTxt.setVisible(false);
        noteTxtAreaFromDB.setVisible(false);
        statussTextLabel.setVisible(false);

        declineBtn.setBackground(myColor.BTN_DECLINE); //(c.myColor("BTN_DECLINE_PRESS"));

        addressTextArea.setEditable(false);
        addressTextArea.setText(addressText);
        addressTextArea.setForeground(myColor.TXT_GREEN);
        addressTextArea.setBackground(myColor.LABEL_TRANSPARENT);
        addressTextArea.setFont(new Font("Times New Roman", Font.BOLD, 16));
        notesTextArea.setEditable(false);
        notesTextArea.setText(notesText);
        notesTextArea.setForeground(myColor.TXT_GREEN);
        notesTextArea.setBackground(myColor.LABEL_TRANSPARENT);
        notesTextArea.setFont(new Font("Times New Roman", Font.BOLD, 16));

        Object[] needToAdd_Array= {
                statussTextLabel, noteTxtAreaFromDB, noteTxt, ordersBox, usersBox, submitBtn,
                declineBtn, acceptBtn, setUserBtn, showOrderBtn, topTextLabel, addressTextArea,
                workingHoursTextLabel, boxesTextLabel, lvlTextLabel, notesTextArea,
                totalTextLabel, monthTextLabel};

        for (int i = 0; i < needToAdd_Array.length; i++) {
            mainLabel.add((Component) needToAdd_Array[i]);
        }

        // no datu bāzes izņem visus lietotāja ID un ievieto Array
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT userId FROM users ORDER BY userID");
            while (resultSet.next()) {
                usersBox.addItem(resultSet.getString("userID"));
                ArrayList<String> inner = new ArrayList<>();
                inner.add(resultSet.getString("userID"));
                outer.add(inner);
            }
            // saliekam visu parastajā Array
            for (int j = 0; j < outer.size(); j++) {
                ordersArray[j] = String.valueOf(outer.get(j));
            }
            System.out.println(Arrays.toString(ordersArray));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        adminPageFrame.add(mainLabel);
        adminPageFrame.setLayout(null);
        adminPageFrame.setVisible(true);
        noteTxtAreaFromDB.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(usersBox)) {
            usersBox.removeItem("UserID");
            setUserBtn.setVisible(true);
            ordersBox.removeAllItems();

            //šim vajag izmantot funkciju GetCount...
            try { // saskaita cik kopā ir klientu ir saglabāti datubāzē
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "");
                Statement statement = connection.createStatement();
                ResultSet getColumnSize = statement.executeQuery("SELECT COUNT(*) FROM users");
                while (getColumnSize.next()) {
                    tableSize = Integer.parseInt(getColumnSize.getString("COUNT(*)"));
                    wttf.writeTextToFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/userTableSize.txt", String.valueOf(tableSize));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource().equals(setUserBtn)) { // uzspiežot ORDERS pogu...
            setUserBtn.setBackground(myColor.BTN_PRESS);
            showOrderBtn.setBackground(myColor.BTN);
            acceptBtn.setVisible(false);
            declineBtn.setVisible(false);
            noteTxt.setVisible(false);
            submitBtn.setVisible(false);
            userNr = (String) usersBox.getSelectedItem(); // userNr glabā izvēlētā lietotāja UserID
            System.out.println("ID= " + userNr);

            try { //izveido dropdown sarakstu ar orderiem pēc lietotāja ID
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT orderId FROM orders WHERE UserID=" + userNr + " ORDER BY orderID");
                while (resultSet.next()) {
                    ordersBox.addItem(resultSet.getString("orderID"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            ordersBox.setVisible(true);
            showOrderBtn.setVisible(true);
            noteTxtAreaFromDB.setVisible(false);
            statussTextLabel.setVisible(false);

        }

        if (e.getSource().equals(ordersBox)) {
            ordersBox.removeItem("OrderID");
        }

        //showOrderBtn action(with database connection) and design
        if (e.getSource().equals(showOrderBtn)) { //uzspieržot SHOW pogu...
            //noteTxtFromDB.setText();
            showOrderBtn.setBackground(myColor.BTN_PRESS);
            setUserBtn.setBackground(myColor.BTN);
            acceptBtn.setBackground(myColor.BTN);
            declineBtn.setBackground(myColor.BTN_DECLINE);
            acceptBtn.setVisible(true);
            declineBtn.setVisible(true);
            noteTxt.setVisible(false);
            submitBtn.setVisible(false);

            orderNr = (String) ordersBox.getSelectedItem(); // orderNr glabā izvēlētā lietotāja OrderID
            System.out.println("#" + orderNr);

            try { // piepilda listsArray ar vērtībām
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE OrderID=" + orderNr);
                while (resultSet.next()) {
                    for (int i = 0; i < listsArray.length; i++) {
                        listsArray[i] = resultSet.getString(databaseTitlesArray[i]);
                    }
                    orderFromList = listsArray[0];
                    addressFromList = listsArray[1];
                    notesFromList = listsArray[2];
                    timeFromList = listsArray[3];
                    boxesFromList = listsArray[4];
                    elevatorFromList = listsArray[5];
                    floorFromList = listsArray[6];
                    dateFromList = listsArray[7];
                    userID = listsArray[8];
                    topTextLabel.setText("ID= " + userID + " #00" + orderNr + " (Glabāšana) " + dateFromList);
                    addressTextArea.setText(addressText + addressFromList);
                    workingHoursTextLabel.setText("Darba laiks: " + timeFromList);
                    boxesTextLabel.setText("Kastes(gb): " + boxesFromList);
                    if (elevatorFromList.equals("1")) {
                        lvlTextLabel.setText("Lifts: IR");
                    } else if (elevatorFromList.equals("0")) {
                        lvlTextLabel.setText("Stāvs: " + floorFromList + " (Lifta NAV!)");
                    }

                    notesTextArea.setText(notesText + notesFromList);
                    totalTextLabel.setText("Kopā: " + gop.getOrderPrice(boxesFromList, floorFromList) + "€ + PVN 21%");
                    monthTextLabel.setText("*Mēneša maksa: " + gmp.getMonthPrise(boxesFromList) + "€ + PVN 21%");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            statussFromDB = gcto.getColumnsTextFromOrder(orderNr, "accept");
            noteFromDB = gcto.getColumnsTextFromOrder(orderNr, "admin_note");
            System.out.println(statussFromDB);
            System.out.println(noteFromDB);
            switch (statussFromDB) {
                case "Atteikts!" -> {
                    noteTxtAreaFromDB.setVisible(true);
                    noteTxtAreaFromDB.setForeground(myColor.TXT_NOTE_RED);
                    statussTextLabel.setForeground(myColor.TXT_NOTE_RED);
                    noteTxtAreaFromDB.setText(noteFromDB);
                    statussTextLabel.setText("Statuss: " + statussFromDB);
                }
                case "Akceptēts!" -> {
                    noteTxtAreaFromDB.setVisible(true);
                    noteTxtAreaFromDB.setForeground(myColor.TXT_GREEN);
                    statussTextLabel.setForeground(myColor.TXT_GREEN);
                    noteTxtAreaFromDB.setText(noteFromDB);
                    statussTextLabel.setText("Statuss: " + statussFromDB);
                }
                case "Gaida" -> {
                    noteTxtAreaFromDB.setVisible(false);
                    noteTxtAreaFromDB.setForeground(myColor.TXT_NOTE_YELLOW);
                    statussTextLabel.setForeground(myColor.TXT_NOTE_YELLOW);
                    statussTextLabel.setText("Statuss: Gaida atbildi!");
                }
            }
            statussTextLabel.setVisible(true);
        }

        //acceptBtn action and design
        if (e.getSource().equals(acceptBtn)) {
            noteTxt.setText("Mūsu e-pasts saziņai: info@documentsolutions.lv");
            statuss = true;
            acceptBtn.setBackground(myColor.BTN_PRESS);
            setUserBtn.setBackground(myColor.BTN);
            declineBtn.setBackground(myColor.BTN_DECLINE);
            showOrderBtn.setBackground(myColor.BTN);
            acceptBtn.setVisible(true);
            noteTxt.setVisible(true);
            submitBtn.setVisible(true);
            noteTxtAreaFromDB.setVisible(false);
            statussTextLabel.setVisible(false);
        }

        //declineBtn action and design
        if (e.getSource().equals(declineBtn)) {
            noteTxt.setText("Mūsu e-pasts saziņai: info@documentsolutions.lv");
            statuss = false;
            declineBtn.setBackground(myColor.BTN_DECLINE_PRESS);
            setUserBtn.setBackground(myColor.BTN);
            showOrderBtn.setBackground(myColor.BTN);
            acceptBtn.setBackground(myColor.BTN);
            noteTxt.setVisible(true);
            submitBtn.setVisible(true);
            noteTxtAreaFromDB.setVisible(false);
            statussTextLabel.setVisible(false);
        }

        //sendBtn action and design
        if (e.getSource().equals(submitBtn)) {
            ud.updateDatabase(orderNr, noteTxt.getText(), statuss);
            declineBtn.setBackground(myColor.BTN_DECLINE_PRESS);
            setUserBtn.setBackground(myColor.BTN);
            showOrderBtn.setBackground(myColor.BTN);
            acceptBtn.setBackground(myColor.BTN);
            noteTxt.setVisible(false);
            submitBtn.setVisible(false);
            acceptBtn.setVisible(false);
            declineBtn.setVisible(false);
            noteTxtAreaFromDB.setVisible(false);
            statussTextLabel.setVisible(false);
        }
    }
}
