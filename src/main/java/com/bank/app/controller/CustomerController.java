package com.bank.app.controller;

import com.bank.app.model.Customer;
import com.bank.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomer(){
        return ResponseEntity.ok(customerService.getALlCustomers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,@RequestBody Customer customer){
        try{
            return ResponseEntity.ok(customerService.updateCustomer(id,customer));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        try{
            customerService.deleteCustomer(id);
            return ResponseEntity.ok("Customer deleted Successfully");
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



}
