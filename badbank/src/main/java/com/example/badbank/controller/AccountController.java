package com.example.badbank.controller;

import com.example.badbank.service.AccountService;
import com.example.badbank.model.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer_money_v1")
public class AccountController {

    private final AccountService service = new AccountService();

    @PostMapping
    public Account create(@RequestParam String owner) {
        return service.createAccount(owner);
    }

    @PostMapping("/transfer")
    public boolean transfer(@RequestParam String from, @RequestParam String to, @RequestParam double amount) {
        return service.transfer(from, to, amount);
    }
}
