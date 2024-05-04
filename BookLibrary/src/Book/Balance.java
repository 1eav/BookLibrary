package Book;

public class Balance {
    private double balance = 0;

    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

    public void put(double balanceToPut) { balance = balance + balanceToPut; }

    public void discount(double balanceToDiscount) { balance = balance + balanceToDiscount * .9; }

    @Override
    public String toString() { return "на сумму: " + balance; }
}