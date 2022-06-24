package RTU_JAVA_kurss.YouNeedThis;

public class GetOrderPrice {
    public double getOrderPrice(String boxes, String floor) {
        double orderPrice, orderBoxPrise = 1.00;
        int orderBoxes = Integer.parseInt(boxes), orderFloor = Integer.parseInt(floor);
        if (orderBoxes >= 100 && orderBoxes < 300) {
            orderBoxPrise = 0.90;
        } else if (orderBoxes >= 300 && orderBoxes < 500) {
            orderBoxPrise = 0.80;
        } else if (orderBoxes >= 500 && orderBoxes < 1000) {
            orderBoxPrise = 0.70;
        } else if (orderBoxes >= 1000 && orderBoxes < 10000) {
            orderBoxPrise = 0.60;
        } else if (orderBoxes >= 10000) {
            orderBoxPrise = 0.50;
        } else {
            System.out.println("Upsī, kļūdiņa! ");
        }

        if (orderFloor < 1) {
            orderPrice = (orderBoxes * orderBoxPrise) + ((int)orderBoxes / 30) * 15;
        } else {
            orderPrice = (orderBoxes * orderBoxPrise) + (((int)orderBoxes / 30) * orderFloor) * 15;
        }
        if (orderBoxes < 30) {
            orderPrice += 7;
        }
        return orderPrice;
    }
}
