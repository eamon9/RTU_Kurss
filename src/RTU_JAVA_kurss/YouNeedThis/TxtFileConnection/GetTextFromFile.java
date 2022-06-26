package RTU_JAVA_kurss.YouNeedThis.TxtFileConnection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class GetTextFromFile {

    public String getTextFromFile(String textPath) {
        String textFromFile = "";
        Scanner sc;
        {
            try {
                sc = new Scanner(Path.of(textPath), StandardCharsets.UTF_8);
                sc.useDelimiter("$^");
                textFromFile = sc.next();
                sc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return textFromFile;
    }
}
