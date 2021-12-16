package no12dataabstraction;

import java.util.ArrayList;
import java.util.Date;


@SuppressWarnings({"FieldMayBeFinal", "unused"})
public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits;
    private ArrayList<Withdraw> withdraws;
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    @SuppressWarnings("FieldCanBeLocal")
    private final int OVERDRAFT = -100;

    /**
     * Create a default {@code Customer} class that sets everything to 0.
     * The default name for an account is <i>'Customer'</i>, and the account number is 0.
     */
    Customer() {
        //create default constructor
        this.accountNumber = 0;
        this.checkBalance = 0;
        this.savingBalance = 0;
        this.savingRate = 0;
        this.name = "Customer";

        deposits = new ArrayList<>();
        withdraws = new ArrayList<>();
    }

    /**
     * A custom constructor that creates a {@code Customer} class with name, account number, and initial deposits.
     *
     * @param name The name of the customer.
     * @param accountNumber The account number.
     * @param checkDeposit Initial deposit to the checking account. Set to 0 if deposit is negative.
     * @param savingDeposit Initial deposit to the saving account. Set to 0 if deposit is negative.
     */
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit) {
        //constructor code here
        this.name = name;
        this.accountNumber = accountNumber;
        this.checkBalance = (checkDeposit >= 0 ? checkDeposit : 0);
        this.savingBalance = (savingDeposit >= 0 ? savingDeposit : 0);
        this.savingRate = 0;

        deposits = new ArrayList<>();
        withdraws = new ArrayList<>();
    }

    /**
     * Deposit to the selected account.
     *
     * @param amt The amount of money that is greater than 0.
     * @param date Date of transaction initiated.
     * @param account The account type:
     *                {@code Customer.CHECKING} for checking account;
     *                {@code Customer.SAVING} for saving account.
     * @return The balance of the selected account, but {@code -1} if any of {@code amt} or {@code account} is invalid.
     */
    public double deposit(double amt, Date date, String account) {
        //your code here
        if (amt <= 0) return -1;

        if (account.equals(Customer.CHECKING))
            checkBalance += amt;
        else if (account.equals(Customer.SAVING))
            savingBalance += amt;
        else return -1;

        deposits.add(new Deposit(amt, date, account));

        return (account.equals(Customer.CHECKING) ? checkBalance : savingBalance);
    }

    /**
     * Withdraw from the selected account.
     *
     * @param amt The amount of money that is greater than 0.
     * @param date Date of transaction initiated.
     * @param account The account type:
     *                {@code Customer.CHECKING} for checking account;
     *                {@code Customer.SAVING} for saving account.
     * @return The balance of the selected account, but {@code -101} if any of {@code amt} or {@code account} is invalid,
     * or the amount is above the limit of overdraft.
     */
    public double withdraw(double amt, Date date, String account) {
        //your code here
        if (amt <= 0) return -101;

        if (account.equals(Customer.CHECKING) && checkOverdraft(amt, account))
            checkBalance -= amt;
        else if (account.equals(Customer.SAVING) && checkOverdraft(amt, account))
            savingBalance -= amt;
        else return -101;

        withdraws.add(new Withdraw(amt, date, account));

        return (account.equals(Customer.CHECKING) ? checkBalance : savingBalance);
    }

    /**
     * Check the overdraft of the current account to make sure the customer won't get extra money.
     *
     * @param amt The amount of money.
     * @param account The account type:
     *                {@code Customer.CHECKING} for checking account;
     *                {@code Customer.SAVING} for saving account.
     * @return {@code true} if the amount is lower than the limit, {@code false} otherwise.
     */
    private boolean checkOverdraft(double amt, String account) {
        //your code here
        return amt <= (account.equals(Customer.CHECKING) ? checkBalance : savingBalance) - OVERDRAFT;
    }

    //do not modify
    public void displayDeposits() {
        for (Deposit d : deposits) {
            System.out.println(d);
        }
    }

    //do not modify
    public void displayWithdraws() {
        for (Withdraw w : withdraws) {
            System.out.println(w);
        }
    }

}
