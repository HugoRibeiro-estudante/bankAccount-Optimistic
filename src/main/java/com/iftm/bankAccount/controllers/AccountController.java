package com.iftm.bankAccount.controllers;

import com.iftm.bankAccount.models.Account;
import com.iftm.bankAccount.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable Long id){
        return accountService.findById(id);
    }

    @PostMapping
    public void criar(@RequestBody Account account){
        accountService.save(account);
    }

    @PostMapping("/saque/{id}")
    public Account saque(@PathVariable Long id, @RequestBody Double valor){
        return accountService.saque(id, valor);
    }

    @PostMapping("/deposito/{id}")
    public Account deposito(@PathVariable Long id, @RequestBody Double valor){
        return accountService.deposito(id, valor);
    }

}
