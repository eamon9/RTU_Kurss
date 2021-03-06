package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.MyExtensions.*;
import RTU_JAVA_kurss.Main.OrderG;
import RTU_JAVA_kurss.YouNeedThis.GetCurrentTime;
import RTU_JAVA_kurss.YouNeedThis.TxtFileConnection.GetTextFromFile;
import RTU_JAVA_kurss.YouNeedThis.TxtFileConnection.WriteTextToFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class OrderPage_StoreDocuments extends Component implements ActionListener, MouseListener {
    MyColor myColor = new MyColor();
    GetTextFromFile gtff = new GetTextFromFile();
    WriteTextToFile wttf = new WriteTextToFile();
    GetCurrentTime gct = new GetCurrentTime();

    String elevatorIs = "1"; // is there an elevator(0- no elevator, 1- there is an elevator)
    String floorA = "0";
    String  userIDTxtFile= "/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/users_ID.txt",
            orderTableSizeTxtFile= "/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/orderTableSize.txt";
    String user_ID = gtff.getTextFromFile(userIDTxtFile);

    MyFrame storeDocumentsFrame = new MyFrame("Document Solutions Store Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

    // components for label #1
    MyTransparentTextLabel adressTxtLabel = new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea addressTxtArea = new MyTextArea(20, 40, 200, 160);

    // components for label #2
    MyTransparentTextLabel businessHoursTxtLabel = new MyTransparentTextLabel("Darba laiks", 60, 0, 240, 50);
    MyTextField businessHoursTextF = new MyTextField(40, 70, 160, 40);

    // components for label #3
    MyTransparentLabel helpLabel1 = new MyTransparentLabel(40, 40, 160, 70);
    MyTransparentTextLabel leftSLabel1Text = new MyTransparentTextLabel("Daudzums kast??s *", 40, 0, 240, 50);
    MyLabel helpTextLabel1 = new MyLabel("VADOTIES P??C T??, KA", 5, 10, 160, 15, 12);
    MyLabel helpTextLabel2 = new MyLabel("VIEN?? KAST?? SALIEN", 5, 30, 160, 15, 12);
    MyLabel helpTextLabel3 = new MyLabel("5 RE??ISTRA MAPES", 5, 50, 160, 15, 12);
    MyTextField boxTextF = new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText = new MyTransparentTextLabel("(- kastes)", 150, 160, 100, 40);

    // components for label #4
    MyTransparentTextLabel middleSLabel2Text = new MyTransparentTextLabel("Piez??mes", 70, 0, 240, 50);
    MyTextArea notesTextArea = new MyTextArea(20, 40, 200, 160);

    // components for label #5
    MyTransparentTextLabel middleSLabel1Text = new MyTransparentTextLabel("St??vs / Pagrabs", 60, 120, 240, 50);
    MyTransparentTextLabel middleSLabel1Text2 = new MyTransparentTextLabel("Lifts", 100, 0, 240, 50);
    MyButton yesBtn = new MyButton("IR", 20, 40, 90, 50);
    MyButton noBtn = new MyButton("NAV", 130, 40, 90, 50);
    MyTextField floorTextF = new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2 = new MyTransparentTextLabel("(- st??vi)", 150, 160, 100, 40);

    // components for label #6
    MyButton nextBtn = new MyButton("Iesniegt", 20, 100, 200, 50);
    String updateUsersCount;

    public OrderPage_StoreDocuments() {
        // components for label #1
        addressTxtArea.setText("Pils??ta,\nIela,\nkorpuss, dz.nr.,\nPasta indeks");
        addressTxtArea.setForeground(Color.GRAY);
        addressTxtArea.setEditable(false);
        addressTxtArea.addMouseListener(this);

        label_1.add(addressTxtArea);
        label_1.add(adressTxtLabel);

        // components for label #2
        businessHoursTextF.setText("09:00 - 17:00");
        businessHoursTextF.setEditable(false);
        businessHoursTextF.setForeground(Color.GRAY);
        businessHoursTextF.addMouseListener(this);

        label_2.add(businessHoursTextF);
        label_2.add(businessHoursTxtLabel);

        // components for label #3
        // Change color of helpText
        helpTextLabel1.setForeground(Color.BLACK);
        helpTextLabel2.setForeground(Color.BLACK);
        helpTextLabel3.setForeground(Color.BLACK);
        // leftSLabel1Text add Mouse Listener
        leftSLabel1Text.addMouseListener(this);
        // helpLabel1 setUp
        helpLabel1.setBackground(myColor.FRAME);
        helpLabel1.add(helpTextLabel1);
        helpLabel1.add(helpTextLabel2);
        helpLabel1.add(helpTextLabel3);
        helpLabel1.setVisible(false);
        // leftSideLabel1 setUp
        label_3.add(boxSideText);
        label_3.add(boxTextF);
        label_3.add(leftSLabel1Text);
        label_3.add(helpLabel1);

        // components for label #4
        label_4.add(notesTextArea);
        label_4.add(middleSLabel2Text);

        // components for label #5
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

        // components for label #6
        label_6.add(nextBtn);

        // All labels added to storeDocumentsFrame
        MyTransparentLabel[] labels = new MyTransparentLabel[]{label_1, label_2, label_3, label_4, label_5, label_6};
        for (int i = 0; i < labels.length; i++) {
            storeDocumentsFrame.add(labels[i]);
        }

        nextBtn.addActionListener(this);
        storeDocumentsFrame.setLayout(null);
        storeDocumentsFrame.setVisible(true);
    } // End OrderPage_StoreDocuments()

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(yesBtn)) {
            floorA = "0";
            elevatorIs = "1";
            yesBtn.setBackground(myColor.BTN_PRESS);
            noBtn.setBackground(myColor.BTN);
            floorTextF.setVisible(false);
            boxSideText2.setVisible(false);
        }
        if (e.getSource().equals(noBtn)) {
            elevatorIs = "0";
            floorA = floorTextF.getText();
            yesBtn.setBackground(myColor.BTN);
            noBtn.setBackground(myColor.BTN_PRESS);
            floorTextF.setVisible(true);
            boxSideText2.setVisible(true);
        }
        if (e.getSource().equals(nextBtn)) {
            registerOrderP();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(addressTxtArea)) {
            addressTxtArea.setText("");
            addressTxtArea.setForeground(Color.BLACK);
            addressTxtArea.setEditable(true);
        }
        if (e.getSource().equals(businessHoursTextF)) {
            businessHoursTextF.setText("");
            businessHoursTextF.setEditable(true);
            businessHoursTextF.setForeground(Color.BLACK);
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
        if (e.getSource().equals(leftSLabel1Text)) {
            helpLabel1.setVisible(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource().equals(leftSLabel1Text)) {
            helpLabel1.setVisible(false);
        }
    }

    OrderG orderG;

    private void registerOrderP() {
        String
                address = addressTxtArea.getText(),
                notes = notesTextArea.getText(),
                time = businessHoursTextF.getText(),
                boxes = boxTextF.getText(),
                elevator = elevatorIs,
                floor = "-1",
                currentTime = gct.getCurrentTime(),
                userID = user_ID;

        if (elevatorIs.equals("1")) {
            floor = "0";
        } else if (elevatorIs.equals("0")) {
            floor = floorTextF.getText();
        }

        if (address.isEmpty() || boxes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "'Adrese' un 'Kastu skaits' laukiem j??b??t aizpild??tiem", "M????ini v??lreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (Integer.parseInt(boxes) <= 0) {
            JOptionPane.showMessageDialog(this, "L??dzu ievadiet der??gu kastu skaitu!", "M????ini v??lreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // zem??k var izveidot valid??cijas piem. adres?? j??b??t skaidram LV-0000
        orderG = addOrderSToDatabase(address, notes, time, boxes, elevator, floor, currentTime, userID);

        if (orderG != null) {
            int tempOrderTableSize = Integer.parseInt(gtff.getTextFromFile(orderTableSizeTxtFile));
            int updatedOrderTableSize = tempOrderTableSize + 1;
            wttf.writeTextToFile(orderTableSizeTxtFile, String.valueOf(updatedOrderTableSize));
            storeDocumentsFrame.dispose();
            new PreviousOrders();
        } else {
            JOptionPane.showMessageDialog(this, "Neizdev??s izveidot pas??t??jumu", "M????ini v??lreiz", JOptionPane.ERROR_MESSAGE);
        }
    }

    private OrderG addOrderSToDatabase(String address, String notes, String time, String boxes, String elevator, String floor, String currentTime, String userID) {
        OrderG orderG = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/JAVA_IT"; //j??nor??da datub??zes lok??cija, kas jau iepriek?? ir izveidota
        final String USERNAME = "root"; // ??is ir noklus??juma username
        final String PASSWORD = ""; // ???? ir izveidot?? parole iek?? MySQL

        try {
            Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement statement = connection.createStatement();
            String sql = "INSERT INTO orders (address, notes, time, boxes, elevator, floor, date_time, userID) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, address);
            preparedStatement.setString(2, notes);
            preparedStatement.setString(3, time);
            preparedStatement.setString(4, boxes);
            preparedStatement.setString(5, elevator);
            preparedStatement.setString(6, floor);
            preparedStatement.setString(7, currentTime);
            preparedStatement.setString(8, userID);
            //l??dz ??im str??d??
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                orderG = new OrderG();
                orderG.address = address;
                orderG.notes = notes;
                orderG.time = time;
                orderG.boxes = boxes;
                orderG.elevator = elevator;
                orderG.floor = floor;
                orderG.currentTime = currentTime;
                orderG.userID = userID;
            }
            statement.close();
            connection.close();
        } catch (SQLException s) {
            if (s.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(this, "????ds pas??t??jums jau eksit??!", "M????ini v??lreiz", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderG;
    }
} //End class OrderPage_StoreDocuments
