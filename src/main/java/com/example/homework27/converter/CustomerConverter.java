package com.example.homework27.converter;

import com.example.homework27.entity.Customer;
import com.example.homework27.model.CustomerJson;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements Converter <Customer, CustomerJson> {

    @Override
    public Customer convertToEntity(CustomerJson model, Customer entity) {
        entity.setEmail(model.getEmail());
        entity.setMoneyBalance(model.getMoneyBalance());
        return entity;
    }

    @Override
    public CustomerJson convertToModel(Customer entity, CustomerJson model) {
        model.setEmail(entity.getEmail());
        model.setMoneyBalance(entity.getMoneyBalance());
        return model;
    }
}
