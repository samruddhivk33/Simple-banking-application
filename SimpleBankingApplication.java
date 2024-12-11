import java.util.ArrayList;
import java.util.Scanner;

// Define the Account class to represent a bank account.
class Account {
    private String accountHolderName; // Store the account holder's name.
    private double balance;           // Store the account balance.

    // Constructor to initialize a new account.
    public Account(String accountHolderName, double initialDeposit) {
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    // Get account holder's name.
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Get current balance.
    public double getBalance() {
        return balance;
    }

    // Deposit money.
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money.
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
}

// Main class for the banking application.
public class SimpleBankingApplication {
    private static ArrayList<Account> accounts = new ArrayList<>(); // List to store all accounts.
    private static Scanner scanner = new Scanner(System.in);        // Scanner for user input.

    public static void main(String[] args) {
        while (true) {
            // Display menu options.
            System.out.println("\nSimple Banking Application");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) { // Handle invalid input for menu choice.
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.next(); // Clear invalid input.
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline.

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositMoney();
                case 3 -> withdrawMoney();
                case 4 -> checkBalance();
                case 5 -> {
                    System.out.println("Thank you for using the banking application. Goodbye!");
                    scanner.close();
                    return; // Exit the program.
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ");
        if (!scanner.hasNextDouble()) { // Validate numeric input.
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Clear invalid input.
            return;
        }
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline.

        accounts.add(new Account(name, initialDeposit));
        System.out.println("Account created successfully!");
    }

    private static void depositMoney() {
        Account account = findAccount();
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            if (!scanner.hasNextDouble()) { // Validate numeric input.
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input.
                return;
            }
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful! New balance: " + account.getBalance());
        }
    }

    private static void withdrawMoney() {
        Account account = findAccount();
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            if (!scanner.hasNextDouble()) { // Validate numeric input.
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input.
                return;
            }
            double amount = scanner.nextDouble();
            account.withdraw(amount);
            System.out.println("Withdrawal successful! New balance: " + account.getBalance());
        }
    }

    private static void checkBalance() {
        Account account = findAccount();
        if (account != null) {
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Current Balance: " + account.getBalance());
        }
    }

    private static Account findAccount() {
        System.out.print("Enter account holder's name: ");
        String name = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getAccountHolderName().equalsIgnoreCase(name)) {
                return account;
            }
        }
        System.out.println("Account not found.");
        return null;
    }
}
