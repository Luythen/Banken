import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String User = "Användare";
        int Money = 0;
        try (Scanner input = new Scanner(System.in)) {
            while (true) { 
                System.out.println("Välkommen " + User + " din saldo är " + Money);
                System.out.println("[I] Sätt in");
                System.out.println("[U] Ta ut");
                System.out.println("[A] Avsluta");
            }
        }
    }
}
