package bojankosta.cyrillic.service;


import bojankosta.cyrillic.entity.Customer;
import bojankosta.cyrillic.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer saveCustomer (Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById (Long id) {
        return customerRepository.getById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
