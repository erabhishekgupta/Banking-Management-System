// AccountService.java

package com.cetpa.services;

import java.util.List;
import com.cetpa.entity.Account;
import com.cetpa.entity.TransactionSummary;

public interface AccountService {
    Account getAccountByUserid(String userid);
    Account getAccount(int an);
    void depositMoney(int amount, int accountno);
    void withdraw(int an, int amount);
    List<TransactionSummary> getList(int accountno);
}
