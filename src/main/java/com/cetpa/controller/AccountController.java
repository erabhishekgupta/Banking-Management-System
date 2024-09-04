// AccountController.java

package com.cetpa.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cetpa.entity.Account;
import com.cetpa.entity.TransactionSummary;
import com.cetpa.services.AccountService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("central-bank/user/transaction")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/show-balance")
    public String getBalanceView(HttpSession ses, Model model) {
        if (ses.getAttribute("name") == null) {
            return "redirect:/central-bank/login";
        }

        int an = (Integer) ses.getAttribute("account");
        Account account = accountService.getAccount(an);
        model.addAttribute("amount", account.getAmount());

        return "Transaction/ShowBalance";
    }

    @RequestMapping("/deposit")
    public String getDepositMoney(HttpSession ses, Model model) {
        if (ses.getAttribute("name") == null) {
            return "redirect:/central-bank/login";
        }

        return "Transaction/deposite-money";
    }

    @RequestMapping("/update-deposit")
    public String getDepositMoneyUpdated(HttpSession ses, Model model, int amount) {
        accountService.depositMoney(amount, (Integer) ses.getAttribute("account"));
        model.addAttribute("amount", amount);
        return "Transaction/deposite-success";
    }

    @RequestMapping("/withdraw")
    public String getWithdrawMoney(HttpSession ses, Model model) {
        if (ses.getAttribute("name") == null) {
            return "redirect:/central-bank/login";
        }

        return "Transaction/withdraw-money";
    }

    @RequestMapping("/withdraw-money")
    public String updateWithdrawAmount(int amount, Model model, HttpSession ses) {
        int an = (Integer) ses.getAttribute("account");
        Account account = accountService.getAccount(an);
        if (amount > account.getAmount()) {
            model.addAttribute("msg", "Sorry, insufficient balance");
            model.addAttribute("amount", amount);
            return "Transaction/withdraw-money";
        }
        accountService.withdraw(an, amount);
        model.addAttribute("amount", amount);
        return "Transaction/withdraw-success";
    }

    @RequestMapping("/transaction-summary")
    public String getTransactionSummary(HttpSession ses, Model model) {
        List<TransactionSummary> list = accountService.getList((Integer) ses.getAttribute("account"));
        model.addAttribute("list", list);

        return "Transaction/summary";
    }
}
