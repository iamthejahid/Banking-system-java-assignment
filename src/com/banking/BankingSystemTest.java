package com.banking;

import com.banking.service.AccountService;
import com.banking.service.TransactionService;

import java.math.BigDecimal;

public class BankingSystemTest {
    public static void runTests() {
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();

        // Create accounts
        accountService.createAccount("David", BigDecimal.valueOf(3000));
        accountService.createAccount("Eva", BigDecimal.valueOf(4000));

        // Perform transactions
        transactionService.performDeposit(1, BigDecimal.valueOf(500));
        transactionService.performWithdrawal(2, BigDecimal.valueOf(200));
        transactionService.performTransfer(1, 2, BigDecimal.valueOf(300));

        // Display account details
        System.out.println(accountService.getAccountDetails(1));
        System.out.println(accountService.getAccountDetails(2));
    }
}
