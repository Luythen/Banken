# Banken

## Översikt
Banken är en enkel konsolebaserad bankapplikation som kan hantera många olika användare
samt också låta alla användare att sätta in och ta ut pengar. Denna applikation är skriven i java
så att man behöver har JDK (Java Development Kit) på datorn denna applikation var skriven i JDK 21.0.8

## Använding
Vid varje start så får man skapa en nya användare eftersom programmet spara bara temporät all data.
Och det rekommenderas att man använder en fyrsifrig PIN men man kan ha hur lång PIN som helst.
För att avsluta programmet så behöver man logga ut först sen kan man avsluta programmet

## Global variabler
```
static List<String> UserList = new ArrayList();
static List<String> moneyList = new ArrayList();
static List<String> transactionList = new ArrayList();

static String currentUser = null;
static int Money = 0;
static boolean isLoggedIn = false;
```
+ **Userlist**: lagra användra namn och den PIN-koden i formatet ```username@pin```
+ **moneyList**: lagra användra namn och den saldon användaren har i formatet ```username@saldo```
+ **transactionList**: lagra användarens transaktions historik i formatet ```username@datum@tid@typ@belopp```
+ **currentUser**: Håller reda på vilket användare som är inloggad
+ **Money**: Håller koll temporät hur mycket pengar användaren har
+ **isLoggedIn**: Håller reda på om användar är in loggad eller inte

## Exemple på hur datan sparas
**UserList**
```
["Andreas@1435, "Alex@2490"]
```
**moneyList**
```
["Andreas@5000, "Alex@1250"]
```
**transactionList**
```
["Andreas@2025-09-19@13:00:20@Insättning@5000, Andreas@2025-09-20@14:00:20@Uttag@-1500"]
```

## Methoder

```getIndexFormList(List<String> list)```
+  Hämatar index för den aktulla användaren och används för att uppdatera användarens saldo och PIN
  
```isNumeric(String number)```
+  Kollar ifall användaren har mattat in ett tal och använd som fel kontroll för PIN och insätt och uttag

```deoesUserExits(String username)```
+  Veriferar att användaren finns och om ```true``` får användaren skriv in sin PIN

```getUsersCurrentMoney()```
+  Retunerar in loggad användarnes saldo. används när användaren loggar tillbaka in

```updateUserMoney(int Index)```
+  Uppdaterar listan där alla användarens saldo sparas

```addToUsersTransactions(String type, int amount)```
+  Lägger till transaktion i ```transactionList``` med formatet ```username@datum@tid@typ@belopp```

```depoistMoney(Scanner input)```
+  Tar in användarens insättningsbelopp och updaterar ```Money``` variablen och uppdatera ```moneyList```
+  Loggar transaktionen

```withdrawMoney(Scanner input)```
+  Tar in användarens uttagnings belopp och kontrollerar att användaren har tillräkligt med saldo
+  Loggar transaktionen med negativ belopp

### Användarmenyn
Du behöver ej skriv med **stora** bokstäver för att använda menyn
+  ```[I]``` Sätta in
+  ```[U]``` Ta ut
+  ```[T]``` Transaktion historik
+  ```[B]``` Ändra PIN
+  ```[A]``` Logga UT

### Registering av ny användare & InLoggning
+  Frågar efter användarnamn och sen kollar om den användern finns
+  Om inte får du skapa en PIN
+  Om användaren finns så skriver den in sin PIN
+  Vid skapning av ny användare så får den alltid den saldo på 0
+  ```if (UserList.indexOf(exitOrUsername + "@" + lPin) != -1)``` ser till att PIN koden är rätt för om PIN är fel retuernar ```indexOf``` -1

### Transaktionhistorik
Skriver ut alla transaktion som nuvarande användare har gjort t.ex.
```
Datum: 2025-09-19 tid: 13:00:20 typ: Insättning belopp: 5000
```

## Varför transaktionhistorik, flera användare och PIN kod
Jag valde att implmenter transaktionhistorik, PIN och flera användare
+  Det är intressant med data hantering
+  Det är något som en riktig bank applikation skulle ha

Jag valde att spara all använda data i olika List<String> med t.ex. formatet ```Andreas@1543``` för att det skulle vara enkelt att kunna associera vilken användare har vilken PIN, pengar eller transaktionhistorik men jag skulle kunnat till och med kunnat ha använd mig av en Map<String, Int> för att kunna spara användarens pin kod eller saldo dock detta kom jag på när jag skriv denna dokumationen.




