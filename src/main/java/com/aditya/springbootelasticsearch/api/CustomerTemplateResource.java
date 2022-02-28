package com.aditya.springbootelasticsearch.api;

import com.aditya.springbootelasticsearch.model.Customer;
import com.aditya.springbootelasticsearch.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerTemplateResource {

    @Autowired
    CustomerService customerService;

    @PostMapping("/createcustomer")
    public String createCustomer(@RequestBody Customer customer){
        return customerService.createIndexBulk(customer);
    }

    @GetMapping("/find/{firstname}")
    public List<Customer> findByFirstName(@PathVariable String firstname){
        return customerService.findByFirstname(firstname);
    }
}
