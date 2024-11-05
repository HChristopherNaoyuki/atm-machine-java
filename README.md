# ATM Application

This is a simple ATM application built in Java using Swing for the graphical user interface (GUI). It allows users to perform three main operations: **Balance Inquiry**, **Deposit**, and **Withdrawal**. The application maintains an account with an initial balance of R1000 and provides feedback on transactions through the GUI.

## Features

- **Balance Inquiry**: Displays the current balance of the account.
- **Deposit**: Allows users to deposit money into the account, with a maximum deposit limit of R50,000.
- **Withdrawal**: Enables users to withdraw money from the account. It checks for sufficient funds before allowing a withdrawal.

## GUI Overview

- **Transaction Panel**: Users can select between balance inquiry, deposit, or withdrawal.
- **Input Panel**: 
  - For deposits: Users can enter a deposit amount.
  - For withdrawals: Users can select predefined withdrawal amounts (e.g., R10, R100, R1000).
- **Messages Panel**: Displays messages confirming successful transactions or errors (e.g., insufficient funds).
- **Balance Panel**: Displays the current balance after each transaction.

## Core Components

- **MainATM**: The main class that initializes the ATM application, sets up the GUI, and handles user interactions.
- **Account**: Manages the account balance and provides methods for deposit and withdrawal.
- **Transaction**: An abstract base class for different types of transactions (Balance Inquiry, Deposit, Withdrawal).
- **BalanceInquiry**: Handles balance inquiries and updates the display.
- **Deposit**: Manages deposit transactions and updates the account balance.
- **Withdrawal**: Handles withdrawal transactions and checks for sufficient funds before proceeding.

## Example Usage

1. **Balance Inquiry**: 
   - Click the "Balance Inquiry" checkbox and press "Enter" to view the current account balance.
   
2. **Deposit**: 
   - Enter the deposit amount and click "Enter" to add the amount to the account balance (up to a maximum of R50,000).
   
3. **Withdrawal**:
   - Select a predefined withdrawal amount from the dropdown menu and click "Enter" to withdraw funds (ensuring sufficient balance).

## Project Structure

```
ATM-Application/
│
├── Solution/
│   ├── MainATM.java        # Main application logic and GUI setup
│   ├── Account.java        # Manages account balance and transactions
│   ├── Transaction.java    # Abstract class for transaction types
│   ├── BalanceInquiry.java # Handles balance inquiry transactions
│   ├── Deposit.java        # Handles deposit transactions
│   └── Withdrawal.java     # Handles withdrawal transactions
│
└── README.md               # Project documentation
```

---
