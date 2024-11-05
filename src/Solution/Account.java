package Solution;

public class Account
{
    private double balance;

    // Constructor initializes account with starting balance of R1000
    public Account()
    {
        this.balance = 1000.00;
    }

    // Returns the current balance
    public double getBalance()
    {
        return balance;
    }

    // Adds an amount to the balance (used for deposits)
    public void deposit(double amount)
    {
        balance += amount;
    }

    // Deducts amount if sufficient funds are available
    public boolean withdraw(double amount)
    {
        if (amount <= balance)
        {
            balance -= amount;
            return true;
        }
        else
        {
            return false;
        }
    }
}
