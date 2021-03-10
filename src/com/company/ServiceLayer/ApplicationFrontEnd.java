package com.company.ServiceLayer;
import com.company.databases.interfaces.IDB;
import com.company.databases.PostgresDB;
import java.util.Scanner;

import static com.company.DataAccessLayer._DAL.Users.byID;
import static com.company.DataAccessLayer._DAL.Cards.createNewUser;

public class ApplicationFrontEnd {

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
            System.out.println(ANSI_BLUE + "Hello, unauthorized User! " + ANSI_RESET );
            System.out.println();
            System.out.println(
                    ANSI_YELLOW + "1 |  Login" + '\n'+
                    ANSI_GREEN +  "2 |  Register"+'\n'+
                     ANSI_RED + "0 |  Exit") ;
            System.out.print(ANSI_RESET + "Please input the number: ");
            int numberchoice = scanner.nextInt();
            switch (numberchoice) {
                case 0 -> exit();
                case 1 -> LoginUser();
                case 2 -> RegisterUser();
                case 9 -> check(); //by id
                default -> System.out.println(ANSI_RED + "Wrong input type!");
            }
        }

    }
    private static void check(){
        System.out.println(ANSI_PURPLE+ "PLEASE check by ID (for admins): ");
        Integer check=scanner.nextInt();
        byID(check);
    }

    private static void RegisterUser() {
        System.out.println("Please enter your first name:");
        String firstname = scanner.next();
        System.out.println("Please enter your last name:");
        String lastname = scanner.next();
        System.out.println("Please enter your IIN:");
        Integer iin = scanner.nextInt();
        System.out.println("Please enter your phone:");
        Integer phone = scanner.nextInt();

        System.out.println("Please enter password that will be used to login:  ");
        System.out.println("| TIP: password must have at least 8 characters, 1 uppercase, 1 lowercase letter and 1 special character. |");
        String pw;
        while (true) {
            if (scanner.hasNext()) {
                pw = scanner.next();
                if (checkPassword(pw)) {

                    break;
                }
            } else {
                scanner.next();
            }
            System.out.println(ANSI_RED + "You entered an invalid password, please re-try: ");


        }

        createNewUser(firstname, lastname, iin, phone, pw);

        System.out.println(ANSI_PURPLE+ "YOU SUCCESSFULLY CREATED NEW ACCOUNT");



    }


    private static boolean checkPassword(String pw) {
        int count = 0, count2=0, count3=0, count4=0;
        if (pw.length() >= 8) {
            for (int i = 0; i < pw.length(); i++) {
                if (pw.charAt(i) >= 'a' && pw.charAt(i) <= 'z' && count == 0) {
                    count++;
                }
                if (pw.charAt(i) >= 'A' && pw.charAt(i) <= 'Z' && count2 == 0) {
                    count2++;
                }
                if (pw.charAt(i) >= '0' && pw.charAt(i) <= '9' && count3==0) count3++;
                if ( (pw.charAt(i) == '!' || pw.charAt(i) == '$' || pw.charAt(i) == '@' || pw.charAt(i) == '^') && count4==0)
                    count4++;
            }

        }
        if((count+count2+count3+count4)==4){
            return true;
        }
        else
            return false;
    }
    public static  String phone_login;
    private static void LoginUser(){
        System.out.println(ANSI_YELLOW + "Please enter your phone numbers: " +ANSI_RESET);
         phone_login = scanner.next();
        System.out.println(ANSI_YELLOW + "Please enter your password: " + ANSI_RESET);
        String pw = scanner.next();

        var res =_SL.Users.authenticateUser(phone_login, pw);
        if(res!=null){
            System.out.println("Loading...\n");
LoginFrontEnd.start();
        }
        else{
            System.out.println("You typed wrong password or phone!");
        }

}







    private static void exit() {
        System.exit(0);
    }

}
