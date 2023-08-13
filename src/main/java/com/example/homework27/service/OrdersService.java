package com.example.homework27.service;

import com.example.homework27.converter.OrderConverter;
import com.example.homework27.entity.Orders;
import com.example.homework27.exception.ServiceException;
import com.example.homework27.model.OrderJson;
import com.example.homework27.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrderConverter orderConverter;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, OrderConverter orderConverter, CustomerService customerService, ProductService productService) {
        this.ordersRepository = ordersRepository;
        this.orderConverter = orderConverter;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Transactional
    public Orders addOrder(OrderJson orderJson){
        orderJson.setTotalPrice(orderJson.getAmount()*productService.getProduct(orderJson.getProductId()).getPrice());
        if(orderJson.getTotalPrice()>customerService.getCustomer(orderJson.getCustomerId()).getMoneyBalance()){
            throw new ServiceException("Customer(id="+orderJson.getCustomerId()+") has not enough money");
        }
        Orders order = orderConverter.convertToEntity(orderJson,new Orders());
        customerService.removeMoneyForCustomer(orderJson.getCustomerId(), orderJson.getAmount());
        return ordersRepository.save(order);
    }
    public Orders getOrder(long id){
        Optional<Orders> order = ordersRepository.findById(id);
        if(order.isEmpty()){
            throw new ServiceException("Order(id="+id+") does not exists");
        }
        return order.get();
    }
}
