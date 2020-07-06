package bojankosta.cyrillic.controller;

import bojankosta.cyrillic.entity.Account;
import bojankosta.cyrillic.entity.User;
import bojankosta.cyrillic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

        @Autowired
        UserService userService;

        @GetMapping("/user/logged")
        public ResponseEntity userLogged(Principal loggedUser){
            User user = userService.getUser(loggedUser.getName());

            return new ResponseEntity<User>(user, HttpStatus.CREATED);

        }

        @PostMapping("/api/user")
        public ResponseEntity<?> saveUser(@RequestParam("fullName")String name, @RequestParam("userName")String username, @RequestParam("password")String password, @RequestParam("email")String email){

            User newUser = new User(name, username,password, email);
            userService.saveUser(newUser);

            return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
        }

        @GetMapping ("/api/users")
        public ResponseEntity<?> getUsers(){
            List<User> users= new ArrayList<>();
            users = userService.getAllUsers();
            return new ResponseEntity<List<User>>(users, HttpStatus.OK);
        }

        // get all accounts that user have access to
        @GetMapping ("/api/users/accounts")
        public ResponseEntity<?> getUsersAccounts(Principal loggedUser){
            User user = userService.getUser(loggedUser.getName());
            List<Account> accounts =  userService.getAllAccounts(user);
            if(accounts == null ) {
                return new ResponseEntity<String>("There is no accounts", HttpStatus.OK);
            }
            return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
        }
    }


