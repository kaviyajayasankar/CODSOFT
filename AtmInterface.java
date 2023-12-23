import java.util.Scanner;

class Bank {
    private double balance;

    public Bank(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: Rs." + balance);
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds. Withdrawal failed.");
            return false;
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: Rs." + balance);
            return true;
        }
    }
}

class AtmActivity {
    private Bank bankAccount;

    public AtmActivity(Bank bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void display() {
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Check Balance");
        System.out.println("4. Exit");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            display();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter withdrawal amount: Rs.");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount > 0) {
                        bankAccount.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid amount. Please enter a positive value.");
                    }
                    break;

                case 2:
                    System.out.print("Enter deposit amount: Rs.");
                    double depositAmount = scanner.nextDouble();
                    if (depositAmount > 0) {
                        bankAccount.deposit(depositAmount);
                    } else {
                        System.out.println("Invalid amount. Please enter a positive value.");
                    }
                    break;

                case 3:
                    System.out.println("Your current balance: Rs." + bankAccount.getBalance());
                    break;

                case 4:
                    System.out.println("Exiting ATM. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }

        } while (choice != 4);

        scanner.close();
    }
}

public class AtmInterface {
    public static void main(String[] args) {
        
        Bank userAccount = new Bank(1000);

        
        AtmActivity atm = new AtmActivity(userAccount);

      
        atm.run();
    }
}
