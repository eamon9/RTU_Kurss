package RTU_JAVA_kurss.YouNeedThis;

import java.text.DecimalFormat;

public class GetOrderPrice {
    public String getOrderPrice(String boxes, String floor) {
        DecimalFormat dc = new DecimalFormat("0.00");
        double orderPrice, orderBoxPrise;
        int orderBoxes = Integer.parseInt(boxes), orderFloor = Integer.parseInt(floor);
        if(orderBoxes < 100) {
            orderBoxPrise= 1.00;
        } else if (orderBoxes < 300) {
            orderBoxPrise = 0.90;
        } else if (orderBoxes < 500) {
            orderBoxPrise = 0.80;
        } else if (orderBoxes < 1000) {
            orderBoxPrise = 0.70;
        } else if (orderBoxes < 10000) {
            orderBoxPrise = 0.60;
        } else {
            orderBoxPrise = 0.50;
        }

        if (orderFloor < 1) {
            orderPrice = (orderBoxes * orderBoxPrise) + ((int)orderBoxes / 30) * 15;
        } else {
            orderPrice = (orderBoxes * orderBoxPrise) + (((int)orderBoxes / 30) * 15) * orderFloor;
        }
        if (orderBoxes < 30) {
            orderPrice += 7;
        }
        return dc.format(orderPrice);
    }
}
