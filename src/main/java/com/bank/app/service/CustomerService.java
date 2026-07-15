package com.bank.app.service;

import java.util.*;
import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getALlCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id){
        return customerRepository.findById(id);
    }

    public Customer updateCustomer(Long id, Customer updateCustomer){
        return customerRepository.findById(id)
                .map(existing ->{
                    existing.setFirstName(updateCustomer.getFirstName());
                    existing.setLastName(updateCustomer.getLastName());
                    existing.setEmail(updateCustomer.getEmail());
                    existing.setPhoneNumber(updateCustomer.getPhoneNumber());
                    return customerRepository.save(existing);
                })
                .orElseThrow(()-> new RuntimeException("Customer not found with id: " + id));
    }

    public void deleteCustomer(Long id){
        if(!customerRepository.existsById(id)){
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}
