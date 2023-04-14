package com.spring.controller;

import com.spring.dto.Customer;
import com.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customersInfo")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){

        return customerService.addCustomer(customer);
    }
    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

}
