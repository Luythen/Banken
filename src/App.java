import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class App {
    static List<String> UserList = new ArrayList<>();
    static List<String> moneyList = new ArrayList<>();

    static String currentUser = null;
    static int Money = 0;

    /* Kollar ifall du har loggat in och på uppstart så det alltid false*/
    static boolean isLoggedIn = false;

    public static int getIndexFromList (List<String> list) {
        for (String index : list) {
            /* gör om till en string array så det blir ["Användanamn", "saldo"] */
            String[] name = index.split(":");
            if (currentUser.equalsIgnoreCase(name[0])) {
                return list.indexOf(index);
            }
        }
        return -1;
    }

    public static int getUsersCurrentMoney () {
        for (String saldo : moneyList) {
            /* gör om till en string array så det blir ["Användanamn", "saldo"] */
            String[] name = saldo.split(":");
            if (currentUser.equalsIgnoreCase(name[0])) {
                return Integer.parseInt(name[1]);
            }
        }
        return 0;
    }

    public static void updateUserMoney (int Index) {
        moneyList.remove(Index);
        moneyList.add(currentUser + ":" + Money);
    }

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
                updateUserMoney(getIndexFromList(moneyList));
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
            updateUserMoney(getIndexFromList(moneyList));
        } 
    }

    public static void userInterface (Scanner input) {
        System.out.println("Välkommen " + currentUser + " din saldo är " + Money);
        System.out.println("[I] Sätt in");
        System.out.println("[U] Ta ut");
        System.out.println("[A] Logga ut");
        String co = input.next();
        switch (co) {
            case "I":
                depositMoney(input);
                break;
            case "U":
                withdrawMoney(input);
                break;
            case "A":
                isLoggedIn = false;
                Money = 0;
                break;
            default:
                System.out.println("Fel försök igen");
        }
    }

    public static boolean doesUserExits (String username) {
        for (String user : UserList) {
            /* gör om till en string array så det blir ["Användanamn", "pinkod"] */
            String[] name = user.split(":");
            if (username.equalsIgnoreCase(name[0])) {
                return true;
            }
        }

        return false;
    }

    public static void loginView(Scanner input) {
        System.out.println("Välkommen till banken var vänlig och skriv in ditt använda namn");
        String lUser = input.next();

        if (doesUserExits(lUser) && !UserList.isEmpty()) {
            System.out.println("Skriv in din pinkod");
            while (true) { 
                String lPin = input.next();
                if (UserList.indexOf(lUser + ":" + lPin) != -1 && UserList.get(UserList.indexOf(lUser + ":" + lPin)) != null) {
                    currentUser = lUser;
                    Money = getUsersCurrentMoney();
                    isLoggedIn = true;
                    break;
                }
                System.out.println("Fel Pinkod. Försök igen");
            }
        } else {
            currentUser = lUser;
            System.out.println("Välkommen ny användare var vänlig att register en pinkod till dit konto");
            int Pin = Integer.parseInt(input.next());
            UserList.add(lUser + ":" + Pin);
            moneyList.add(lUser + ":" + Money);
            isLoggedIn = true;
        }
    }

    public static void main(String[] args) throws Exception {
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                System.out.println(isLoggedIn);
                if (!isLoggedIn) {
                    loginView(input);
                }

                userInterface(input);
            }
        }
    }
}
