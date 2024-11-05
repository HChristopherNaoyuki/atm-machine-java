package Solution;

public abstract class Transaction
{
    protected Account account;

    // Constructor to associate transaction with an account
    public Transaction(Account account)
    {
        this.account = account;
    }

    // Abstract method to be implemented by subclasses
    public abstract void execute();
}
