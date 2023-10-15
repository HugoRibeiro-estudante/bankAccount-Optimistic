package com.iftm.bankAccount.services;

import com.iftm.bankAccount.models.Account;
import com.iftm.bankAccount.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private Lock lock = new ReentrantLock();


    public Account saque(Long id, Double valor) {
        lock.lock();
        try {
            var account = accountRepository.findById(id);
            var saldo = account.get().getSaldo();

            if (valor <= saldo){
                account.get().setSaldo(saldo - valor);
                System.out.println("Saque realizado com sucesso");
                return accountRepository.save(account.get());
            } else {
                System.out.println("Saldo insuficiente");
                return account.get();
            }
        } finally {
            lock.unlock();
        }
    }


    public Account deposito(Long id, Double valor) {
        lock.lock();
        try {
            var account = accountRepository.findById(id).get();
            var saldo = account.getSaldo();
            account.setSaldo(saldo + valor);
            System.out.println("Depósito realizado com sucesso");
            return accountRepository.save(account);
        } finally {
            lock.unlock();
        }
    }


    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }
}
