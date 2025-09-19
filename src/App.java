import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class App {
    static List<String> UserList = new ArrayList<>();
    static List<String> moneyList = new ArrayList<>();
    static List<String> transactionList = new ArrayList<>();

    static String currentUser = null;
    static int Money = 0;

    /* Kollar ifall du har loggat in och på uppstart så det alltid false*/
    static boolean isLoggedIn = false;

    public static int getIndexFromList (List<String> list) {
        for (String index : list) {
            String[] name = index.split("@");
            if (currentUser.equalsIgnoreCase(name[0])) {
                return list.indexOf(index);
            }
        }
        return -1;
    }
    
    /* source: https://www.baeldung.com/java-check-string-number */
    public static boolean isNumeric (String number) {
        if (number == null || number.contains("-")) {
            return false;
        }
        try {
            int i = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void transactionHistoryView (Scanner input) {
        System.out.println("Transaktionhistorik:");
        System.out.println("[B] Tillbaka till huvudmenun");

        for (String transaction : transactionList) {
            String[] transactionFliter = transaction.split("@");
            if (transactionFliter[0].equalsIgnoreCase(currentUser)) {
                System.out.println("Datum: " + transactionFliter[1] + " tid: " + transactionFliter[2] + " transaktiontyp: " + transactionFliter[3] + " Belopp: " + transactionFliter[4]);
            }
        }

        while (true) { 
            String choice = input.next();
            if (choice.equalsIgnoreCase("B")) {
                break;
            }

            System.out.println("Fel försök igen!, [B] Tillbaka till huvudmenun");
        }
    }

    public static void addToUsersTransactions (String type,int amount) {
        /* ["Användanamn"@"Datum"@"Tid"@"Transaktiontyp"@"belopp"] */
        transactionList.add(currentUser + "@" + LocalDate.now() + "@"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + "@" + type + "@" + amount);
    }

    public static int getUsersCurrentMoney () {
        for (String saldo : moneyList) {
            /* gör om till en string array så det blir ["Användanamn", "saldo"] */
            String[] name = saldo.split("@");
            if (currentUser.equalsIgnoreCase(name[0])) {
                return Integer.parseInt(name[1]);
            }
        }
        return 0;
    }

    public static void updateUserMoney (int Index) {
        moneyList.remove(Index);
        moneyList.add(currentUser + "@" + Money);
    }

    public static void withdrawMoney(Scanner input) {
        while (true) { 
            System.out.println("Hej hur mycket pengar skulle du vilja ta ut? skriv då t.ex. 100");
            System.out.println("Din Saldo är " + Money);
            System.out.println("[B] Tillbaka till huvudmenun");

            String choice = input.next();
            if (choice.equalsIgnoreCase("b")) {
                break;
            }
            if (isNumeric(choice)) {
                int wMoney = Integer.parseInt(choice);
                if (wMoney <= Money) {
                    Money = Money - wMoney;
                    updateUserMoney(getIndexFromList(moneyList));
                    addToUsersTransactions("Uttag", -wMoney);
                } else {
                    System.out.println("Du kan inte ta ut mer än vad du har på dit konto");
                }
            } else {
                System.out.println("Fel du kan bara skriv nummer");
            }
        }
    }

    public static void depositMoney (Scanner input) {
        while (true) { 
            System.out.println("Hej hur mycket pengar skulle du vilja sätta in? skriv då t.ex. 100");
            System.out.println("Din Saldo är " + Money);
            System.out.println("[B] Tillbaka till huvudmenun");

            String choice = input.next();
            if (choice.equalsIgnoreCase("b")) {
                break;
            }
            if (isNumeric(choice)) {
                int amount = Integer.parseInt(choice);
                Money = Money + amount;
                updateUserMoney(getIndexFromList(moneyList));
                addToUsersTransactions("Insättning", amount);
            } else {
                System.out.println("Fel du kan bara skriv nummer");
            }
        } 
    }

    public static void userInterface (Scanner input) {
        System.out.println("Välkommen " + currentUser + " din saldo är " + Money);
        System.out.println("[I] Sätt in");
        System.out.println("[U] Ta ut");
        System.out.println("[T] Transaktionshistorik");
        System.out.println("[B] Byt PIN kod");
        System.out.println("[A] Logga ut");
        String co = input.next();
        switch (co.toUpperCase()){
            case "I":
                depositMoney(input);
                break;
            case "U":
                withdrawMoney(input);
                break;
            case "T":
                transactionHistoryView(input);
                break;
            case "B":
                changePINView(input);
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
            String[] name = user.split("@");
            if (username.equalsIgnoreCase(name[0])) {
                return true;
            }
        }

        return false;
    }

    public static void changePINView (Scanner input) {
        boolean correctCurrentPIN = false;
        String currentPIN = "0";

        System.out.println("Vad är din nuvarande PIN");
        while (true) { 
            if (!correctCurrentPIN) {
                currentPIN = input.next();
            } 
            if (UserList.indexOf(currentUser + "@" + currentPIN) != -1) {
                System.out.println("Vad vill du byta din PIN till");
                correctCurrentPIN = true;
                String changedPIN = input.next();
                if (isNumeric(changedPIN)) {
                    UserList.remove(getIndexFromList(UserList));
                    UserList.add(currentUser + "@" + changedPIN);
                    break;
                }

                System.out.println("Fel PIN koden får ej inhållar bokstäver");
            } else {
                System.out.println("Fel PIN försök igen!");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                if (!isLoggedIn) {
                    System.out.println("Välkommen till banken var vänlig och skriv in ditt använda namn");
                    System.out.println("[A] För att avsluta applikation");
                    String exitOrUsername = input.next();

                    if (exitOrUsername.equalsIgnoreCase("A")) {
                        break;
                    }

                    if (doesUserExits(exitOrUsername)) {
                        System.out.println("Skriv in din pinkod");
                        while (true) { 
                            String lPin = input.next();
                            if (UserList.indexOf(exitOrUsername + "@" + lPin) != -1 && isNumeric(lPin)) {
                                currentUser = exitOrUsername;
                                Money = getUsersCurrentMoney();
                                isLoggedIn = true;
                                break;
                            }
                            System.out.println("Fel Pinkod. Försök igen");
                        }
                    } else {
                        currentUser = exitOrUsername;
                        System.out.println("Välkommen ny användare var vänlig att register en pinkod till dit konto");
                        String Pin;
                        while (true) { 
                            Pin = input.next();
                            if (isNumeric(Pin)) {
                                UserList.add(currentUser + "@" + Pin);
                                moneyList.add(exitOrUsername + "@" + Money);
                                isLoggedIn = true;
                                break;
                            }

                            System.out.println("Fel PIN koden får ej inhållar bokstäver");
                        }
                    }
                }

                userInterface(input);
            }
        }
    }
}
