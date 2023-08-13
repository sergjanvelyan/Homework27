package com.example.homework27.service;

import com.example.homework27.converter.CustomerConverter;
import com.example.homework27.entity.Customer;
import com.example.homework27.exception.ServiceException;
import com.example.homework27.model.CustomerJson;
import com.example.homework27.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }
    @Transactional
    public Customer addCustomer(CustomerJson customerJson){
        Customer customer = customerConverter.convertToEntity(customerJson,new Customer());
        return customerRepository.save(customer);
    }
    @Transactional
    public void addMoneyForCustomer(long id,double amount){
        Customer customer = getCustomer(id);
        customer.setMoneyBalance(customer.getMoneyBalance()+amount);
        customerRepository.save(customer);
    }
    @Transactional
    public void removeMoneyForCustomer(long id,double amount){
        Customer customer = getCustomer(id);
        customer.setMoneyBalance(customer.getMoneyBalance()-amount);
        customerRepository.save(customer);
    }
    public Customer getCustomer(long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new ServiceException("Customer(id="+id+") does not exists");
        }
        return customer.get();
    }
    public Customer getCustomerReference(long id){
        return customerRepository.getReferenceById(id);
    }
}
