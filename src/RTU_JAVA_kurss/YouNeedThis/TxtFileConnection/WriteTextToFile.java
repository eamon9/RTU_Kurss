package RTU_JAVA_kurss.YouNeedThis.TxtFileConnection;

import java.io.File;
import java.io.FileWriter;

public class WriteTextToFile {
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
