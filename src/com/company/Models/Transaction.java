package com.company.Models;

public class Transaction {
    private int transactionID;
    private int fromUserID;
    private int toUserID;
    private double amount;

    public Transaction(int fromID, int toID, double amount) {
        setFromUserID(fromID);
        setToUserID(toID);
        setAmount(amount);
    }

    public Transaction(int transactionID, int fromID, int toID, double amount) {
        this(fromID, toID, amount);
        this.transactionID = transactionID;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getToUserID() {
        return toUserID;
    }

    public void setToUserID(int toUserID) {
        this.toUserID = toUserID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
