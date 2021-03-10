package com.company.ServiceLayer;

import com.company.databases.PostgresDB;
import com.company.databases.interfaces.IDB;
import com.company.DataAccessLayer._DAL.Users;
import com.company.ServiceLayer.ApplicationFrontEnd;
import java.util.Scanner;

import static com.company.DataAccessLayer._DAL.Cards.createNewUserCard;
import static com.company.ServiceLayer.ApplicationFrontEnd.phone_login;
import static com.company.DataAccessLayer._DAL.Users.byPhone;
import static com.company.ServiceLayer._SL.Cards.generateCardNumber;
import static com.company.ServiceLayer._SL.Cards.generatePIN;


public class LoginFrontEnd {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IDB postgres = new PostgresDB();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void start() {

        while (true) {
            System.out.println(ANSI_BLUE + "Hello,  "+ byPhone(phone_login).getFirstName()+" " +byPhone(phone_login).getLastName() + "! " + ANSI_RESET);
            System.out.println();
            System.out.println(
                    ANSI_GREEN + "1 | Create new card" + '\n' +
                            ANSI_GREEN + "2 | Take credit" + '\n' +
                            "3 | Pay for credit\n" +
                            "4 | All transactions\n" +
                            "5 | Make transaction by phone\n" +
                            "6 | Change PIN code \n" +
                            ANSI_RED + "0 | Exit");
            System.out.print(ANSI_RESET + "Please input the number: ");
            int numberchoice = scanner.nextInt();
            switch (numberchoice) {
                case 0 -> exit();
                case 1 -> createCard();
                case 2 -> exit();
                case 3 -> exit();
                case 4 -> exit();
                case 5 -> exit();
                case 6 -> exit();

                default -> System.out.println(ANSI_RED + "Wrong input type!");
            }
        }

    }
    public static void createCard(){
        String newcard = generateCardNumber();
        String pin = generatePIN();
        System.out.println(ANSI_PURPLE+"Please choose which card do you prefer: \n 1 - HalykBank \n2 - Kaspi");
        Integer typeCardid=scanner.nextInt();
        createNewUserCard(newcard, pin, typeCardid, byPhone(phone_login).getUserID() );
        System.out.println("SUCCESS! Card was created...\n\n");
    }
    private static void exit() {
        System.exit(0);
    }

}
