package no12dataabstraction;

import java.util.Date;

@SuppressWarnings("FieldMayBeFinal")
public class Withdraw {
    private double amount;
    private Date date;
    private String account;

    Withdraw(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    public String toString(){
        //your code here
        return String.format("Withdraw of: $%.1f Date: %s into account: %s", this.amount, this.date, this.account);
    }
}
