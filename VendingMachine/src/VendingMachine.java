import java.util.Scanner;

public class VendingMachine {
    public int coin;

    public void displayProducts(Product[] Products) {
        System.out.println("Produse disponibile, alegeti cate unul pe rand:");
        for (int i = 0; i < Products.length; i++)
            System.out.println(Products[i].name + "(" + i + ") - " + Products[i].price + " credite");
    }

    public void insertCoin(int credit) {
        this.coin += credit;
    }
    public void displayCredit() {
        System.out.println("Aveti " + coin + " credit/e");
    }

    public void UserMenu() {
        displayCredit();
        System.out.println("Ce doriti sa faceti?");
        System.out.println("8. Adaugati credit/e");
        System.out.println("9. Iesire\n");
        //  System.out.println("Alegeti un produs: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int credit;
        int selection = -1;
        Product[] Products = new Product[4];
        Products[0] = new Product("Coca-Cola", 5);
        Products[1] = new Product("Pepsi", 4);
        Products[2] = new Product("Fanta", 5);
        Products[3] = new Product("Apa", 3);
        VendingMachine V = new VendingMachine();

        System.out.println("Adaugati credit/e");
        credit = scanner.nextInt();
        V.insertCoin(credit);

        while (selection <= 9) {
            V.UserMenu();
            V.displayProducts(Products);
            selection = scanner.nextInt();
            switch (selection) {
                case 8:
                    System.out.println("Adaugati credit/e");
                    credit = scanner.nextInt();
                    V.insertCoin(credit);
                    //V.displayCredit();
                    break;

                case 9:
                    System.out.println("Va multumim ca ati folosit serviciile noastre");
                    selection = 10;
                    break;
                default:
                    if (selection >= Products.length) {
                        System.out.println("Nu se poate");
                    } else {
                        if (V.coin < Products[selection].price) {
                            System.out.println("Credit insuficient");
                        } else {
                            V.coin -= Products[selection].price;
                            //V.displayCredit();
                            System.out.println("Ati ales " + Products[selection].name);
                        }
                    }
            }
        }


    }
}
