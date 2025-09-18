# Banken

## Översikt
Banken är en enkel konsolebaserad bankapplikation som kan hantera många olika användare
samt också låta alla användare att sätta in och ta ut pengar. Denna applikation är skriven i java
så att man behöver har JDK (Java Development Kit) på datorn denna applikation var skriven i JDK 21.0.8

## Global variabler
```
static List<String> UserList = new ArrayList();
static String currentUser = null;
static int Money = 0;
static boolean isLoggedIn = false;
```
**Userlist**: lagra användra namn och den PIN-koden i lista i formatet ```username:pin```
**currentUser**: Håller reda på vilket användare som är inloggad
**Money**: Håller koll temporät hur mycket pengar användaren har
**isLoggedIn**: Håller reda på om användar är in loggad eller inte

## Methoder
