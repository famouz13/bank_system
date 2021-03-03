package com.company.Models;

import java.util.Date;

public class Transaction {
    private int transactionID;
    private int fromUserID;
    private int toUserID;
    private Date transactionDate;
    private double amount;


    public Transaction(int fromUserID, int toUserID, Date transactionDate, double amount) {
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.transactionDate = transactionDate;
        this.amount = amount;
    }

    public Transaction(int transactionID, int fromUserID, int toUserID, Date transactionDate, double amount) {
        this(fromUserID, toUserID, transactionDate, amount);
        this.transactionID = transactionID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public int getToUserID() {
        return toUserID;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }
}
