package com.company;

import com.company.DataAccessLayer._DAL;
import com.company.ServiceLayer._SL;

public class Main {

    public static void main(String[] args) {
        // write your code here

       var res =_SL.Users.authenticateUser("1234507890123456", "123556");
       System.out.println(res);

    }
}
