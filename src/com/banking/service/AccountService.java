package com.banking.service;

import com.banking.model.Account;
import com.banking.util.JDBCUtil;

import java.math.BigDecimal;
import java.sql.*;

public class AccountService {
    public void createAccount(String accountHolderName, BigDecimal initialBalance) {
        String sql = "INSERT INTO Accounts (account_holder_name, balance) VALUES (?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountHolderName);
            pstmt.setBigDecimal(2, initialBalance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountDetails(int accountId) {
        String sql = "SELECT * FROM Accounts WHERE account_id = ?";
        Account account = null;

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setAccountHolderName(rs.getString("account_holder_name"));
                account.setBalance(rs.getBigDecimal("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }
}
