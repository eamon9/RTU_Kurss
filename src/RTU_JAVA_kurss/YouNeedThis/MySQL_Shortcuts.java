package RTU_JAVA_kurss.YouNeedThis;

public class MySQL_Shortcuts{
    public String mySQL_JDBC_Shortcut1() {
        return "Connection connection = DriverManager.getConnection(\"jdbc:mysql://localhost:3306/JAVA_IT\", \"root\", \"e6127609-\");\n" +
                "\n" +
                "            Statement statement = connection.createStatement();";
    }
}

