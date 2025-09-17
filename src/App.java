import java.util.Scanner;

public class App {
    static String User = "Användare";
    static int Money = 0;

    public static void withdrawMoney(Scanner input) {
        while (true) { 
            System.out.println("Hej hur mycket pengar skulle du vilja ta ut? skriv då t.ex. 100");
            System.out.println("[B] Tillbaka till huvudmenun");

            String choice = input.next();
            if (choice.equalsIgnoreCase("b")) {
                break;
            }
            int wMoney = Integer.parseInt(choice);
            if (wMoney <= Money) {
                Money = Money - wMoney;
            } else {
                System.out.println("Du kan inte ta ut mer än vad du har på dit konto");
            }
        }
    }

    public static void depositMoney (Scanner input) {
        while (true) { 
            System.out.println("Hej hur mycket pengar skulle du vilja sätta in? skriv då t.ex. 100");
            System.out.println("[B] Tillbaka till huvudmenun");

            String choice = input.next();
            if (choice.equalsIgnoreCase("b")) {
                break;
            }

            Money = Money + Integer.parseInt(choice);
        } 
    }

    public static void userInterface (Scanner input) {
        System.out.println("Välkommen " + User + " din saldo är " + Money);
        System.out.println("[I] Sätt in");
        System.out.println("[U] Ta ut");
        System.out.println("[A] Avsluta");
        String co = input.next();
        switch (co) {
            case "I":
                depositMoney(input);
                break;
            case "U":
                withdrawMoney(input);
                break;
            case "A":
                System.out.println("");
                break;
            default:
                System.out.println("Fel försök igen");
        }
    }

    public static void main(String[] args) throws Exception {
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                userInterface(input);
            }
        }
    }
}
