package com.company.Models;

public class CardType {
    private int cardTypeID;
    private String cardTypeName;

    public CardType(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public CardType(int cardTypeID, String cardTypeName) {
        this(cardTypeName);
        this.cardTypeID = cardTypeID;
    }

    public int getCardTypeID() {
        return cardTypeID;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }
}
