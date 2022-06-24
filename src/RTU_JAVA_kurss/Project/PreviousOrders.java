package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.Extensions.*;
import RTU_JAVA_kurss.YouNeedThis.*;

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
    WriteTextToFile wttf = new WriteTextToFile();
    GetTextFromFile gtff = new GetTextFromFile();
    GetLastOrdersInfo gloi = new GetLastOrdersInfo();
    GetOrdersInfo goi = new GetOrdersInfo();
    GetOrderPrice gop = new GetOrderPrice();
    String currentTime, address, time, boxes, elevator, floor, notes, orderType, currentUser, currentOrderID;

    MyTransparentLabel mainLabel = new MyTransparentLabel(65, 100, 770, 500);
    MyFrame previousOrdersFrame = new MyFrame("Document Solutions Previous Orders Page");
    MyLabel topTextLabel = new MyLabel("*TEST* 23.06.2022 (Glabāšana)", 300, 10, 400, 30, 20);
    MyLabel addressTextLabel = new MyLabel("*TEST* Adrese: " + "Rīga, Lienes iela 3, 3, LV-1004", 250, 50, 500, 20, 16);
    MyLabel addressTextLabel2 = new MyLabel("", 250, 70, 500, 20, 16);
    MyLabel timeTextLabel = new MyLabel("*TEST* Darba laiks: " + "09:00- 19:30", 250, 90, 500, 20, 16);
    MyLabel boxesTextLabel = new MyLabel("*TEST* Kastes: " + "400" + " GB", 250, 110, 500, 20, 16);
    MyLabel lvlTextLabel = new MyLabel("*TEST* Stāvs: " + "0", 250, 130, 500, 20, 16);
    MyLabel notesTextLabel = new MyLabel("*TEST* Piezīmes: " + "Piebtraukt var no Cēsu ielas......", 250, 150, 550, 20, 16);
    MyLabel notesTextLabel2 = new MyLabel("", 250, 170, 550, 20, 16);
    MyLabel notesTextLabel3 = new MyLabel("", 250, 190, 550, 20, 16);
    MyLabel totalTextLabel = new MyLabel("*TEST* Kopā: " + "235,0 Eiro + PVN 21%", 250, 250, 500, 30, 20);
    MyLabel monthTextLabel = new MyLabel("*TEST* *Mēneša maksa: " + "90,0 Eiro + PVN 21%", 250, 290, 500, 20, 16);
    //MyLabel statussTextLabel= new MyLabel();

    MyButton showOrderBtn = new MyButton("Parādīt", 25, 80, 200, 50);

    MyComboBox ordersBox = new MyComboBox("OrderID", 50, 20, 150, 50);

    String orderNr = "", userNr = "", orderFromList = "", addressFromList = "", notesFromList = "",
            timeFromList = "", boxesFromList = "", elevatorFromList = "", floorFromList = "", dateFromList = "", userID = "";
    String[] listsArray = new String[]{orderFromList, addressFromList, notesFromList, timeFromList, boxesFromList, elevatorFromList, floorFromList, dateFromList, userID};
    String[] databaseTitlesArray = new String[]{"OrderID", "address", "notes", "time", "boxes", "elevator", "floor", "date_time", "UserID"};
    int tableSize = Integer.parseInt(gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/userTableSize.txt"));
    ArrayList<ArrayList<String>> outer = new ArrayList<>();
    String[] ordersArray = new String[tableSize]; // glabās visus lietotāja ID

    PreviousOrders() {
        currentUser = gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/users_ID.txt");
        currentOrderID = gloi.getLastOrdersInfo(currentUser, "orderID", "orderID");
        currentTime = goi.getOrdersInfo(currentOrderID, "date_time");
        address = goi.getOrdersInfo(currentOrderID, "address");
        time = goi.getOrdersInfo(currentOrderID, "time");
        boxes = goi.getOrdersInfo(currentOrderID, "boxes");
        elevator = goi.getOrdersInfo(currentOrderID, "elevator");
        floor = goi.getOrdersInfo(currentOrderID, "floor");
        notes = goi.getOrdersInfo(currentOrderID, "notes");
        orderType = " (Glabāšana)";

        showOrderBtn.addActionListener(this);
        ordersBox.addActionListener(this);

        showOrderBtn.setVisible(false);

        topTextLabel.setText(currentTime + orderType);
        if (address.length() > 50 && address.length() <= 100) {
            addressTextLabel.setText("Adrese: " + address.substring(0, 50));
            addressTextLabel2.setText("            " + address.substring(50, address.length() - 1));
        } else if (address.length() <= 50) {
            addressTextLabel.setText("Adrese: " + address);
        }
        timeTextLabel.setText("Darba laiks: " + time);
        boxesTextLabel.setText("Kastes(gb): " + boxes);
        if (elevator.equals("1")) {
            lvlTextLabel.setText("Lifts: IR");
        } else if (elevator.equals("0")) {
            lvlTextLabel.setText("Stāvs: " + floor + " (Lifta NAV!)");
        }

        if (notes.length() > 50 && notes.length() <= 100) {
            notesTextLabel.setText("Piezīmes: " + notes.substring(0, 50));
            notesTextLabel2.setText("                " + notes.substring(50, notes.length() - 1));
        } else if (notes.length() > 100) {
            notesTextLabel.setText("Piezīmes: " + notes.substring(0, 50));
            notesTextLabel2.setText("                " + notes.substring(50, 100));
            notesTextLabel3.setText("                " + notes.substring(100, notes.length() - 1));
        } else if (notes.length() <= 50) {
            notesTextLabel.setText("Piezīmes: " + notes);
        }
        totalTextLabel.setText("Kopā: " + gop.getOrderPrice(boxes, floor) + "€ + PVN 21%");
        monthTextLabel.setText("*Mēneša maksa: " + Integer.parseInt(boxes) * 0.6 + "€ + PVN 21%");

        mainLabel.add(showOrderBtn);
        mainLabel.add(ordersBox);
        mainLabel.add(monthTextLabel);
        mainLabel.add(totalTextLabel);
        //mainLabel.add(statussTextLabel);
        mainLabel.add(notesTextLabel3);
        mainLabel.add(notesTextLabel2);
        mainLabel.add(notesTextLabel);
        mainLabel.add(lvlTextLabel);
        mainLabel.add(boxesTextLabel);
        mainLabel.add(timeTextLabel);
        mainLabel.add(addressTextLabel2);
        mainLabel.add(addressTextLabel);
        mainLabel.add(topTextLabel);


        //currentUser
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
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

            try { // saskaita cik kopā ir klientu ir saglabāti datubāzē
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
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
        if (e.getSource().equals(showOrderBtn)) {
            showOrderBtn.setBackground(new Color(141, 210, 93));
            orderNr = (String) ordersBox.getSelectedItem(); // OrderNr glabā izvēlētā pasūtījuma OrderID
            System.out.println("OrderID= " + orderNr);

            try { // piepilda listsArray ar vērtībām
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
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
                    topTextLabel.setText("ID= " + userID + " #00" + orderNr + " (Glabāšana) " + dateFromList);
                    if (addressFromList.length() > 60 && addressFromList.length() <= 120) {
                        addressTextLabel.setText("Adrese: " + addressFromList.substring(0, 60));
                        addressTextLabel2.setText("            " + addressFromList.substring(60, addressFromList.length() - 1));
                    } else if (addressFromList.length() <= 60) {
                        addressTextLabel.setText("Adrese: " + addressFromList);
                        addressTextLabel2.setText("");
                    }
                    timeTextLabel.setText("Darba laiks: " + timeFromList);
                    boxesTextLabel.setText("Kastes(gb): " + boxesFromList);
                    if (elevatorFromList.equals("1")) {
                        lvlTextLabel.setText("Lifts: IR");
                    } else if (elevatorFromList.equals("0")) {
                        lvlTextLabel.setText("Stāvs: " + floorFromList + " (Lifta NAV!)");
                    }

                    if (notesFromList.length() > 60 && notesFromList.length() <= 120) {
                        notesTextLabel.setText("Piezīmes: " + notesFromList.substring(0, 60));
                        notesTextLabel2.setText("                " + notesFromList.substring(60, notesFromList.length() - 1));
                        notesTextLabel3.setText("");
                    } else if (notesFromList.length() > 120) {
                        notesTextLabel.setText("Piezīmes: " + notesFromList.substring(0, 60));
                        notesTextLabel2.setText("                " + notesFromList.substring(60, 120));
                        notesTextLabel3.setText("                " + notesFromList.substring(120, notesFromList.length() - 1));
                    } else if (notesFromList.length() <= 60) {
                        notesTextLabel.setText("Piezīmes: " + notesFromList);
                        notesTextLabel2.setText("");
                        notesTextLabel3.setText("");
                    }
                    totalTextLabel.setText("Kopā: " + gop.getOrderPrice(boxesFromList, floorFromList) + "€ + PVN 21%");
                    monthTextLabel.setText("*Mēneša maksa: " + Integer.parseInt(boxesFromList) * 0.6 + "€ + PVN 21%");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
