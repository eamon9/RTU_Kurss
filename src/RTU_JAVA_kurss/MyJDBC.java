package RTU_JAVA_kurss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC {
    public static void main(String[] args) {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");

            Statement statement = connection.createStatement();

            //ResultSet resultSet = statement.executeQuery("select * from OrderS WHERE userID= 2");
            //ResultSet resultSet = statement.executeQuery("select * from OrderS WHERE userID=2 ORDER BY orderID DESC LIMIT 1");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE orderID=2");
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

