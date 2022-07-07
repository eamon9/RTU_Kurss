package RTU_JAVA_kurss.YouNeedThis;

import java.text.DecimalFormat;

public class GetOrderPrice {
    public String getOrderPrice(String boxes, String floor) { //divi argumenti
        DecimalFormat dc = new DecimalFormat("0.00"); // lai skaitlim aiz komata ir divas nulles
        int orderBoxes = Integer.parseInt(boxes), orderFloor = Integer.parseInt(floor), palletSize= 30; //From String to Int
        double orderPrice = 0, orderBoxPrise= 1, palletPrice= 15, smallOrderTax= 7;
        if (orderBoxes < 100) {
            orderBoxPrise = 1.00;
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

        if (orderFloor == 0) { //ir lifts orderFloor glabā skaitli 0
            orderPrice = (orderBoxes * orderBoxPrise) + getLodingCosts(orderBoxes, palletSize, palletPrice, orderFloor);
        } else if(orderFloor >= 1){
            orderPrice = (orderBoxes * orderBoxPrise) + getLodingCosts(orderBoxes, palletSize, palletPrice, orderFloor);
        } else if(orderFloor < 0){
            orderPrice = (orderBoxes * orderBoxPrise) + getLodingCosts(orderBoxes, palletSize, palletPrice, orderFloor);
        } else{
            System.out.println("Kautkas nogāja greizi!");
        }

        if (orderBoxes < palletSize) {
            orderPrice += smallOrderTax;
        }
        return dc.format(orderPrice);
    }

    public double getLodingCosts(int boxes, int palletSize, double palletPrice, int floor) {
        int pallets= boxes/palletSize;
        double fullPrice= pallets*palletPrice;
        if(floor > 0) {
            fullPrice*= floor;
        } else if(floor < 0) {
            fullPrice*= floor-(floor*2);
        }

        return fullPrice;
    }
}
