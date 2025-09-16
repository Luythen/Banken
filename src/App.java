import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String User = "Användare";
        int Money = 0;
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Välkommen " + User + " din saldo är " + Money);
            System.out.println("[I] Sätt in");
            System.out.println("[U] Ta ut");
            System.out.println("[A] Avsluta");
            while (true) { 
                switch (input.next()) {
                    case "I":
                        System.out.println("In");
                        break;
                    case "U":
                        System.out.println("Ut");
                        break;
                    case "A":
                        System.out.println("");
                        break;
                    default:
                        System.out.println("Fel försök igen");
                }
            }
        }
    }
}
