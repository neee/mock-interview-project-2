package com.example.badbank.controller;

import com.example.badbank.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountControllerTest {

    @Test
    public void testAccountCreationLogicCopyPaste() {
        Account acc = new Account("123", "Alice", 100.0);
        assertEquals("123", acc.getId());
        assertEquals("Alice", acc.getOwner());
        assertEquals(100.0, acc.getBalance());
    }

    @Test
    public void testTransferSuccessLogging() {
        AccountController controller = new AccountController();
        controller.create("Alice");
        controller.create("Bob");
        controller.transfer("1", "2", 10.0);
    }
}
