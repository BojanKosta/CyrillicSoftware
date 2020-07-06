package bojankosta.cyrillic.service;


import bojankosta.cyrillic.entity.Account;
import bojankosta.cyrillic.entity.Farm;
import bojankosta.cyrillic.entity.User;
import bojankosta.cyrillic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    public User updatePass (User user, String password) {
        user.setPassword(cryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public User saveUser (User user) {

        User exist = userRepository.findByEmail(user.getEmail());

        if(exist != null) {
            return exist;
        }

        exist = userRepository.findByUsername(user.getUsername());

        if(exist != null) {
            return exist;
        }

        user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }


    public User getUser ( String userName) {
        return userRepository.findByUsername(userName);
    }

    public User getUserByEmail (String email) {
        return userRepository.findByEmail(email);
    }

    public List <User> getAllUsers () {
        return userRepository.findAll();
    }

    public List <Account> getAllAccounts (User user) {
        return user.getAccounts();
    }

    public List<Farm> getAllFarmsByUser(User user) {
        List<Account> accounts = user.getAccounts();
        List<Farm> farmsOfUser = new ArrayList<>();

        for(int i = 0; i<accounts.size(); i++){
            List<Farm> farmsOfAccount = accounts.get(i).getFarm();
            for(int j = 0; j<farmsOfAccount.size(); j++){
                farmsOfUser.add(farmsOfAccount.get(j));
            }
        }
        return farmsOfUser;
    }

}
