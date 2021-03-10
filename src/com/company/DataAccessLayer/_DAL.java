package com.company.DataAccessLayer;

import com.company.Models.*;
import com.company.databases.PostgresDB;
import com.company.databases.interfaces.IDB;
import org.postgresql.core.Utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class _DAL {
    /**
     * this class provides methods for working with users table
     */
    public static class Users {

        public static boolean AddNew(User user) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("INSERT INTO Users(IIN,firstName,lastName,Phone,Password)\n" +
                        "VALUES(?,?,?,?,?)");
                prt.setString(1, user.getIIN());
                prt.setString(2, user.getFirstName());
                prt.setString(3, user.getLastName());
                prt.setString(4, user.getPhone());
                prt.setString(5, user.getPassword());

                int rows = prt.executeUpdate();

                return rows == 1;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }

        /**
         * @param phone of user which you want to get
         * @return if successful user instance otherwise null
         */

        public static User byPhone(String phone) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("SELECT*FROM Users WHERE Phone = ?");
                prt.setString(1, phone);

                ResultSet rs = prt.executeQuery();
                if (rs.next())
                    return createUserFromRS(rs);

                return null;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }

        /**
         * @param userID id of user which you want to get
         * @return if successful user instance otherwise null
         */

        public static User byID(int userID) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("SELECT*FROM Users WHERE UserID = ?");
                prt.setInt(1, userID);

                ResultSet rs = prt.executeQuery();
                if (rs.next())
                    return createUserFromRS(rs);

                return null;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }

        /**
         * @param cardNumber card number which entered user
         * @return if user with that card number exists returns user otherwise null
         */
        public static User byCardNumber(String cardNumber) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("SELECT UserID from Cards WHERE CardNumber = ?");
                prt.setString(1, cardNumber);

                ResultSet rs = prt.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    return byID(id);
                }

                return null;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }

        /**
         * helper method to avoid code repeating
         *
         * @param rs
         * @return initialized from result set user instance
         */
        private static User createUserFromRS(ResultSet rs) {
            try {
                int id = rs.getInt(1);
                String IIN = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String phone = rs.getString(5);
                String password = rs.getString(6);
                return new User(id, IIN, firstName, lastName, phone, password);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }


    }

    /**
     * this class provides methods for working with cards table
     */

    public static class Cards {
        /**
         * @param userID - id of user which cards you want to get
         * @return if successful ArrayList of Cards otherwise null
         */
        public static ArrayList<Card> byUserID(int userID) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("SELECT*FROM cardsandtypes WHERE UserID = ?");
                prt.setInt(1, userID);

                var cards = new ArrayList<Card>();
                ResultSet rs = prt.executeQuery();

                while (rs.next()) {
                    var card = new Card(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            rs.getInt(5),
                            rs.getInt(7));

                    card.setCardType(new CardType(rs.getInt(5), rs.getString(6)));
                    cards.add(card);
                }
                return cards;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }


        public static Card byID(int ID) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("SELECT*FROM cardsandtypes WHERE cardid = ?");
                prt.setInt(1, ID);

                ResultSet rs = prt.executeQuery();

                if (rs.next()) {
                    var card = new Card(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getDouble(4),
                            rs.getInt(5),
                            rs.getInt(7));

                    card.setCardType(new CardType(rs.getInt(5), rs.getString(6)));
                    return card;
                }
                return null;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }


        public static String getPINByCardNumber(String cardNumber) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("SELECT PIN from Cards WHERE CardNumber = ?");
                prt.setString(1, cardNumber);

                ResultSet rs = prt.executeQuery();
                if (rs.next()) {
                    return rs.getString(1);
                }

                return null;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }

        /**
         * @param newCard card instance with initialized cardNumber, pin, cardTypeID, userID fields
         * @return if successful true otherwise false
         */
        public static boolean createNewUserCard(Card newCard) {
            try (var conn = PostgresDB.getInstance().getConnection()) {

                PreparedStatement prt = conn.prepareStatement("INSERT INTO Cards(CardNumber,PIN,Balance,CardTypeID,UserID) VALUES(?,?,?,?,?)");

                prt.setString(1, newCard.getCardNumber());
                prt.setString(2, newCard.getPIN());
                prt.setBigDecimal(3, BigDecimal.valueOf(0));
                prt.setInt(4, newCard.getCardTypeID());
                prt.setInt(5, newCard.getUserID());
                prt.executeUpdate();

                return true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }

    }

    /**
     * this class provides methods for working with credits table
     */
    public static class Credits {
        /**
         * @param userID - id of user which credits you want to get
         * @return if successful - ArrayList of Credits, otherwise - null
         */
        public static ArrayList<Credit> byUserID(int userID) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("SELECT*FROM Credits WHERE UserID = ?");
                prt.setInt(1, userID);

                var credits = new ArrayList<Credit>();
                ResultSet rs = prt.executeQuery();

                while (rs.next()) {
                    credits.add(new Credit(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getDate(3),
                            rs.getDate(4),
                            rs.getFloat(5),
                            rs.getDouble(6),
                            rs.getDouble(7)
                    ));
                }
                return credits;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }

        /**
         * @param newCredit credit instance with initialized userid, start date, end date, percent, amount fields
         * @return if successful - true, otherwise - false
         */
        public static boolean addNewCredit(Credit newCredit) {
            try (var conn = PostgresDB.getInstance().getConnection()) {

                var prt = conn.prepareStatement("INSERT INTO Credits(userid,startdate,enddate,creditpercent,fullamount,loanbalance) VALUES(?, ?, ?,?,?,?)");

                prt.setInt(1, newCredit.getUserID());
                prt.setDate(2, Date.valueOf(LocalDate.now()));
                prt.setDate(3, Date.valueOf(LocalDate.now().plusYears(newCredit.getEndDate().getYear())));
                prt.setFloat(4, newCredit.getPercent());
                prt.setDouble(5, newCredit.getFullAmount());
                prt.setDouble(6, newCredit.getLoanBalance());
                prt.executeUpdate();


                var card = _DAL.Cards.byUserID(newCredit.getUserID());
                if (card.stream().findFirst().orElse(null) == null)
                    return false;

                var pst2 = conn.prepareStatement("UPDATE Cards SET Balance = Balance + ? WHERE CardID = ?");
                pst2.setBigDecimal(1, BigDecimal.valueOf(newCredit.getFullAmount()));
                pst2.setInt(2, card.stream().findFirst().orElse(null).getCardID());
                pst2.executeUpdate();

                return true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }

        /**
         * @param creditID      id of user which want to get new card
         * @param creditPayment creditPayment instance with initialized fields
         * @return if successful - true, otherwise - false
         */
        public static boolean payCredit(CreditPayment creditPayment) {
            try (var conn = PostgresDB.getInstance().getConnection()) {

                var cstmt = conn.prepareCall("CALL payForCredit(?,?)");
                cstmt.setInt(1, creditPayment.getCreditID());
                cstmt.setBigDecimal(2, BigDecimal.valueOf(creditPayment.getPaymentAmount()));

                cstmt.executeUpdate();
                cstmt.close();
                return true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }

        /**
         * @param creditID -
         * @return if successful - list of payments related to selected credit, otherwise null
         */
        public static ArrayList<CreditPayment> paymentCreditsByCreditID(int creditID) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("SELECT*FROM CreditPayments WHERE CreditID = ?");
                prt.setInt(1, creditID);

                var payments = new ArrayList<CreditPayment>();
                ResultSet rs = prt.executeQuery();

                while (rs.next()) {
                    payments.add(new CreditPayment(
                            rs.getInt(1),
                            rs.getInt(2),
                            rs.getDouble(3),
                            rs.getDate(4)));
                }
                return payments;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }

    }

    /**
     * this class provides methods for working with transactions table
     */
    public static class Transactions {
        /**
         * @param transaction - transaction with initialized
         *                    fromUserID, toUserID,fromCardNumber,toCardNumber,
         *                    amount fields.
         *                    Comment field is optional
         * @return if successful - ArrayList of Credits, otherwise - null
         */
        public static boolean transfer(Transaction transaction) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                var cstmt = conn.prepareCall("CALL sp_transfer(?,?,?,?,?,?)");
                cstmt.setInt(1, transaction.getFromUserID());
                cstmt.setInt(2, transaction.getToUserID());
                cstmt.setString(3, transaction.getFromCardNumber());
                cstmt.setString(4, transaction.getToCardNumber());
                cstmt.setBigDecimal(5, BigDecimal.valueOf(transaction.getAmount()));
                cstmt.setString(6, transaction.getTransactionComment());

                cstmt.executeUpdate();
                return true;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }


        /**
         * @param userID - id of user which credits you want to get
         * @return if successful - ArrayList of Transactions with users info involved in transactions, otherwise - null
         */
        public static ArrayList<Transaction> byUserID(int userID) {
            try (var conn = PostgresDB.getInstance().getConnection()) {
                PreparedStatement prt = conn.prepareStatement("SELECT*FROM V_UsersTransactions WHERE fromUserID = ? or toUserID = ?");
                prt.setInt(1, userID);
                prt.setInt(2, userID);

                var transactions = new ArrayList<Transaction>();
                ResultSet rs = prt.executeQuery();

                while (rs.next()) {
                    java.util.Date date = (java.util.Date) rs.getDate(4);
                    int fromUserID = rs.getInt(2);
                    int toUserID = rs.getInt(3);
                    var tr = new Transaction(
                            rs.getInt(1),
                            fromUserID,
                            toUserID,
                            date,
                            rs.getDouble(5),
                            rs.getString(6)
                    );

                    tr.setFromUser(new User(fromUserID, rs.getString(9), rs.getString(10)));

                    tr.setToUser(new User(toUserID, rs.getString(11), rs.getString(12)));
                    transactions.add(tr);
                }
                return transactions;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return null;
            }
        }
    }
}
