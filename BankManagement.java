import java.util.Scanner;

class BankAccount {
    private int accountId;
    private String holderName;
    private double balance;

    BankAccount(int accountId, String holderName, double balance) {
        this.accountId = accountId;
        this.holderName = holderName;
        this.balance = balance;
    }

    int getAccountId() { return accountId; }
    String getHolderName() { return holderName; }
    double getBalance() { return balance; }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        }
    }

    void displayAccount() {
        System.out.println("ID: " + accountId + " | Name: " + holderName + " | Balance: " + balance);
    }
}

class BankSystem {
    private BankAccount[] accounts = new BankAccount[10];
    private int count = 0;

    void createAccount(int id, String name, double balance) {
        accounts[count++] = new BankAccount(id, name, balance);
        System.out.println("Account created successfully.");
    }

    BankAccount findAccount(int id) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountId() == id) return accounts[i];
        }
        return null;
    }

    void viewAllAccounts() {
        if (count == 0) {
            System.out.println("No accounts found.");
            return;
        }
        for (int i = 0; i < count; i++) {
            accounts[i].displayAccount();
        }
    }

    void deleteAccount(int id) {
        for (int i = 0; i < count; i++) {
            if (accounts[i].getAccountId() == id) {
                for (int j = i; j < count - 1; j++) {
                    accounts[j] = accounts[j + 1];
                }
                accounts[--count] = null;
                System.out.println("Account deleted.");
                return;
            }
        }
        System.out.println("Account not found.");
    }
}

public class BankManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankSystem bank = new BankSystem();

        while (true) {
            System.out.println("\n--- Bank Account Management ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. View All Accounts");
            System.out.println("6. Delete Account");
            System.out.println("7. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Account ID: "); int id = sc.nextInt();
                System.out.print("Name: "); String name = sc.next();
                System.out.print("Initial Balance: "); double bal = sc.nextDouble();
                bank.createAccount(id, name, bal);

            } else if (choice == 2) {
                System.out.print("Account ID: "); int id = sc.nextInt();
                BankAccount acc = bank.findAccount(id);
                if (acc != null) { System.out.print("Amount: "); acc.deposit(sc.nextDouble()); }
                else System.out.println("Account not found.");

            } else if (choice == 3) {
                System.out.print("Account ID: "); int id = sc.nextInt();
                BankAccount acc = bank.findAccount(id);
                if (acc != null) { System.out.print("Amount: "); acc.withdraw(sc.nextDouble()); }
                else System.out.println("Account not found.");

            } else if (choice == 4) {
                System.out.print("Account ID: "); int id = sc.nextInt();
                BankAccount acc = bank.findAccount(id);
                if (acc != null) acc.displayAccount();
                else System.out.println("Account not found.");

            } else if (choice == 5) {
                bank.viewAllAccounts();

            } else if (choice == 6) {
                System.out.print("Account ID: "); int id = sc.nextInt();
                bank.deleteAccount(id);

            } else if (choice == 7) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}