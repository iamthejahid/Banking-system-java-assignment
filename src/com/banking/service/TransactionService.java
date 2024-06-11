package com.banking.service;

import com.banking.util.JDBCUtil;

import java.math.BigDecimal;
import java.sql.*;

public class TransactionService {
    private final Object lock = new Object();

    public void performDeposit(int accountId, BigDecimal amount) {
        synchronized (lock) {
            try (Connection conn = JDBCUtil.getConnection()) {
                conn.setAutoCommit(false);

                String updateBalanceSql = "UPDATE Accounts SET balance = balance + ? WHERE account_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(updateBalanceSql)) {
                    pstmt.setBigDecimal(1, amount);
                    pstmt.setInt(2, accountId);
                    pstmt.executeUpdate();
                }

                String insertTransactionSql = "INSERT INTO Transactions (account_id, transaction_type, amount, timestamp) VALUES (?, 'DEPOSIT', ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertTransactionSql)) {
                    pstmt.setInt(1, accountId);
                    pstmt.setBigDecimal(2, amount);
                    pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                    pstmt.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void performWithdrawal(int accountId, BigDecimal amount) {
        synchronized (lock) {
            try (Connection conn = JDBCUtil.getConnection()) {
                conn.setAutoCommit(false);

                String updateBalanceSql = "UPDATE Accounts SET balance = balance - ? WHERE account_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(updateBalanceSql)) {
                    pstmt.setBigDecimal(1, amount);
                    pstmt.setInt(2, accountId);
                    pstmt.executeUpdate();
                }

                String insertTransactionSql = "INSERT INTO Transactions (account_id, transaction_type, amount, timestamp) VALUES (?, 'WITHDRAWAL', ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertTransactionSql)) {
                    pstmt.setInt(1, accountId);
                    pstmt.setBigDecimal(2, amount);
                    pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                    pstmt.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void performTransfer(int fromAccountId, int toAccountId, BigDecimal amount) {
        synchronized (lock) {
            try (Connection conn = JDBCUtil.getConnection()) {
                conn.setAutoCommit(false);

                String deductBalanceSql = "UPDATE Accounts SET balance = balance - ? WHERE account_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(deductBalanceSql)) {
                    pstmt.setBigDecimal(1, amount);
                    pstmt.setInt(2, fromAccountId);
                    pstmt.executeUpdate();
                }

                String addBalanceSql = "UPDATE Accounts SET balance = balance + ? WHERE account_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(addBalanceSql)) {
                    pstmt.setBigDecimal(1, amount);
                    pstmt.setInt(2, toAccountId);
                    pstmt.executeUpdate();
                }

                String insertTransactionSql = "INSERT INTO Transactions (account_id, transaction_type, amount, timestamp) VALUES (?, 'TRANSFER', ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertTransactionSql)) {
                    pstmt.setInt(1, fromAccountId);
                    pstmt.setBigDecimal(2, amount);
                    pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                    pstmt.executeUpdate();

                    pstmt.setInt(1, toAccountId);
                    pstmt.setBigDecimal(2, amount);
                    pstmt.executeUpdate();
                }

                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
