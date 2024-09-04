// AccountServiceImpl.java

package com.cetpa.services_impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cetpa.entity.Account;
import com.cetpa.entity.TransactionSummary;
import com.cetpa.repository.AccountRepository;
import com.cetpa.repository.TransactionRepository;
import com.cetpa.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Account getAccountByUserid(String userid) {
        return accountRepository.findByUserId(userid);
    }

    @Override
    public Account getAccount(int an) {
        return accountRepository.findById(an).orElse(null);
    }

    @Override
    public void depositMoney(int amount, int accountno) {
        accountRepository.updateAmount(amount, accountno);
        TransactionSummary summary = new TransactionSummary();
        summary.setAccountno(accountno);
        summary.setDate(LocalDate.now().toString());
        summary.setTime(LocalTime.now().toString());
        summary.setAmount(amount);
        summary.setTransactionType("credit");
        transactionRepository.save(summary);  // Changed to use TransactionRepository
    }

    @Override
    public void withdraw(int an, int amount) {
        accountRepository.updateAmount(-amount, an);
        TransactionSummary summary = new TransactionSummary();
        summary.setAccountno(an);
        summary.setDate(LocalDate.now().toString());
        summary.setTime(LocalTime.now().toString());
        summary.setAmount(amount);
        summary.setTransactionType("debit");
        transactionRepository.save(summary);  // Changed to use TransactionRepository
    }

    @Override
    public List<TransactionSummary> getList(int accountno) {
        return transactionRepository.findByAccountno(accountno);
    }
    
    public void saveSummary(TransactionSummary transactionSummary) {
        accountRepository.save(transactionSummary);
    }
}
