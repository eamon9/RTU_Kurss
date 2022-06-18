package RTU_JAVA_kurss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyJDBC {

    public int userID;

    public static void main(String[] args) {

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select UserID from OrderS");
            //ResultSet resultSet = statement.executeQuery("INSERT INTO `people`.`people`(`id`,`username`) VALUES(3, 'sanchezz9');");


            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                //System.out.println(resultSet.getString("address"));
                //System.out.println(resultSet.getString("floor"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

