package uz.pdp.model;

public class Transaction {
    public static void pay(User user, double amount) {
        double balance = user.getBalance() - amount;
        user.setBalance(balance);
    }

    public static void receive(Account account, double amount) {
        double balance = account.getBalance() + amount;
        account.setBalance(balance);
    }
}
