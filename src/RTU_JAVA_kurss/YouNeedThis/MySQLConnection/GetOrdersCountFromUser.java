package RTU_JAVA_kurss.YouNeedThis.MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetOrdersCountFromUser {
    public String getOrdersCountFromUser(String UserID) {
        String tableSize= "";
        try { // saskaita cik kopā ir klientu ir saglabāti datubāzē
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet getColumnSize = statement.executeQuery("SELECT COUNT(*) FROM orders WHERE UserID="+UserID);
            while (getColumnSize.next()) {
                tableSize = getColumnSize.getString("COUNT(*)");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tableSize;
    }
}