package RTU_JAVA_kurss.YouNeedThis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetLastOrdersInfo {
    public String getLastOrdersInfo(String orderID, String search) {
        String info = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders orderID=" + orderID); //piem. userID= 1;
            while (resultSet.next()) {
                info = resultSet.getString(search); //search= piem. "name"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    public String getLastOrdersInfo(String argument1, String argument2, String argument3) {
        String info = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from OrderS WHERE userID=" + argument1 + " ORDER BY " + argument2 + " DESC LIMIT 1"); //piem. userID= 1;
            while (resultSet.next()) {
                info = resultSet.getString(argument3); //search= piem. "name"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
