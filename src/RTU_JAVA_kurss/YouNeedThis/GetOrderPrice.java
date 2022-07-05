package RTU_JAVA_kurss.YouNeedThis;

import java.text.DecimalFormat;

public class GetOrderPrice {
    public String getOrderPrice(String boxes, String floor) { //divi argumenti
        DecimalFormat dc = new DecimalFormat("0.00"); // lai skaitlim aiz komata ir divas nulles
        double orderPrice, orderBoxPrise; //int 1 , double 1.0
        int orderBoxes = Integer.parseInt(boxes), orderFloor = Integer.parseInt(floor); //From String to Int
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

        if (orderFloor < 1) { //ir lifts orderFloor glabā skaitli 0
            orderPrice = (orderBoxes * orderBoxPrise) + (((int)orderBoxes / 30) * 15); //15eur- paletes cena, 30kastes uz vienas paletes
        } else {
            orderPrice = (orderBoxes * orderBoxPrise) + ((((int)orderBoxes / 30) * 15) * orderFloor);
        }
        if (orderBoxes < 30) {
            orderPrice += 7;
        }
        return dc.format(orderPrice);
    }
}
