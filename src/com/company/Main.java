package com.company;

import com.company.DataAccessLayer._DAL;

public class Main {

    public static void main(String[] args) {
	// write your code here
    var dal = _DAL.Users.byCardNumber("1234567890123456");
    System.out.println(dal.getFirstName());
    }
}
