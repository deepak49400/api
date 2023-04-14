package com.spring.service;

import com.spring.dto.Customer;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private List<Customer> customerList=new ArrayList<>();
    @Autowired
    private ObservationRegistry registory;

    public Customer addCustomer(Customer customer){
        customerList.add(customer);
        return Observation.createNotStarted("addCustomer",registory).observe(()->customer);
    }

    public List<Customer> getCustomers(){
        return Observation.createNotStarted("addCustomer",registory).observe(()->customerList);
    }
    public Customer getCustomerById(int id){
        return
                Observation.createNotStarted("addCustomer",registory)
                        .observe(()->customerList.stream().filter(customer -> customer.id()==id).findAny().orElseThrow(()->new RuntimeException("Customer not found with id "+id)));
    }
}
