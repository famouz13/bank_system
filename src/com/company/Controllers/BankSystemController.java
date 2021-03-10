package com.company.Controllers;

import com.company.Controllers.Interfaces.IBankSystemController;
import com.company.DataAccessLayer._DAL;
import com.company.Models.*;
import com.company.ServiceLayer._SL;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BankSystemController implements IBankSystemController {
    @Override
    public User authenticateUser(String phone, String password) {
        return _SL.Users.authenticateUser(phone, password);
    }

    @Override
    public User registerUser(String phone, String password, String IIN, String firstName, String lastName) {
        boolean isCreated = _DAL.Users.AddNew(new User(IIN, firstName, lastName, phone, password));
        if (isCreated)
            return _DAL.Users.byPhone(phone);
        return null;
    }

    @Override
    public ArrayList<Card> getUserCards(int userID) {
        return _DAL.Cards.byUserID(userID);
    }

    @Override
    public ArrayList<Credit> getUserCredits(int userID) {
        return _DAL.Credits.byUserID(userID);
    }

    @Override
    public ArrayList<Transaction> getUserTransactions(int userID) {
        return _DAL.Transactions.byUserID(userID);
    }

    @Override
    public boolean Transfer(int fromID, int toID, String fromCard, String toCard, double amount, String comment) {
        Transaction tr = new Transaction(fromID, toID, fromCard, toCard, amount, comment);
        return _DAL.Transactions.transfer(tr);
    }

    @Override
    public boolean addNewCredit(int userId, int year, double amount, float percent, double loanBalance) {
        Date startDate = Calendar.getInstance().getTime();
        Date endDate = Calendar.getInstance().getTime();
        endDate.setYear(year);

        Credit newCredit = new Credit(userId, startDate, endDate, percent, amount, loanBalance);
        return _DAL.Credits.addNewCredit(newCredit);
    }

    @Override
    public boolean addNewCard(int userId, String cardNumber, String pin, double balance, int cardTypeId) {
        Card newCard = new Card(cardNumber, pin, balance, cardTypeId, userId);
        return _DAL.Cards.createNewUserCard(newCard);
    }

    @Override
    public boolean payCredit(int creditId, double amount) {
        CreditPayment newCreditPayemnt = new CreditPayment(creditId, amount);
        return _DAL.Credits.payCredit(newCreditPayemnt);
    }

    @Override
    public ArrayList<CreditPayment> getUserCreditPayments(int creditID) {
        return _DAL.Credits.paymentCreditsByCreditID(creditID);
    }
}
