package com.cetpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cetpa.entity.Account;
import com.cetpa.entity.User;
import com.cetpa.services.AccountService;
import com.cetpa.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("central-bank/login")
public class userController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    @RequestMapping("")
    public String grtLoginView() {
        return "User/login";
    }

    @RequestMapping("/register-user")
    public String getRegistrationView() {
        return "User/registration";
    }

    @RequestMapping("/save-user")
    public String saveUserDetails(User user, Model model) {
        int ac = userService.RegisterUser(user);
        model.addAttribute("accountNo", ac);
        model.addAttribute("name", user.getName());
        return "User/registration-success";
    }

    @RequestMapping("/authenticate-user")
    public String authenticateUser(String userid, String password, Model model, HttpSession ses) {
        User user = userService.getUser(userid);
        if (user == null) {
            model.addAttribute("msg", "User ID doesn't exist");
            model.addAttribute("uid", userid);
            return "User/login";
        }

        String dpassword = user.getPassword();
        if (!password.equals(dpassword)) {
            model.addAttribute("msg", "Entered password is wrong!");
            model.addAttribute("uid", userid);
            return "User/login";
        }
        String username =  user.getName();
        Account account = accountService.getAccountByUserid(userid);
        if (account == null) {
            model.addAttribute("msg", "No account associated with this User ID.");
            return "User/login";
        }
        
        int accountNo = account.getAccountNo();
        ses.setAttribute("name", username);
        ses.setAttribute("account", accountNo);
        return "redirect:/central-bank/home";
    }
    
    
       
        @RequestMapping("/logout")
        public String someMethod(HttpServletRequest request, Model model) {
        HttpSession ses = request.getSession();
        model.addAttribute("name", ses.getAttribute("name"));
        ses.invalidate();
        return "User/logout";
    }

    

}
