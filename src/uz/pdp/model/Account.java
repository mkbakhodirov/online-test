package uz.pdp.model;

import java.util.UUID;

public class Account {
    private static String currency = "US dollar";
    private double balance;
    private UUID payTypeId;

    public Account(UUID payTypeId) {
        this.payTypeId = payTypeId;
    }

    public static String getCurrency() {
        return currency;
    }

    public static void setCurrency(String currency) {
        Account.currency = currency;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UUID getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(UUID payTypeId) {
        this.payTypeId = payTypeId;
    }
}
