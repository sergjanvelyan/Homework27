package com.example.homework27.converter;

import com.example.homework27.entity.Orders;
import com.example.homework27.model.OrderJson;
import com.example.homework27.service.CustomerService;
import com.example.homework27.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements Converter<Orders, OrderJson>{
    private final CustomerService customerService;
    private final ProductService productService;
    @Autowired
    public OrderConverter(CustomerService customerService, ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }
    @Override
    public Orders convertToEntity(OrderJson model, Orders entity) {
        entity.setCustomer(customerService.getCustomerReference(model.getCustomerId()));
        entity.setProduct(productService.getProductReference(model.getProductId()));
        entity.setAmount(model.getAmount());
        entity.setTotalPrice(model.getTotalPrice());
        return entity;
    }

    @Override
    public OrderJson convertToModel(Orders entity, OrderJson model) {
        model.setCustomerId(entity.getCustomer().getId());
        model.setProductId(entity.getProduct().getId());
        model.setAmount(entity.getAmount());
        model.setTotalPrice(entity.getTotalPrice());
        return model;
    }
}
