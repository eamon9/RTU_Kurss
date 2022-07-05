package RTU_JAVA_kurss.YouNeedThis.MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetLastOrdersInfo {
    /*public String getOrdersInfo(String orderID, String search) {
        String info = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE orderID=" + orderID); //piem. userID= 1;
            while (resultSet.next()) {
                info = resultSet.getString(search); //search= piem. "name"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }*/

    public String getLastOrdersInfo(String userID, String orderedBY, String i) {
        String info = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from OrderS WHERE userID=" + userID + " ORDER BY " + orderedBY + " DESC LIMIT 1"); //piem. userID= 1;
            while (resultSet.next()) {
                info = resultSet.getString(i); //search= piem. "name"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
