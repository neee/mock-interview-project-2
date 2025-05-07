package com.example.badbank.service;

import com.example.badbank.model.Account;
import com.example.badbank.util.ConsoleLogger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountService {

    private Map<String, Account> accounts = new HashMap<>();
    private final ConsoleLogger logger = ConsoleLogger.getInstance();

    public Account createAccount(String owner) {
        Account acc = new Account(UUID.randomUUID().toString(), owner, 0.0);
        accounts.put(acc.getId(), acc);
        logger.log("Created account: " + acc.getId());
        saveAccountToDb(acc);
        return acc;
    }

    public boolean transfer(String fromId, String toId, double amount) {
        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);
        if (from == null || to == null || from.getBalance() < amount) {
            logger.log("Transfer failed from " + fromId + " to " + toId);
            return false;
        }
        from.withdraw(amount);
        to.deposit(amount);
        logger.log("Transferred " + amount + " from " + fromId + " to " + toId);
        return true;
    }

    private void saveAccountToDb(Account account) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO accounts (id, owner, balance) VALUES (?, ?, ?)"
            );
            stmt.setString(1, account.getId());
            stmt.setString(2, account.getOwner());
            stmt.setDouble(3, account.getBalance());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}