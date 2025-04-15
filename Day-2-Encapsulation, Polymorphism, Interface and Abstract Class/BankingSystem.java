
// Interface for loanable accounts
interface Loanable {
    void applyForLoan(double amount);
    double calculateLoanEligibility();
}

// Abstract class BankAccount
abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Encapsulation: Getters and setters
    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    public void deposit(double amount) { this.balance += amount; }
    public void withdraw(double amount) {
        if (balance >= amount) {
            this.balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // Abstract method to calculate interest
    public abstract double calculateInterest();

    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + holderName);
        System.out.println("Balance: ₹" + balance);
        System.out.println("Interest: ₹" + calculateInterest());
        System.out.println("-------------------------------");
    }
}

// SavingsAccount class with interest calculation
class SavingsAccount extends BankAccount implements Loanable {
    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * 0.04; // 4% interest
    }

    @Override
    public void applyForLoan(double amount) {
        System.out.println("Loan Application for Savings Account: ₹" + amount + " has been received.");
    }

    @Override
    public double calculateLoanEligibility() {
        return getBalance() * 2; // Loan eligibility is 2x balance for savings account
    }
}

// CurrentAccount class with interest calculation
class CurrentAccount extends BankAccount implements Loanable {
    public CurrentAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * 0.02; // 2% interest
    }

    @Override
    public void applyForLoan(double amount) {
        System.out.println("Loan Application for Current Account: ₹" + amount + " has been received.");
    }

    @Override
    public double calculateLoanEligibility() {
        return getBalance() * 1.5; // Loan eligibility is 1.5x balance for current account
    }
}

// Main class to test Banking System
public class BankingSystem {
    public static void main(String[] args) {
        // Create account instances
        BankAccount savings = new SavingsAccount("SA12345", "John Doe", 100000);
        BankAccount current = new CurrentAccount("CA98765", "Jane Smith", 50000);

        // Demonstrate polymorphism by processing accounts dynamically
        BankAccount[] accounts = {savings, current};

        for (BankAccount account : accounts) {
            account.displayAccountDetails();

            if (account instanceof Loanable) {
                Loanable loanAccount = (Loanable) account;
                loanAccount.applyForLoan(20000);
                System.out.println("Loan Eligibility: ₹" + loanAccount.calculateLoanEligibility());
            }
        }

        // Test deposit and withdraw
        savings.deposit(5000);
        current.withdraw(10000);

        // Show updated account details
        System.out.println("\n--- Updated Account Details ---");
        savings.displayAccountDetails();
        current.displayAccountDetails();
    }
}



