package RTU_JAVA_kurss;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

class GetUserInfo {

    public String getUsersInfo(String userID, String search) {
        String info= "";

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JAVA_IT", "root", "e6127609-");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users WHERE userID= "+userID); //userID= 1;

            while (resultSet.next()) {
                info= resultSet.getString(search); //search= "name"
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return info;
    }
}

class GetTextFromFile {

    public String getTextFromFile(String textPath) {
        String textFromFile= "";
        Scanner sc;
        {
            try {
                sc = new Scanner(Path.of(textPath), StandardCharsets.UTF_8);
                sc.useDelimiter("$^");
                textFromFile = sc.next(); sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return textFromFile;
    }
}

class WriteTextToFile {
    public void writeTextToFile(String address, String data) {
        File file = new File(address);
        try {
            FileWriter fileWriter = new FileWriter(file.getPath());
            fileWriter.write(data);
            fileWriter.close();
        } catch (Exception f) {
            System.out.println("Kautkas nogāja greizi.. " + f);
        }
    }
}
