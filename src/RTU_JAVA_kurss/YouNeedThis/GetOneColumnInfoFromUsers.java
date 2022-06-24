package RTU_JAVA_kurss.YouNeedThis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetOneColumnInfoFromUsers {
    public String getOneColumnInfoFromUsers(String column) {
        String info = " ";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT " + column + " FROM orders ORDER BY " + column);
            while (resultSet.next()) {
                info = resultSet.getString(column);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return info;
    }
}
