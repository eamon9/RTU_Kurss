package RTU_JAVA_kurss.YouNeedThis;

import java.text.DecimalFormat;

public class GetMonthPrise {
    public String getMonthPrise(String boxes) {
        DecimalFormat dc = new DecimalFormat("0.00");
        dc.format(Integer.parseInt(boxes)* 0.6);
        return dc.format(Integer.parseInt(boxes)* 0.6);
    }
}
