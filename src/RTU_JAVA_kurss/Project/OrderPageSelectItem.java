package RTU_JAVA_kurss.Project;

import RTU_JAVA_kurss.MyExtensions.MyColor;
import RTU_JAVA_kurss.MyExtensions.MyFrame;
import RTU_JAVA_kurss.MyExtensions.MyTransparentLabel;
import RTU_JAVA_kurss.MyExtensions.MyTransparentTextLabel;
import RTU_JAVA_kurss.YouNeedThis.MySQLConnection.GetOrdersCountFromUser;
import RTU_JAVA_kurss.YouNeedThis.TxtFileConnection.GetTextFromFile;
import RTU_JAVA_kurss.YouNeedThis.TxtFileConnection.WriteTextToFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderPageSelectItem implements ActionListener, MouseListener {
    MyColor myColor = new MyColor();
    GetOrdersCountFromUser goc = new GetOrdersCountFromUser();
    WriteTextToFile wttf = new WriteTextToFile();
    GetTextFromFile gtff = new GetTextFromFile();
    MyFrame orderPageFrame = new MyFrame("Document Solutions Order Page");
    MyTransparentLabel sideLabel1 = new MyTransparentLabel(65, 100, 365, 500);
    MyTransparentLabel sideLabel2 = new MyTransparentLabel(480, 100, 350, 500);
    MyTransparentLabel leftSideLabel = new MyTransparentLabel(65, 100, 240, 500);
    MyTransparentLabel middleSideLabel = new MyTransparentLabel(330, 100, 240, 500);
    MyTransparentLabel rightSideLabel = new MyTransparentLabel(600, 100, 240, 500);
    MyTransparentTextLabel servicesTextLabel = new MyTransparentTextLabel("Pakalpojumi", 135, 60, 250, 50);
    MyTransparentTextLabel previousOrdersTextLabel = new MyTransparentTextLabel("Iepriekšējie pasūtījumi", 90, 60, 250, 50);
    MyTransparentTextLabel leftSideTextLabel = new MyTransparentTextLabel("Dokumentu glabāšana", 30, 20, 240, 100);
    MyTransparentTextLabel middleSideTextLabel = new MyTransparentTextLabel("Smalcināšana / iznīcināšana", 10, 20, 240, 100);
    MyTransparentTextLabel rightSideTextLabel = new MyTransparentTextLabel("Arhīva sakārtošana", 40, 20, 240, 100);

    String currentUser = "", ordersSum = "";

    public OrderPageSelectItem() {
        currentUser = gtff.getTextFromFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/users_ID.txt");
        ordersSum = goc.getOrdersCountFromUser(currentUser);
        wttf.writeTextToFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/orderTableSize.txt", ordersSum);
        System.out.println(ordersSum);

        sideLabel2.setVisible(!ordersSum.equals("0"));

        sideLabel2.add(previousOrdersTextLabel);
        sideLabel1.add(servicesTextLabel);
        leftSideLabel.add(leftSideTextLabel);
        middleSideLabel.add(middleSideTextLabel);
        rightSideLabel.add(rightSideTextLabel);

        MyTransparentLabel[] labels = new MyTransparentLabel[]{leftSideLabel, middleSideLabel, rightSideLabel};
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
        if (e.getSource().equals(leftSideLabel)) {
            orderPageFrame.dispose();
            new OrderPage_StoreDocuments();
        }
        if (e.getSource().equals(middleSideLabel)) {
            orderPageFrame.dispose();
            new OrderPage_ShreddingDocuments();
        }
        if (e.getSource().equals(rightSideLabel)) {
            orderPageFrame.dispose();
            new OrderPage_ArchiveDocuments();
        }
        MyTransparentLabel[] labels2 = new MyTransparentLabel[]{sideLabel1, sideLabel2};
        for (int i = 0; i < labels2.length; i++) {
            if (e.getSource().equals(sideLabel1)) {
                labels2[i].setVisible(false);
                leftSideLabel.setVisible(true);
                middleSideLabel.setVisible(true);
                rightSideLabel.setVisible(true);
            }
        }
        if (e.getSource().equals(sideLabel2)) {

            try { // saskaita cik kopā ir, konkrētam klientam pasūtījumu saglabāti datubāzē un iegūto skaitu saglabā text datnē
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "");
                Statement statement = connection.createStatement();
                ResultSet getColumnSize = statement.executeQuery("SELECT COUNT(*) FROM orders WHERE UserID=" + currentUser);
                while (getColumnSize.next()) {
                    int orderTableSize = Integer.parseInt(getColumnSize.getString("COUNT(*)"));
                    wttf.writeTextToFile("/Users/qwer/eclipse-workspace/IT_Projekts/src/RTU_JAVA_kurss/textFiles/orderTableSize.txt", String.valueOf(orderTableSize));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

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
        MyTransparentLabel[] labels = new MyTransparentLabel[]{leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            if (e.getSource().equals(labels[i])) {
                labels[i].setBackground(myColor.BTN); //c.myColor("LABEL_TRANSPARENT") color before entered
            }
        }
        MyTransparentLabel[] labels2 = new MyTransparentLabel[]{sideLabel1, sideLabel2};
        for (int i = 0; i < labels2.length; i++) {
            if (e.getSource().equals(labels2[i])) {
                labels2[i].setBackground(myColor.BTN); //c.myColor("LABEL_TRANSPARENT") color before entered
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MyTransparentLabel[] labels = new MyTransparentLabel[]{leftSideLabel, middleSideLabel, rightSideLabel};
        for (int i = 0; i < labels.length; i++) {
            if (e.getSource().equals(labels[i])) {
                labels[i].setBackground(myColor.LABEL_TRANSPARENT);
            }
        }
        MyTransparentLabel[] labels2 = new MyTransparentLabel[]{sideLabel1, sideLabel2};
        for (int i = 0; i < labels2.length; i++) {
            if (e.getSource().equals(labels2[i])) {
                labels2[i].setBackground(myColor.LABEL_TRANSPARENT);
            }
        }
    }
} //End OrderPageSelectItem class
