package com.aditya.springbootelasticsearch.api;

import com.aditya.springbootelasticsearch.model.Customer;
import com.aditya.springbootelasticsearch.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerResource {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/savecustomer")
    public int saveCustomer(@RequestBody List<Customer> customers){
        customerRepository.saveAll(customers);
        return customers.size();
    }

    @GetMapping("/findAll")
    public Iterable<Customer> findAllCustomer(){
        return customerRepository.findAll();
    }

    @GetMapping("/findByName/{firstname}")
    public List<Customer> findByFirstName(@PathVariable String firstname){
        return customerRepository.findByFirstname(firstname);
    }
}
