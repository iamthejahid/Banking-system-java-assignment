import com.banking.BankingSystemTest;
import com.banking.model.Account;
import com.banking.service.AccountService;
import com.banking.service.TransactionService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BankingSystemTest.runTests(); // Initialize with some sample data
        displayWelcomeMessage();

        boolean continueUsage = true;
        while (continueUsage) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    performTransaction();
                    break;
                case 2:
                    displayAllAccounts();
                    break;
                case 3:
                    continueUsage = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }

        System.out.println("Thank you for using the banking system. Goodbye!");
    }

    private static void displayWelcomeMessage() {
        System.out.println("Welcome to the Simple Banking System!");
        System.out.println("=======================================");
    }

    private static void displayMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Perform a Transaction");
        System.out.println("2. View All Accounts");
        System.out.println("3. Exit");
    }

    private static int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static void performTransaction() {
        System.out.println("\nChoose the type of transaction:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        int transactionType = getUserChoice();

        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();

        switch (transactionType) {
            case 1:
                performDeposit(accountService, transactionService);
                break;
            case 2:
                performWithdrawal(accountService, transactionService);
                break;
            case 3:
                performTransfer(accountService, transactionService);
                break;
            default:
                System.out.println("Invalid choice! Please enter a valid transaction type.");
        }
    }

    private static void performDeposit(AccountService accountService, TransactionService transactionService) {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter Amount to Deposit: ");
        BigDecimal amount = scanner.nextBigDecimal();

        transactionService.performDeposit(accountId, amount);
        displayAccountDetails(accountService, accountId);
    }

    private static void performWithdrawal(AccountService accountService, TransactionService transactionService) {
        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter Amount to Withdraw: ");
        BigDecimal amount = scanner.nextBigDecimal();

        transactionService.performWithdrawal(accountId, amount);
        displayAccountDetails(accountService, accountId);
    }

    private static void performTransfer(AccountService accountService, TransactionService transactionService) {
        System.out.print("Enter From Account ID: ");
        int fromAccountId = scanner.nextInt();
        System.out.print("Enter To Account ID: ");
        int toAccountId = scanner.nextInt();
        System.out.print("Enter Amount to Transfer: ");
        BigDecimal amount = scanner.nextBigDecimal();

        transactionService.performTransfer(fromAccountId, toAccountId, amount);
        System.out.println("Transfer successful!");
        displayAccountDetails(accountService, fromAccountId);
        displayAccountDetails(accountService, toAccountId);
    }

    private static void displayAllAccounts() {
        AccountService accountService = new AccountService();
        System.out.println("\nAll Accounts:");
        accountService.getAllAccounts().forEach(System.out::println);
    }

    private static void displayAccountDetails(AccountService accountService, int accountId) {
        Account account = accountService.getAccountDetails(accountId);
        if (account != null) {
            System.out.println("Account Details:");
            System.out.println(account);
        } else {
            System.out.println("Account not found!");
        }
    }
}
