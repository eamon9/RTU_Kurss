package RTU_JAVA_kurss.YouNeedThis.MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetOrdersInfo {
    public String getOrdersInfo(String orderID, String search) {
        String info = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders WHERE orderID=" + orderID); //piem. orderID= 1;
            while (resultSet.next()) {
                info = resultSet.getString(search); //search= piem. "name"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
}
