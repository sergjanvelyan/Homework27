package com.example.homework27.controller;

import com.example.homework27.converter.CustomerConverter;
import com.example.homework27.entity.Customer;
import com.example.homework27.model.CustomerJson;
import com.example.homework27.model.Status;
import com.example.homework27.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {
    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerRestController(CustomerService customerService, CustomerConverter customerConverter) {
        this.customerService = customerService;
        this.customerConverter = customerConverter;
    }

    @PostMapping(value = "/add",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Status addCustomer(@RequestBody CustomerJson customerJson){
        Customer customer = customerService.addCustomer(customerJson);
        return new Status("Customer(id="+customer.getId()+") created");
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerJson getCustomer(@PathVariable long id){
        Customer customer = customerService.getCustomer(id);
        return customerConverter.convertToModel(customer,new CustomerJson());
    }

    @PutMapping (value = "/{id}/update/add/money/{amount}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Status addMoneyForCustomer(@PathVariable long id,@PathVariable double amount){
        customerService.addMoneyForCustomer(id,amount);
        return new Status("Added money for Customer(id="+id+")");
    }

    @PutMapping (value = "/{id}/update/remove/money/{amount}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Status removeMoneyForCustomer(@PathVariable long id,@PathVariable double amount){
        customerService.removeMoneyForCustomer(id,amount);
        return new Status("Removed money for Customer(id="+id+")");
    }
}
