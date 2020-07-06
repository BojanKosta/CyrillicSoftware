package bojankosta.cyrillic.controller;


import bojankosta.cyrillic.entity.Account;
import bojankosta.cyrillic.entity.User;
import bojankosta.cyrillic.service.AccountService;
import bojankosta.cyrillic.service.CustomerService;
import bojankosta.cyrillic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @PostMapping("/api/account")
    public ResponseEntity <?> saveAccount (@RequestBody Account account, Principal userLogged) {

        User user = userService.getUser(userLogged.getName());

        Account newAccount = account;
        newAccount.setUser(user);
        user.setAccount(newAccount);

        accountService.saveAccount(newAccount);

        return new ResponseEntity<Account>(newAccount, HttpStatus.CREATED);
    }

    @GetMapping("/api/accounts")
    public ResponseEntity <?> getAllUsers (@RequestBody Account account) {
        List<User> users = accountService.getAllUsers(account);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}
