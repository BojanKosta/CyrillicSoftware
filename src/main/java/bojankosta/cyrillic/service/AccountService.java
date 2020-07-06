package bojankosta.cyrillic.service;


import bojankosta.cyrillic.entity.Account;
import bojankosta.cyrillic.entity.User;
import bojankosta.cyrillic.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;


    public Account saveAccount (Account account) {
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        return accountRepository.getById(id);
    }

    public List<Account> getAllAccounts () {
        return accountRepository.findAll();
    }

    public List<User> getAllUsers( Account account) {
        return account.getUsers();
    }
}
