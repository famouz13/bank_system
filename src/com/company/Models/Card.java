package com.company.Models;

public class Card {
    private int cardID;
    private String cardNumber;
    private String PIN;
    private double balance;
    private int cardTypeID;
    private int userID;
    private CardType cardType;

    public Card(String cardNumber, String PIN, double balance, int cardTypeID, int userID) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
        this.balance = balance;
        this.cardTypeID = cardTypeID;
        this.userID = userID;
    }

    public Card(int cardID, String cardNumber, String PIN, double balance, int cardTypeID, int userID) {
        this(cardNumber, PIN, balance, cardTypeID, userID);
        this.cardID = cardID;
    }

    public int getCardID() {
        return cardID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public double getBalance() {
        return balance;
    }

    public int getCardTypeID() {
        return cardTypeID;
    }

    public void setCardTypeID(int cardTypeID) {
        this.cardTypeID = cardTypeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
