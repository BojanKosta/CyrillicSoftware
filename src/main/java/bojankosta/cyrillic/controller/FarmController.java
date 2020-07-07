package bojankosta.cyrillic.controller;


import bojankosta.cyrillic.entity.Account;
import bojankosta.cyrillic.entity.Farm;
import bojankosta.cyrillic.entity.User;
import bojankosta.cyrillic.service.AccountService;
import bojankosta.cyrillic.service.FarmService;
import bojankosta.cyrillic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class FarmController {

    @Autowired
    FarmService farmService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    // {id} is account id that farm is going to be tied for
    @PostMapping("/api/farm/{id}")
    public ResponseEntity<?> saveFarm(@PathVariable Long id, @RequestBody Farm farm) {
        Account account = accountService.getAccount(id);
        Farm newFarm = farm;
        newFarm.setAccount(account);
        farmService.saveFarm(newFarm);
        return new ResponseEntity<Farm>(newFarm, HttpStatus.CREATED);
    }

    @GetMapping("/api/farms")
    public ResponseEntity<?> getAllFarms(){
        List<Farm> farms =  farmService.getAllFarms();
        if(farms == null ) {
            return new ResponseEntity<String>("There is no farms yet", HttpStatus.OK);
        }
        return new ResponseEntity<List<Farm>>(farms, HttpStatus.OK);
    }

    // get all farms that user have access to
    @GetMapping("/api/accounts/farms")
    public ResponseEntity<?> getAllFarmsByUser(Principal userLogged){
        User user = userService.getUser(userLogged.getName());
        List<Farm> farms =  userService.getAllFarmsByUser(user);
        if(farms == null ) {
            return new ResponseEntity<String>("There is no farms yet", HttpStatus.OK);
        }
        return new ResponseEntity<List<Farm>>(farms, HttpStatus.OK);
    }

}
