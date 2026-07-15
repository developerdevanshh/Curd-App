package com.bank.app.controller;

import com.bank.app.model.Customer;
import com.bank.app.repository.CustomerRepository;
import com.bank.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

}
