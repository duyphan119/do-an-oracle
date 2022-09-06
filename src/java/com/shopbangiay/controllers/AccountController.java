package com.shopbangiay.controllers;

import com.google.gson.Gson;
import com.shopbangiay.models.Account;
import com.shopbangiay.services.AccountService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
    Gson gson = new Gson();
    
    AccountService account_service = new AccountService();
    
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public ResponseEntity<String> listAllAccounts() {
        List<Account> accounts = account_service.findAllAccounts();
        return new ResponseEntity<>(gson.toJson(accounts), HttpStatus.OK);
    }
}
