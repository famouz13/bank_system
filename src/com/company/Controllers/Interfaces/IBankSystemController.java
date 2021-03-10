package com.company.Controllers.Interfaces;

import com.company.DataAccessLayer._DAL;
import com.company.Models.*;

import java.util.ArrayList;

public interface IBankSystemController {
    User authenticateUser(String phone, String password);

    User registerUser(String phone, String password, String IIN, String firstName, String lastName);

    ArrayList<Card> getUserCards(int userID);

    ArrayList<Credit> getUserCredits(int userID);

    ArrayList<Transaction> getUserTransactions(int userID);

    boolean Transfer(int fromID, int toID, String fromCard, String toCard, double amount, String comment);


    boolean addNewCredit(int userId, int year, double amount, float percent, double loanBalance);

    boolean addNewCard(int userId, String cardNumber, String pin, double balance, int cardTypeId);

    boolean payCredit(int creditId, double amount);

    ArrayList<CreditPayment> getUserCreditPayments(int creditID);

}
