package Solution;

import javax.swing.*;

public class Deposit extends Transaction
{
    private final double amount;
    private final JLabel balanceLabel;

    public Deposit(Account account, double amount, JLabel balanceLabel)
    {
        super(account);
        this.amount = amount;
        this.balanceLabel = balanceLabel;
    }

    // Adds deposit amount to the balance and updates the balance label
    @Override
    public void execute()
    {
        account.deposit(amount);
        balanceLabel.setText("Balance: R" + account.getBalance());
    }
}
