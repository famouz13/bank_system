package com.company.Models;

public class User {
    private int userID;
    private String IIN;
    private String PIN;
    private String cardNumber;
    private double Balance;

    public User(String IIN, String PIN, String cardNumber, double Balance) {
        setIIN(IIN);
        this.PIN = PIN;
        this.cardNumber = cardNumber;
        this.Balance = Balance;
    }

    public User(int userID, String IIN, String PIN, String cardNumber, double Balance) {
        this(IIN, PIN, cardNumber, Balance);
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public String getIIN() {
        return IIN;
    }

    public void setIIN(String IIN) {
        this.IIN = IIN;
    }

    public String getPIN() {
        return PIN;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public double getBalance() {
        return Balance;
    }

}
