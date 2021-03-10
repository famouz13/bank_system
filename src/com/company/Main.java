package com.company;

import com.company.Controllers.BankSystemController;
import com.company.DataAccessLayer._DAL;
import com.company.ServiceLayer._SL;
import com.company.Views.BankSystemConsoleView;

public class Main {

    public static void main(String[] args) {
        var view = new BankSystemConsoleView(new BankSystemController());
        view.startApp();
    }
}
