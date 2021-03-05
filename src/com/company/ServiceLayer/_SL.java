package com.company.ServiceLayer;

import com.company.DataAccessLayer._DAL;
import com.company.Models.User;

import java.time.LocalDateTime;

public class _SL {
    /**
     * this class provides helper methods for work with users
     */
    public static class Users {

        public static User authenticateUser(String cardNumber, String PIN) {
            var cardPIN = _DAL.Cards.getPINByCardNumber(cardNumber);

            if(cardPIN != null && PIN.equals(cardPIN)) {
                return  _DAL.Users.byCardNumber(cardNumber);
            }
            return null;
        }
    }

    /**
     * this class provides helper methods for work with cards
     */
    public static class Cards {
        public static String generateCardNumber() {
            String newCardNumber = generateNumericString(16);

            while (_DAL.Users.byCardNumber(newCardNumber) != null) {
                newCardNumber = generateNumericString(16);
            }

            return newCardNumber;
        }

        public static String generatePIN() {
            return generateNumericString(6);
        }

        private static String generateNumericString(int length) {
            String numericString = new String();
            for (int i = 0; i < length; i++) {
                numericString += ((int) (Math.random() * 10));
            }
            return numericString;
        }
    }
}
