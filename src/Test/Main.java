package Test;

public class Main {

    public static void main(String[] args) {
        /*Scanner fromKey = new Scanner(System.in);
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
        }*/
        /*String textField1= "Reiz sen senos laikos dzīvoja vilks, kam ļoti gribējās nobaudīt kaimiņos redzmos sivēnus! Vien uz viņiem paskatoties, siekals tecēja aumaļām!";
        String textField2= "Kad man vēl nebija 18 gadi, es mēdzu no tēva zagt cigaretes un kopā ar labāko draugu, šķūnī smēķējām, vienu cigareti uz diviem! Un mēs viens otram apsolījāmies pie šīs tradīcojas palikt mūžigi!";
        String textField3= "Tā kā bija senāk nebūs vairs nekad, tā man teica mana vecāmāte, kjura bija smēlusies labi daudz dzīves gudrību un tas viņai ļāva būt oriģinālai!";
        String textField4= "Šis nu gan nav nekas!";
        String textField5= "Kādreiz man maksāja piecus eirīšus, par to, ka es sanesu malku mājās!";




    }

    public static String substringTextToGoodLook(String text) {
        if(text.length()<30) {
            System.out.println(text);
        } else if(text.length()>=30 && text.length()<60) {
            System.out.println(text.substring(0, 30));
            System.out.println(text.substring(30));
        } else if(text.length()>=60 && text.length()<90) {
            System.out.println(text.substring(0, 30));
            System.out.println(text.substring(30, 60));
            System.out.println(text.substring(60));
        } else if(text.length()>=90 && text.length()<120) {
            System.out.println(text.substring(0, 30));
            System.out.println(text.substring(30, 60));
            System.out.println(text.substring(60, 90));
            System.out.println(text.substring(90));
        } else if(text.length()>=120 && text.length()<150) {
            System.out.println(text.substring(0, 30));
            System.out.println(text.substring(30, 60));
            System.out.println(text.substring(60, 90));
            System.out.println(text.substring(90, 120));
            System.out.println(text.substring(120));
        } else if(text.length()>=150 && text.length()<180) {
            System.out.println(text.substring(0, 30));
            System.out.println(text.substring(30, 60));
            System.out.println(text.substring(60, 90));
            System.out.println(text.substring(90, 120));
            System.out.println(text.substring(120, 150));
            System.out.println(text.substring(150));
        } else if(text.length()>=180 && text.length()<210) {
            System.out.println(text.substring(0, 30));
            System.out.println(text.substring(30, 60));
            System.out.println(text.substring(60, 90));
            System.out.println(text.substring(90, 120));
            System.out.println(text.substring(120, 150));
            System.out.println(text.substring(150, 180));
            System.out.println(text.substring(180));
        } else if(text.length()>=210 && text.length()<240) {
            System.out.println(text.substring(0, 30));
            System.out.println(text.substring(30, 60));
            System.out.println(text.substring(60, 90));
            System.out.println(text.substring(90, 120));
            System.out.println(text.substring(120, 150));
            System.out.println(text.substring(150, 180));
            System.out.println(text.substring(180, 210));
            System.out.println(text.substring(210));
        }
        return text;
    }

    public static boolean checkIfLastCharIsEmpty(String text) {
        boolean isEmpty;
        if(text.substring(text.length()-1).equals(" ")) {
            isEmpty= true;
        } else{
            isEmpty= false;
        }
        return isEmpty;
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
    }*/

    }
}

