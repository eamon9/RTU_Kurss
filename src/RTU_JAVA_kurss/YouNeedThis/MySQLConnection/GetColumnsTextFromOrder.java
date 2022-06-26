package RTU_JAVA_kurss.YouNeedThis.MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetColumnsTextFromOrder {

    public String getColumnsTextFromOrder(String OrderID, String column) {
        //column= izvēlēties izvadāmo lietu, piemēram "accept" vai "admin_note" for OrderID
        String columnName = "";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT "+column+" FROM orders WHERE orderID="+OrderID);
            while (resultSet.next()) {
                columnName= resultSet.getString(column);
                if(columnName== null || columnName.length()== 0) {
                    columnName= "Gaida";
                } else{
                    columnName = resultSet.getString(column);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnName;
    }
}
