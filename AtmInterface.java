import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Bank {
    private double balance;

    public Bank(double initialBal) {
        this.balance = initialBal;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

public class AtmInterface extends JFrame {
    private Bank usersAcc;

    private JTextField amountField;
    private JTextArea result;

    public AtmInterface(Bank account) {
        this.usersAcc = account;

        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        amountField = new JTextField(10);
        add(new JLabel("Enter Amount:"));
        add(amountField);

        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton checkBalanceButton = new JButton("Check Balance");

        result = new JTextArea(5, 20);
        result.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(result);

        add(withdrawButton);
        add(depositButton);
        add(checkBalanceButton);
        add(scrollPane);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBalance();
            }
        });
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (usersAcc.withdraw(amount)) {
                displayMessage("Withdrawal successful. New balance: " + usersAcc.getBalance());
            } else {
                displayMessage("Insufficient funds for withdrawal.");
            }
        } catch (NumberFormatException ex) {
            displayMessage("Invalid amount. Please enter a valid number.");
        }
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            usersAcc.deposit(amount);
            displayMessage("Deposit successful. New balance: " + usersAcc.getBalance());
        } catch (NumberFormatException ex) {
            displayMessage("Invalid amount. Please enter a valid number.");
        }
    }

    private void displayBalance() {
        displayMessage("Current Balance: " + usersAcc.getBalance());
    }

    private void displayMessage(String message) {
        result.setText(message);
    }

    public static void main(String[] args) {
        Bank usersAcc = new Bank(1000.0); 
        SwingUtilities.invokeLater(() -> new AtmInterface(usersAcc).setVisible(true));
    }
}