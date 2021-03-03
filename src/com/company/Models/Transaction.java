package com.company.Models;

import java.util.Date;

public class Transaction {
    private int transactionID;
    private int fromUserID;
    private int toUserID;
    private Date transactionDate;
    private double amount;
    private String transactionComment;

    private User fromUser;
    private User toUser;


    public Transaction(int fromUserID, int toUserID, Date transactionDate, double amount, String transactionComment) {
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionComment = transactionComment;
    }

    public Transaction(int transactionID, int fromUserID, int toUserID, Date transactionDate, double amount, String transactionComment) {
        this(fromUserID, toUserID, transactionDate, amount, transactionComment);
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

    public String getTransactionComment() {
        return transactionComment;
    }

    public void setTransactionComment(String transactionComment) {
        this.transactionComment = transactionComment;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
