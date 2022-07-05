package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.MyExtensions.*;
import RTU_JAVA_kurss.YouNeedThis.GetMonthPrise;
import RTU_JAVA_kurss.YouNeedThis.GetOrderPrice;
import RTU_JAVA_kurss.YouNeedThis.MySQLConnection.GetColumnsTextFromOrder;
import RTU_JAVA_kurss.YouNeedThis.MySQLConnection.GetLastOrdersInfo;
import RTU_JAVA_kurss.YouNeedThis.MySQLConnection.GetOrdersInfo;
import RTU_JAVA_kurss.YouNeedThis.TxtFileConnection.GetTextFromFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class PreviousOrders implements ActionListener, MouseListener {
    GetMonthPrise gmp= new GetMonthPrise();
    GetColumnsTextFromOrder gct = new GetColumnsTextFromOrder();
    GetLastOrdersInfo gloi = new GetLastOrdersInfo();
    GetTextFromFile gtff = new GetTextFromFile();
    GetOrdersInfo goi = new GetOrdersInfo();
    GetOrderPrice gop = new GetOrderPrice();
    String orderDateTime, address, workingTime, boxes, isElevator, floor, notes, orderType, currentUser, currentOrderID;

    MyTransparentLabel mainLabel = new MyTransparentLabel(65, 100, 770, 500);
    MyFrame previousOrdersFrame = new MyFrame("Document Solutions Previous Orders Page");
    MyLabel topTextLabel = new MyLabel("(Glabāšana)", 300, 10, 400, 30, 20);

    MyTextArea addressTextArea= new MyTextArea(530, 40, 220, 150);
    MyLabel workingTimeTextLabel = new MyLabel("Darba laiks: ", 250, 40, 500, 20, 16);
    MyLabel boxesTextLabel = new MyLabel("Kastes: ", 250, 60, 500, 20, 16);
    MyLabel lvlTextLabel = new MyLabel("Stāvs: ", 250, 80, 500, 20, 16);
    MyTextArea notesTextArea= new MyTextArea(530, 200, 220, 150);

    MyLabel totalPaymentTextLabel = new MyLabel("Kopā: ", 250, 250, 500, 30, 20);
    MyLabel monthlyPaymentTextLabel = new MyLabel("*Mēneša maksa: ", 250, 290, 500, 20, 16);
    MyLabel statussTextLabel = new MyLabel("Statuss: ", 250, 310, 500, 20, 16);
    MyTextArea noteFromAdminTextF = new MyTextArea(245, 355, 280, 100);



    MyButton showOrderBtn = new MyButton("Parādīt", 25, 80, 200, 50);
    MyButton showStatussBtn = new MyButton("Statuss", 25, 355, 200, 50);


    MyComboBox ordersBox = new MyComboBox("OrderID", 50, 20, 150, 50);

    //int orderTableSize;
    String orderNr = "1", orderFromList = "", addressFromList = "", notesFromList = "", addressText= "Adrese: ", notesText= "Piezīmes: ",
            timeFromList = "", boxesFromList = "", elevatorFromList = "", floorFromList = "", dateFromList = "", userID = "", statuss= "", adminNote="";
    String[] listsArray = new String[]{orderFromList, addressFromList, notesFromList, timeFromList, boxesFromList, elevatorFromList, floorFromList, dateFromList, userID};
    String[] databaseTitlesArray = new String[]{"OrderID", "address", "notes", "time", "boxes", "elevator", "floor", "date_time", "UserID"};
    int orderTableSize = Integer.parseInt(gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/orderTableSize.txt"));
    ArrayList<ArrayList<String>> outer = new ArrayList<>();
    String[] ordersArray = new String[orderTableSize]; // glabās visus pasūtījumu ID

    public PreviousOrders() {

        currentUser = gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/users_ID.txt");
        currentOrderID = gloi.getLastOrdersInfo(currentUser, "orderID", "orderID");
        orderDateTime = goi.getOrdersInfo(currentOrderID, "date_time");
        address = goi.getOrdersInfo(currentOrderID, "address");
        workingTime = goi.getOrdersInfo(currentOrderID, "time");
        boxes = goi.getOrdersInfo(currentOrderID, "boxes");
        isElevator = goi.getOrdersInfo(currentOrderID, "elevator");
        floor = goi.getOrdersInfo(currentOrderID, "floor");
        notes = goi.getOrdersInfo(currentOrderID, "notes");
        orderType = " (Glabāšana)";

        showStatussBtn.addActionListener(this);
        showOrderBtn.addActionListener(this);
        ordersBox.addActionListener(this);

        showStatussBtn.setVisible(false);
        showOrderBtn.setVisible(false);
        noteFromAdminTextF.setVisible(false);
        noteFromAdminTextF.setEditable(false);
        noteFromAdminTextF.setBackground(new Color(46, 149, 169, 255)); //new Color(46, 149, 169, 255)
        noteFromAdminTextF.setText("*TEST*\n*TEST*\n*TEST*\n*TEST*");

        addressTextArea.setEditable(false);
        addressTextArea.setText(addressText);
        addressTextArea.setForeground(new Color(7, 105, 64, 190));
        addressTextArea.setBackground(new Color(46, 149, 169, 255));
        addressTextArea.setFont(new Font("Times New Roman", Font.BOLD, 16));
        notesTextArea.setEditable(false);
        notesTextArea.setText(notesText);
        notesTextArea.setForeground(new Color(7, 105, 64, 190));
        notesTextArea.setBackground(new Color(46, 149, 169, 255));
        notesTextArea.setFont(new Font("Times New Roman", Font.BOLD, 16));

        topTextLabel.setText("ID= " + currentUser + " #00" + currentOrderID +" "+orderType+" "+ orderDateTime);
        addressTextArea.setText(addressText+ address);

        workingTimeTextLabel.setText("Darba laiks: " + workingTime);
        boxesTextLabel.setText("Kastes(gb): " + boxes);
        if (isElevator.equals("1")) {
            lvlTextLabel.setText("Lifts: IR");
        } else if (isElevator.equals("0")) {
            lvlTextLabel.setText("Stāvs: " + floor + " (Lifta NAV!)");
        }
        notesTextArea.setText(notesText+notes);
        totalPaymentTextLabel.setText("Kopā: " + gop.getOrderPrice(boxes, floor) + "€ + PVN 21%");
        monthlyPaymentTextLabel.setText("*Mēneša maksa: " + gmp.getMonthPrise(boxes) + "€ + PVN 21%");
        statussTextLabel.setText("Statuss: ");

        mainLabel.add(addressTextArea);
        mainLabel.add(notesTextArea);
        mainLabel.add(statussTextLabel);
        mainLabel.add(noteFromAdminTextF);
        mainLabel.add(showStatussBtn);
        mainLabel.add(showOrderBtn);
        mainLabel.add(ordersBox);
        mainLabel.add(monthlyPaymentTextLabel);
        mainLabel.add(totalPaymentTextLabel);
        mainLabel.add(lvlTextLabel);
        mainLabel.add(boxesTextLabel);
        mainLabel.add(workingTimeTextLabel);
        mainLabel.add(topTextLabel);


        //currentUser
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT OrderId FROM orders WHERE userID=" + currentUser + " ORDER BY OrderID");
            while (resultSet.next()) {
                ordersBox.addItem(resultSet.getString("OrderID"));
                ArrayList<String> inner = new ArrayList<>();
                inner.add(resultSet.getString("OrderID"));
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

        previousOrdersFrame.add(mainLabel);
        previousOrdersFrame.setLayout(null);
        previousOrdersFrame.setVisible(true);
    } //End PreviousOrders()

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ordersBox)) {
            ordersBox.removeItem("OrderID");
            showOrderBtn.setVisible(true);
            showStatussBtn.setVisible(false);
            noteFromAdminTextF.setVisible(false);
            statussTextLabel.setText("Statuss: ");
            statussTextLabel.setForeground(new Color(7, 105, 64, 190));
        }

        if (e.getSource().equals(showOrderBtn)) {
            showOrderBtn.setBackground(new Color(141, 210, 93));
            showStatussBtn.setBackground(new Color(184, 229, 154));
            showStatussBtn.setVisible(true);
            orderNr = (String) ordersBox.getSelectedItem(); // OrderNr glabā izvēlētā pasūtījuma OrderID
            System.out.println("OrderID= " + orderNr);

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
                    System.out.println(Arrays.toString(listsArray));
                    topTextLabel.setText("ID= " + userID + " #00" + orderNr +" "+ orderType +" "+ dateFromList);
                    addressTextArea.setText(addressText+addressFromList);
                    workingTimeTextLabel.setText("Darba laiks: " + timeFromList);
                    boxesTextLabel.setText("Kastes(gb): " + boxesFromList);
                    if (elevatorFromList.equals("1")) {
                        lvlTextLabel.setText("Lifts: IR");
                    } else if (elevatorFromList.equals("0")) {
                        lvlTextLabel.setText("Stāvs: " + floorFromList + " (Lifta NAV!)");
                    }
                    notesTextArea.setText(notesText+notesFromList);
                    totalPaymentTextLabel.setText("Kopā: " + gop.getOrderPrice(boxesFromList, floorFromList) + "€ + PVN 21%");
                    monthlyPaymentTextLabel.setText("*Mēneša maksa: " + gmp.getMonthPrise(boxes) + "€ + PVN 21%");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if(e.getSource().equals(showStatussBtn)) {
            showStatussBtn.setBackground(new Color(141, 210, 93));
            showOrderBtn.setBackground(new Color(184, 229, 154));
            statuss= gct.getColumnsTextFromOrder(orderNr, "accept");
            adminNote= gct.getColumnsTextFromOrder(orderNr, "admin_note");
            if(statuss.equals("Atteikts!")) {
                statussTextLabel.setText("Statuss: "+statuss);
                statussTextLabel.setForeground(new Color(112, 5, 11));
                noteFromAdminTextF.setForeground(new Color(112, 5, 11));
                noteFromAdminTextF.setText(adminNote);
            } else if(statuss.equals("Akceptēts!")) {
                statussTextLabel.setText("Statuss: "+statuss);
                statussTextLabel.setForeground(new Color(7, 105, 64, 190));
                noteFromAdminTextF.setForeground(new Color(7, 105, 64, 190));
                noteFromAdminTextF.setText(adminNote);
            } else if(statuss.equals("Gaida")) {
                statussTextLabel.setText("Statuss: Gaida atbildi!");
                statussTextLabel.setForeground(new Color(247, 211, 28));
                noteFromAdminTextF.setText("");
            }
            noteFromAdminTextF.setVisible(true);
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
} //End class PreviousOrders
