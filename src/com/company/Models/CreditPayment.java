package com.company.Models;

import java.util.Date;

public class CreditPayment {
    private int creditPaymentID;
    private int creditID;
    private double paymentAmount;
    private Date paymentDate;

    public CreditPayment(int creditID, double paymentAmount) {
        this.paymentAmount = paymentAmount;
        this.creditID = creditID;
    }

    public CreditPayment(int creditID, double paymentAmount, Date paymentDate) {
        this.creditID = creditID;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    public CreditPayment(int creditPaymentID, int creditID, double paymentAmount, Date paymentDate) {
        this(creditID, paymentAmount, paymentDate);
        this.creditPaymentID = creditPaymentID;
    }

    public int getCreditPaymentID() {
        return creditPaymentID;
    }

    public int getCreditID() {
        return creditID;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return String.format("Amount: %f | Date: %s", getPaymentAmount(), getPaymentDate());
    }
}
