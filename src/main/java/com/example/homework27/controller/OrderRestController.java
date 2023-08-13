package com.example.homework27.controller;

import com.example.homework27.converter.OrderConverter;
import com.example.homework27.entity.Orders;
import com.example.homework27.model.OrderJson;
import com.example.homework27.model.Status;
import com.example.homework27.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderRestController {
    private final OrdersService ordersService;
    private final OrderConverter orderConverter;

    @Autowired
    public OrderRestController(OrdersService ordersService, OrderConverter orderConverter) {
        this.ordersService = ordersService;
        this.orderConverter = orderConverter;
    }

    @PostMapping(value = "/new",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Status addOrder(@RequestBody OrderJson orderJson){
        Orders order = ordersService.addOrder(orderJson);
        return new Status("Order(id="+order.getId()+") confirmed");
    }
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderJson getOrder(@PathVariable long id){
        Orders order = ordersService.getOrder(id);
        return orderConverter.convertToModel(order,new OrderJson());
    }
}
