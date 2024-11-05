package Solution;

import javax.swing.*;

public class Withdrawal extends Transaction
{
    private final double amount;
    private final JLabel balanceLabel;

    public Withdrawal(Account account, double amount, JLabel balanceLabel)
    {
        super(account);
        this.amount = amount;
        this.balanceLabel = balanceLabel;
    }

    // Attempts to withdraw the amount, updates the balance, and shows success or error message
    @Override
    public void execute()
    {
        if (account.withdraw(amount))
        {
            JOptionPane.showMessageDialog(null, "Withdrew: R" + amount);
            balanceLabel.setText("Balance: R" + account.getBalance());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Insufficient funds. Transaction cancelled.");
        }
    }
}
