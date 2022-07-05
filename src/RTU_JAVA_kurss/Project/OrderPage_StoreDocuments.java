package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.MyExtensions.*;
import RTU_JAVA_kurss.OrderG;
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
    GetTextFromFile gtff= new GetTextFromFile();
    WriteTextToFile wttf= new WriteTextToFile();
    GetCurrentTime gct = new GetCurrentTime();
    String elevatorIs = "1"; // apzīmē vai ir lifts(0- nav lifta, 1- ir lifts)
    String floorA = "0";
    String currentTime = gct.getCurrentTime();
    String user_ID = gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/users_ID.txt");
    MyFrame storeDocumentsFrame = new MyFrame("Document Solutions Store Documents Page");
    MyTransparentLabel label_1 = new MyTransparentLabel(65, 100, 240, 240);
    MyTransparentLabel label_2 = new MyTransparentLabel(330, 100, 240, 240);
    MyTransparentLabel label_3 = new MyTransparentLabel(600, 100, 240, 240);
    MyTransparentLabel label_4 = new MyTransparentLabel(65, 365, 240, 240);
    MyTransparentLabel label_5 = new MyTransparentLabel(330, 365, 240, 240);
    MyTransparentLabel label_6 = new MyTransparentLabel(600, 365, 240, 240);

    // components #1
    MyTransparentTextLabel rightSLabel1Text = new MyTransparentTextLabel("Adrese", 90, 0, 240, 50);
    MyTextArea addressTextArea = new MyTextArea(20, 40, 200, 160);

    // components #2
    MyTransparentTextLabel leftSLabel2Text = new MyTransparentTextLabel("Darba laiks", 60, 0, 240, 50);
    MyTextField workingTimeTextF = new MyTextField(40, 70, 160, 40);

    // components #3
    MyTransparentLabel helpLabel1 = new MyTransparentLabel(40, 40, 160, 70);
    MyTransparentTextLabel leftSLabel1Text = new MyTransparentTextLabel("Daudzums kastēs *", 40, 0, 240, 50);
    MyLabel helpTextLabel1 = new MyLabel("VADOTIES PĒC TĀ, KA", 5, 10, 160, 15, 12);
    MyLabel helpTextLabel2 = new MyLabel("VIENĀ KASTĒ SALIEN", 5, 30, 160, 15, 12);
    MyLabel helpTextLabel3 = new MyLabel("5 REĢISTRA MAPES", 5, 50, 160, 15, 12);
    MyTextField boxTextF = new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText = new MyTransparentTextLabel("(- kastes)", 150, 160, 100, 40);

    // components #4
    MyTransparentTextLabel middleSLabel2Text = new MyTransparentTextLabel("Piezīmes", 70, 0, 240, 50);
    MyTextArea notesTextArea = new MyTextArea(20, 40, 200, 160);

    // components #5
    MyTransparentTextLabel middleSLabel1Text = new MyTransparentTextLabel("Stāvs / Pagrabs", 60, 120, 240, 50);
    MyTransparentTextLabel middleSLabel1Text2 = new MyTransparentTextLabel("Lifts", 100, 0, 240, 50);
    MyButton yesBtn = new MyButton("IR", 20, 40, 90, 50);
    MyButton noBtn = new MyButton("NAV", 130, 40, 90, 50);
    MyTextField floorTextF = new MyTextField(40, 160, 100, 40);
    MyTransparentTextLabel boxSideText2 = new MyTransparentTextLabel("(- stāvi)", 150, 160, 100, 40);

    // components #6
    MyButton nextBtn = new MyButton("Iesniegt", 20, 100, 200, 50);
    String updateUsersCount;

    public OrderPage_StoreDocuments() {
        System.out.println("UserIDs: ID" + user_ID + "#001S");
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
        MyTransparentLabel[] labels = new MyTransparentLabel[]{label_3, label_5, label_1, label_2, label_4, label_6};
        for (int i = 0; i < labels.length; i++) {
            storeDocumentsFrame.add(labels[i]);
        }
        storeDocumentsFrame.setLayout(null);
        storeDocumentsFrame.setVisible(true);
    } // End OrderPage_StoreDocuments()

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(yesBtn)) {
            floorA = "0";
            elevatorIs = "1";
            yesBtn.setBackground(new Color(141, 210, 93));
            noBtn.setBackground(new Color(184, 229, 154));
            floorTextF.setVisible(false);
            boxSideText2.setVisible(false);
        }
        if (e.getSource().equals(noBtn)) {
            elevatorIs = "0";
            floorA = floorTextF.getText();
            yesBtn.setBackground(new Color(184, 229, 154));
            noBtn.setBackground(new Color(141, 210, 93));
            floorTextF.setVisible(true);
            boxSideText2.setVisible(true);
        }
        if (e.getSource().equals(nextBtn)) {
            registerOrderP();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(addressTextArea)) {
            addressTextArea.setText("");
            addressTextArea.setForeground(Color.BLACK);
            addressTextArea.setEditable(true);
        }
        if (e.getSource().equals(workingTimeTextF)) {
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
        String address = addressTextArea.getText();
        String notes = notesTextArea.getText();
        String time = workingTimeTextF.getText();
        String boxes = boxTextF.getText();
        String elevator = elevatorIs;
        String floor = "-1";
        String currentTime = gct.getCurrentTime();
        String userID = user_ID;

        if (elevatorIs.equals("1")) {
            floor = "0";
        } else if (elevatorIs.equals("0")) {
            floor = floorTextF.getText();
        }

        if (address.isEmpty() || boxes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Adresei un Kastu skaitam jābūt aizpildītiem", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // zemāk var izveidot validācijas piem. adresē jābūt skaidram LV-0000
        orderG = addOrderSToDatabase(address, notes, time, boxes, elevator, floor, currentTime, userID);
        System.out.println("Stāvi: " + floor + " vs StāviA: " + floorA + " vs Stāvi.getText(): " + floorTextF.getText());
        if (orderG != null) {
            storeDocumentsFrame.dispose();
            int tempOrderTableSize= Integer.parseInt(gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/orderTableSize.txt"));
            int updatedOrderTableSize= tempOrderTableSize+1;
            wttf.writeTextToFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/orderTableSize.txt", String.valueOf(updatedOrderTableSize));
            new PreviousOrders();
            //System.out.println("Adr: " + address + "|| Notes: " + notes + "|| WorkingTime: " + time + "|| Boxes: " + boxes + "|| Lifts: " + elevator + "|| Stāvs:  " + floor + "|| ID: " + userID + "|| Time: " + currentTime);

        } else {
            System.out.println(orderG);
            JOptionPane.showMessageDialog(this, "Neizdevās izveidot pasūtījumu", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
        }
    }

    private OrderG addOrderSToDatabase(String address, String notes, String time, String boxes, String elevator, String floor, String currentTime, String userID) {
        OrderG orderG = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/JAVA_IT"; //jānorāda datubāzes lokācija, kas jau iepriekš ir izveidota
        final String USERNAME = "root"; // šis ir noklusējuma username
        final String PASSWORD = ""; // šī ir izveidotā parole iekš MySQL

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
            //līdz šim strādā
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                System.out.println("Rows added strādā");
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
                JOptionPane.showMessageDialog(this, "Šāds pasūtījums jau eksitē!", "Mēģini vēlreiz", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderG;
    }
} //End class OrderPage_StoreDocuments
