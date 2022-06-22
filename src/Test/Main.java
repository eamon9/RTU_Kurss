package Test;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner fromKey = new Scanner(System.in);
        boolean check= true;
        while (check) {
            System.out.print("Cik daudz kastes vēlaties uzglabāt?:");
            int skaits = fromKey.nextInt();
            if (skaits <= 0) {
                System.out.println("Kļūda: Ievadiet derīgu skaitli!");
                check = true;
            } else if(skaits >= 1000000) {
                System.out.println("Kļūda: Ievadiet derīgu skaitli!");
            } else {
                System.out.println(getPriceForBoxes(skaits));
                check = false;
            }
        }
    }

    public static String getPriceForBoxes (int skaits) {
        double price= 1.0;
        if (skaits > 0 && skaits <= 100) {
            price= 1.0;
        }
        else if (skaits >= 101 && skaits <= 200) {
            price= 0.9;
        }
        double fullPrice = skaits * price;
        return "Par " + skaits + " kastēm kopā: " + fullPrice + " € +PVN21%";
    }
}

