package RTU_JAVA_kurss.YouNeedThis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GetCurrentTime {
    public String getCurrentTime() {
        String time;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();
        time = dtf.format((now));
        return time;
    }
}
