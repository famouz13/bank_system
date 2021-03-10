package com.company.Views;

import com.company.Controllers.BankSystemController;
import com.company.Controllers.Interfaces.IBankSystemController;
import com.company.DataAccessLayer._DAL;
import com.company.Models.User;
import com.company.ServiceLayer._SL;
import com.company.Views.interfaces.IBankSystemView;

import java.util.Scanner;

public class BankSystemConsoleView implements IBankSystemView {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    IBankSystemController controller;
    private User currentUser;
    private Scanner scanner = new Scanner(System.in);

    public BankSystemConsoleView(IBankSystemController controller) {
        this.controller = controller;
    }

    public void startApp() {
        while (this.currentUser == null) {
            authMenu();
        }
        System.out.println(this.currentUser);
        System.out.println(ANSI_GREEN + "AUTHENTICATED");
        while (this.currentUser != null) {
            accountMenu();
        }
    }

    @Override
    public void authMenu() {
        try {
            System.out.println(ANSI_GREEN + "1.Login \n2.Register\n" + ANSI_RED + "3.Exit");
            int option = Integer.parseInt(this.scanner.nextLine());
            switch (option) {
                case 1: {
                    System.out.println(ANSI_PURPLE + "Phone:");
                    String phone = this.scanner.nextLine();
                    System.out.println(ANSI_PURPLE + "Password:");
                    String password = this.scanner.nextLine();
                    this.currentUser = this.controller.authenticateUser(phone, password);
                    break;
                }
                case 2: {
                    System.out.println(ANSI_PURPLE + "IIN:");
                    String iin = this.scanner.nextLine();
                    System.out.println(ANSI_PURPLE + "firstName:");
                    String firstName = this.scanner.nextLine();
                    System.out.println(ANSI_PURPLE + "lastName:");
                    String lastName = this.scanner.nextLine();
                    System.out.println(ANSI_PURPLE + "Phone:");
                    String Phone = this.scanner.nextLine();
                    System.out.println(ANSI_PURPLE + "password:");
                    String password = this.scanner.nextLine();
                    if (!checkPassword(password))
                        throw new IllegalArgumentException("| TIP: password must have at least 8 characters, 1 uppercase, 1 lowercase letter and 1 special character. |");
                    this.currentUser = this.controller.registerUser(Phone, password, iin, firstName, lastName);
                    break;
                }
                case 3:
                    System.exit(0);
                default:
                    System.out.println(ANSI_RED + "Only option 1 and 2 allowed");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void accountMenu() {
        try {
            System.out.println(ANSI_BLUE + "1.Account info\n" +
                    "2.My cards\n" +
                    "3.My Credits\n" +
                    "4.My Transactions\n" +
                    "5.New transaction\n" +
                    "6.New Credit\n" +
                    "7.New card\n" +
                    "8.Pay for credit \n" +
                    "9.My credit payments");

            int option = Integer.parseInt(this.scanner.nextLine());

            switch (option) {
                case 1:
                    showUserInfo();
                    break;
                case 2:
                    showUserCards();
                    break;
                case 3:
                    showUserCredits();
                    break;
                case 4:
                    showUserTransactions();
                    break;
                case 5:
                    showCreateNewTransaction();
                    break;
                case 6:
                    showAddCreditMenu();
                    break;
                case 7:
                    showAddNewCardMenu();
                    break;
                case 8:
                    showPayCreditMenu();
                    break;
                case 9:
                    showCreditPayments();
                default:
                    System.out.println(ANSI_RED + "Unsupported option");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void showCreditPayments() {
        showUserCredits();
        System.out.println(ANSI_PURPLE + "Enter credit id: ");
        int id = Integer.parseInt(this.scanner.nextLine());

        System.out.println(ANSI_WHITE + "----------CREDIT PAYMENTS----------");
        for (var payment : this.controller.getUserCreditPayments(id)) {
            System.out.println(payment);
        }
        System.out.println(ANSI_WHITE + "------------------------");
    }

    @Override
    public void showUserCards() {
        System.out.println(ANSI_WHITE + "----------CARDS----------");
        for (var card : this.controller.getUserCards(this.currentUser.getUserID())) {
            System.out.println(card);
        }
        System.out.println(ANSI_WHITE + "------------------------");
    }

    @Override
    public void showUserInfo() {
        System.out.println(this.currentUser);
    }

    @Override
    public void showUserCredits() {
        System.out.println(ANSI_WHITE + "----------CREDITS----------");
        for (var credit : this.controller.getUserCredits(this.currentUser.getUserID())) {
            System.out.println(credit);
        }
        System.out.println(ANSI_WHITE + "------------------------");
    }

    @Override
    public void showAddCreditMenu() {
        try {
            System.out.println(ANSI_PURPLE + "Enter credit time (years):");
            int year = Integer.parseInt(this.scanner.nextLine());
            System.out.println(ANSI_PURPLE + "Enter amount");
            double amount = Double.parseDouble(this.scanner.nextLine());
            float percent = 5.5f;

            System.out.println(ANSI_WHITE + "-------------------------------------------");
            System.out.println(ANSI_RESET + "Percent for this amount will be 5.5% ");
            System.out.println(ANSI_RESET + "Your loan balance is " + (amount + ((amount * percent) / 100)) + " " + "Loan term: " + year);
            System.out.println(ANSI_WHITE + "-------------------------------------------");

            boolean result = this.controller.addNewCredit(this.currentUser.getUserID(), year, amount, percent, (amount + ((amount * percent) / 100)));
            if (result)
                System.out.println(ANSI_GREEN + "SUCCESSFUL");
            else
                System.out.println(ANSI_RED + "ERROR");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showPayCreditMenu() {
        System.out.println(ANSI_PURPLE + "Enter id of your credit: ");
        showUserCredits();
        int id = Integer.parseInt(this.scanner.nextLine());
        System.out.println(ANSI_PURPLE + "Enter amount: ");
        double amount = Double.parseDouble(this.scanner.nextLine());

        boolean result = this.controller.payCredit(id, amount);
        if (result)
            System.out.println(ANSI_GREEN + "SUCCESSFUL");
        else
            System.out.println(ANSI_RED + "ERROR");
    }

    @Override
    public void showAddNewCardMenu() {
        try {
            System.out.println(ANSI_PURPLE + "Please choose which card do you prefer: 1.Credit 2.Debit");
            int type = Integer.parseInt(this.scanner.nextLine());
            String cardNumber = _SL.Cards.generateCardNumber();
            String PIN = _SL.Cards.generatePIN();

            System.out.println(ANSI_BLUE + "Your card number: " + cardNumber);
            System.out.println(ANSI_BLUE + "Your card PIN code: " + PIN);

            boolean result = this.controller.addNewCard(this.currentUser.getUserID(), cardNumber, PIN, 0, type);
            if (result)
                System.out.println(ANSI_GREEN + "SUCCESSFUL");
            else
                System.out.println(ANSI_RED + "ERROR");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showUserTransactions() {
        System.out.println(ANSI_WHITE + "----------TRANSACTIONS----------");
        for (var transaction : this.controller.getUserTransactions(this.currentUser.getUserID())) {
            System.out.println(transaction);
        }
        System.out.println(ANSI_WHITE + "------------------------");
    }

    @Override
    public void showCreateNewTransaction() {
        try {
            showUserCards();
            System.out.println(ANSI_PURPLE + "Enter card id from which you want send money");
            int cardID = Integer.parseInt(this.scanner.nextLine());
            System.out.println(ANSI_PURPLE + "Enter user phone to which you want send money");
            String phone = this.scanner.nextLine();
            System.out.println(ANSI_PURPLE + "Enter card number to which you want send money");
            String sendTo = this.scanner.nextLine();
            System.out.println(ANSI_PURPLE + "Enter amount: ");
            double amount = Double.parseDouble(this.scanner.nextLine());
            System.out.println(ANSI_PURPLE + "Enter a comment");
            String comment = this.scanner.nextLine();

            var toUser = _DAL.Users.byPhone(phone).getUserID();
            var card = _DAL.Cards.byID(cardID);

            boolean result = this.controller.Transfer(this.currentUser.getUserID(), toUser, card.getCardNumber(), sendTo, amount, comment);
            if (result)
                System.out.println(ANSI_GREEN + "Transaction successful");
            else
                System.out.println(ANSI_RED + "Transaction error");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    private static boolean checkPassword(String pw) {
        int count = 0, count2 = 0, count3 = 0, count4 = 0;
        if (pw.length() >= 8) {
            for (int i = 0; i < pw.length(); i++) {
                if (pw.charAt(i) >= 'a' && pw.charAt(i) <= 'z' && count == 0) {
                    count++;
                }
                if (pw.charAt(i) >= 'A' && pw.charAt(i) <= 'Z' && count2 == 0) {
                    count2++;
                }
                if (pw.charAt(i) >= '0' && pw.charAt(i) <= '9' && count3 == 0) count3++;
                if ((pw.charAt(i) == '!' || pw.charAt(i) == '$' || pw.charAt(i) == '@' || pw.charAt(i) == '^') && count4 == 0)
                    count4++;
            }

        }
        if ((count + count2 + count3 + count4) == 4) {
            return true;
        } else
            return false;
    }
}