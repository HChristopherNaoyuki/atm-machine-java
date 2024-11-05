package Solution;

import javax.swing.*;

public class BalanceInquiry extends Transaction
{
    private final JLabel balanceLabel;

    public BalanceInquiry(Account account, JLabel balanceLabel)
    {
        super(account);
        this.balanceLabel = balanceLabel;
    }

    // Updates the balance label with the current balance
    @Override
    public void execute()
    {
        balanceLabel.setText("Balance: R" + account.getBalance());
    }
}
