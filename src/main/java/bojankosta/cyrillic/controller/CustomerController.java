package bojankosta.cyrillic.controller;

import bojankosta.cyrillic.entity.Account;
import bojankosta.cyrillic.entity.Customer;
import bojankosta.cyrillic.service.AccountService;
import bojankosta.cyrillic.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    //Id from account that will belongs to customer
    @PostMapping("/api/customer/{id}")
    public ResponseEntity<?> saveCustomer (@PathVariable Long id,  @RequestBody Customer customer) {
        Account account = accountService.getAccount(id);
        Customer newCustomer = customer;
        newCustomer.setAccount(account);
        account.setCustomer(customer);
        customerService.saveCustomer(newCustomer);
        return new ResponseEntity<Customer>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/api/customers")
    public ResponseEntity<?> getAllCustomer() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.CREATED);
    }
}
