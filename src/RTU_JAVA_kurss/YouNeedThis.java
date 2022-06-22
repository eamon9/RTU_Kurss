package RTU_JAVA_kurss;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/*class AllYouNeedInOnePlace extends Object{
    public GetCurrentTime gct;
    public GetUserInfo gui;
    public GetOrdersInfo goi;
    public GetLastOrdersInfo gloi;
    public GetTextFromFile gtff;
    public WriteTextToFile wttf;
    public GetOrderPrice gop;
    public GetOneColumnInfoFromUsers ci;

    public void allYouNeedInOnePlace() {
        this.gct= new GetCurrentTime();
        this.gui= new GetUserInfo();
        this.goi= new GetOrdersInfo();
        this.gloi= new GetLastOrdersInfo();
        this.gtff= new GetTextFromFile();
        this.wttf= new WriteTextToFile();
        this.gop= new GetOrderPrice();
        this.ci= new GetOneColumnInfoFromUsers();
    }
}*/

class GetCurrentTime {
    public String getCurrentTime() {
        String time= "";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        time= dtf.format((now));
        return time;
    }
}
class GetUserInfo {
    public String getUsersInfo(String userID, String search) {
        String info= "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE userID= "+userID); //userID= 1;
            while (resultSet.next()) {
                info= resultSet.getString(search); //search= "name"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
class GetOneColumnInfoFromUsers {
    public String getOneColumnInfoFromUsers(String column) {
        String info= " ";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT "+column+" FROM orders ORDER BY "+column);
            while (resultSet.next()) {
                info= resultSet.getString(column);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return info;
    }
}
class GetOrdersInfo {
    public String getOrdersInfo(String orderID, String search) {
        String info= "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE orderID="+orderID); //piem. orderID= 1;
            while (resultSet.next()) {
                info= resultSet.getString(search); //search= piem. "name"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
class GetLastOrdersInfo {
    public String getLastOrdersInfo(String orderID, String search) {
        String info= "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders orderID="+orderID); //piem. userID= 1;
            while (resultSet.next()) {
                info= resultSet.getString(search); //search= piem. "name"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public String getLastOrdersInfo(String argument1, String argument2, String argument3) {
        String info= "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from OrderS WHERE userID="+ argument1+" ORDER BY "+argument2+" DESC LIMIT 1"); //piem. userID= 1;
            while (resultSet.next()) {
                info= resultSet.getString(argument3); //search= piem. "name"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
class GetTextFromFile {

    public String getTextFromFile(String textPath) {
        String textFromFile= "";
        Scanner sc;
        {
            try {
                sc = new Scanner(Path.of(textPath), StandardCharsets.UTF_8);
                sc.useDelimiter("$^");
                textFromFile = sc.next(); sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return textFromFile;
    }
}
class WriteTextToFile {
    public void writeTextToFile(String address, String data) {
        File file = new File(address);
        try {
            FileWriter fileWriter = new FileWriter(file.getPath());
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception f) {
            System.out.println("Kautkas nogāja greizi.. " + f);
        }
    }
}

class GetOrderPrice {
    public double getOrderPrice(String boxes, String floor) {
        double orderPrice= 0.00, orderBoxPrise= 1.00;
        int orderBoxes= Integer.parseInt(boxes), orderFloor= Integer.parseInt(floor);
        if(orderBoxes < 100) {
            orderBoxPrise= 1.00;
        } else if(orderBoxes >= 100 && orderBoxes < 300) {
            orderBoxPrise= 0.90;
        } else if(orderBoxes >= 300 && orderBoxes < 500) {
            orderBoxPrise= 0.80;
        } else if(orderBoxes >= 500 && orderBoxes < 1000) {
            orderBoxPrise= 0.70;
        } else if(orderBoxes >= 1000 && orderBoxes < 10000) {
            orderBoxPrise= 0.60;
        } else if(orderBoxes >= 10000) {
            orderBoxPrise= 0.50;
        } else {
            System.out.println("Upsī, kļūdiņa! ");
        }

        if(orderFloor < 1) {
            orderPrice= (orderBoxes*orderBoxPrise)+((int)orderBoxes/30)*15;
        } else{
            orderPrice= (orderBoxes*orderBoxPrise)+(((int)orderBoxes/30)*orderFloor)*15;
        }
        if(orderBoxes < 30) {
            orderPrice+= 7;
        }
        return orderPrice;
    }
}

class UpdateDatabase{
    public void updateDatabase(String OrderID, String note, boolean statuss) {
        String accept= "Akceptēts!";
        String decline= "Atteikts!";
        String answer= "";
        if(statuss== true) {
            answer= accept;
        } else{
            answer= decline;
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            PreparedStatement preparedStmt= connection.prepareStatement("UPDATE orders SET accept = ? WHERE OrderID = ?");
            PreparedStatement preparedStmt2= connection.prepareStatement("UPDATE orders SET admin_note = ? WHERE OrderID = ?");
            preparedStmt.setString(1, answer);
            preparedStmt.setString(2, OrderID);
            preparedStmt2.setString(1, note);
            preparedStmt2.setString(2, OrderID);
            preparedStmt.executeUpdate();
            preparedStmt2.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MySQL_Shortcuts{
    public String mySQL_JDBC_Shortcut1() {
        return "Connection connection = DriverManager.getConnection(\"jdbc:mysql://localhost:3306/JAVA_IT\", \"root\", \"e6127609-\");\n" +
                "\n" +
                "            Statement statement = connection.createStatement();";
    }
}

