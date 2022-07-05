package RTU_JAVA_kurss.YouNeedThis.MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateDatabase {
    public void updateDatabase(String OrderID, String note, boolean statuss) {
        String accept = "Akceptēts!";
        String decline = "Atteikts!";
        String answer;
        if (statuss) {
            answer = accept;
        } else {
            answer = decline;
        }
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "");
            PreparedStatement preparedStmt = connection.prepareStatement("UPDATE orders SET accept = ? WHERE OrderID = ?");
            PreparedStatement preparedStmt2 = connection.prepareStatement("UPDATE orders SET admin_note = ? WHERE OrderID = ?");
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
