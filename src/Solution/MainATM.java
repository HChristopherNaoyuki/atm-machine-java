// Solution/MainATM.java
package Solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class MainATM
{
    private static final double MAX_DEPOSIT = 50000.00;  // Maximum deposit limit
    private final Account account;
    private final JLabel balanceLabel;
    private JLabel withdrawalMessageLabel, depositMessageLabel;
    private final JPanel balancePanel;

    public MainATM()
    {
        account = new Account();  // Initialize Account with starting balance

        // Main Frame Configuration
        JFrame frame = new JFrame("ATM");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);  // Prevent window resizing
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);

        // Title Panel (Minimalist Gray Theme)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.DARK_GRAY);
        JLabel titleLabel = new JLabel("ATM - Bank Inquiry");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // Balance Display Panel (Initially hidden, shown upon Balance Inquiry selection)
        balancePanel = new JPanel();
        balancePanel.setBackground(Color.LIGHT_GRAY);
        balanceLabel = new JLabel("");
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        balanceLabel.setForeground(Color.BLACK);
        balancePanel.add(balanceLabel);
        frame.add(balancePanel, BorderLayout.CENTER);

        // Transaction Selection Panel with Checkboxes
        JPanel transactionPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        transactionPanel.setBackground(Color.LIGHT_GRAY);
        JCheckBox balanceCheckBox = new JCheckBox("Balance Inquiry");
        JCheckBox depositCheckBox = new JCheckBox("Deposit");
        JCheckBox withdrawalCheckBox = new JCheckBox("Withdrawal");
        setCheckBoxColor(balanceCheckBox);
        setCheckBoxColor(depositCheckBox);
        setCheckBoxColor(withdrawalCheckBox);
        transactionPanel.add(balanceCheckBox);
        transactionPanel.add(depositCheckBox);
        transactionPanel.add(withdrawalCheckBox);

        // Input Panel for Deposit and Withdrawal
        JPanel inputPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        inputPanel.setBackground(Color.LIGHT_GRAY);
        JTextField depositField = new JTextField(10);
        depositField.setBorder(BorderFactory.createTitledBorder("Enter Deposit Amount"));
        inputPanel.add(depositField);

        String[] withdrawalAmounts = {"R10", "R100", "R1000"};
        JComboBox<String> withdrawalComboBox = new JComboBox<>(withdrawalAmounts);
        withdrawalComboBox.setBorder(BorderFactory.createTitledBorder("Select Withdrawal Amount"));
        inputPanel.add(withdrawalComboBox);

        // Messages Panel for Deposit and Withdrawal Status
        JPanel messagesPanel = new JPanel(new GridLayout(2, 1));
        messagesPanel.setBackground(Color.LIGHT_GRAY);
        depositMessageLabel = new JLabel("");
        withdrawalMessageLabel = new JLabel("");
        messagesPanel.add(depositMessageLabel);
        messagesPanel.add(withdrawalMessageLabel);

        // Button Panel with Enter and Cancel Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.LIGHT_GRAY);
        JButton enterButton = createButton("Enter", Color.DARK_GRAY, Color.WHITE);
        JButton cancelButton = createButton("Cancel", Color.GRAY, Color.BLACK);
        buttonPanel.add(enterButton);
        buttonPanel.add(cancelButton);

        // Enter Button Functionality
        enterButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    // Perform Balance Inquiry
                    if (balanceCheckBox.isSelected())
                    {
                        performBalanceInquiry();
                    }

                    // Perform Deposit
                    if (depositCheckBox.isSelected())
                    {
                        double depositAmount = Double.parseDouble(depositField.getText());

                        // Check deposit limit
                        if (depositAmount > MAX_DEPOSIT)
                        {
                            depositMessageLabel.setText("Deposit exceeds limit of R50,000.00");
                        }
                        else
                        {
                            performDeposit(depositAmount);
                        }
                    }

                    // Perform Withdrawal
                    if (withdrawalCheckBox.isSelected())
                    {
                        String selectedAmount = (String) withdrawalComboBox.getSelectedItem();
                        double withdrawalAmount = Double.parseDouble(selectedAmount.substring(1));
                        performWithdrawal(withdrawalAmount);
                    }
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(frame, "Invalid amount entered. Please try again.");
                }
            }
        });

        // Cancel Button Functionality to reset all fields
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                balanceCheckBox.setSelected(false);
                depositCheckBox.setSelected(false);
                withdrawalCheckBox.setSelected(false);
                depositField.setText("");
                depositMessageLabel.setText("");
                withdrawalMessageLabel.setText("");
                withdrawalComboBox.setSelectedIndex(0);
            }
        });

        // Layout Panels
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(transactionPanel, BorderLayout.NORTH);
        leftPanel.add(inputPanel, BorderLayout.CENTER);

        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(messagesPanel, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        // Display Frame
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
    }

    // Helper Method to Create Buttons with Colors
    private JButton createButton(String text, Color bgColor, Color fgColor)
    {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        return button;
    }

    // Helper Method to Set Checkbox Color
    private void setCheckBoxColor(JCheckBox checkBox)
    {
        checkBox.setBackground(Color.LIGHT_GRAY);
        checkBox.setFocusPainted(false);
    }

    // Format balance to #,###.00
    private String formatBalance(double balance)
    {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(balance);
    }

    // Perform Balance Inquiry
    private void performBalanceInquiry()
    {
        balanceLabel.setText("Balance: R" + formatBalance(account.getBalance()));
        balancePanel.setVisible(true); // Show balance panel when Balance Inquiry is selected
    }

    // Perform Deposit
    private void performDeposit(double depositAmount)
    {
        account.deposit(depositAmount);
        depositMessageLabel.setText("Deposit successful. New balance: R" + formatBalance(account.getBalance()));
        balanceLabel.setText("Balance: R" + formatBalance(account.getBalance()));
    }

    // Perform Withdrawal
    private void performWithdrawal(double withdrawalAmount)
    {
        if (account.withdraw(withdrawalAmount))
        {
            withdrawalMessageLabel.setText("Withdrawal successful. New balance: R" + formatBalance(account.getBalance()));
            balanceLabel.setText("Balance: R" + formatBalance(account.getBalance()));
        }
        else
        {
            withdrawalMessageLabel.setText("Insufficient funds for withdrawal.");
        }
    }

    public static void main(String[] args)
    {
        new MainATM();
    }
}
