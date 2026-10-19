
import java.util.Scanner;

abstract class Payment {
    double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    abstract double calculateAmount();

    String getType() {
        return "Payment";
    }
}

class Card extends Payment {
    Card(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount + amount * 0.02;
    }

    String getType() {
        return "CARD";
    }
}

class Wallet extends Payment {
    Wallet(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount + amount * 0.01;
    }

    String getType() {
        return "WALLET";
    }
}

class BankTransfer extends Payment {
    BankTransfer(double amount) {
        super(amount);
    }

    double calculateAmount() {
        return amount;
    }

    String getType() {
        return "BANKTRANSFER";
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        double total = 0;

        for (int i = 0; i < n; i++) {
            String type = sc.next();
            double amount = sc.nextDouble();

            Payment p;

            if (type.equals("CARD")) {
                p = new Card(amount);
            } else if (type.equals("WALLET")) {
                p = new Wallet(amount);
            } else {
                p = new BankTransfer(amount);
            }

            double result = p.calculateAmount();

            System.out.printf("%s: %.2f%n",
                    p.getType(), result);

            total += result;
        }

        System.out.printf("Total: %.2f%n", total);
        sc.close();
    }
}
