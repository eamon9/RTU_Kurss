package RTU_JAVA_kurss;

import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        /*try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            //ResultSet resultSet= statement.executeQuery("UPDATE orders SET accept = ? WHERE OrderID = ?");
            PreparedStatement preparedStmt= connection.prepareStatement("UPDATE orders SET accept = ? WHERE OrderID = ?");
            PreparedStatement preparedStmt2= connection.prepareStatement("UPDATE orders SET admin_note = ? WHERE OrderID = ?");
            preparedStmt.setString(1, "Akceptēts");
            preparedStmt.setString(2, "52");
            preparedStmt2.setString(1, "Jums ir izveidojies parāds!");
            preparedStmt2.setString(2, "52");
            preparedStmt.executeUpdate();
            preparedStmt2.executeUpdate();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");

            Statement statement = connection.createStatement();

            //ResultSet resultSet = statement.executeQuery("select * from OrderS WHERE userID= 2");
            //ResultSet resultSet = statement.executeQuery("select * from OrderS WHERE userID=2 ORDER BY orderID DESC LIMIT 1");
            ///ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE orderID=2");
            ResultSet resultSet = statement.executeQuery("SELECT OrderId FROM orders WHERE userID=3 ORDER BY OrderID");
            //ResultSet resultSet = statement.executeQuery("INSERT INTO `people`.`people`(`id`,`username`) VALUES(3, 'sanchezz9');");


            while (resultSet.next()) {
                System.out.println(resultSet.getString("orderID"));
                //System.out.println(resultSet.getString("address"));
                //System.out.println(resultSet.getString("floor"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

