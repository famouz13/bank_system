package com.company.Models;

import java.util.Date;

public class Credit {
    private int creditID;
    private int userID;
    private Date startDate;
    private Date endDate;
    private float percent;
    private double fullAmount;

    public Credit(int userID, Date startDate, Date endDate, float percent, double fullAmount) {
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percent = percent;
        this.fullAmount = fullAmount;
    }

    public Credit(int creditID, int userID, Date startDate, Date endDate, float percent, double fullAmount) {
        this(userID, startDate, endDate, percent, fullAmount);
        this.creditID = creditID;
    }

    public int getCreditID() {
        return creditID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public double getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(double fullAmount) {
        this.fullAmount = fullAmount;
    }
}
