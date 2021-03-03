package com.company.Models;


//entity of bank database
public class User {
    private int userID;
    private String IIN;
    private String firstName;
    private String lastName;
    private String phone;

    /**
     * this constructor used for creating new users
     *
     * @param iin
     * @param firstName
     * @param lastName
     * @param phone
     */
    public User(String iin, String firstName, String lastName, String phone) {
        this.IIN = iin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    /**
     * this constructor used for retrieving users from database
     * @param id
     * @param iin
     * @param firstName
     * @param lastName
     * @param phone
     */
    public User(int id, String iin, String firstName, String lastName, String phone) {
        this(iin, firstName, lastName, phone);
        this.userID = id;
    }

    public int getUserID() {
        return userID;
    }

    public String getIIN() {
        return IIN;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
