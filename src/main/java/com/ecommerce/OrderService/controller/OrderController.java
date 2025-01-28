package com.ecommerce.OrderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/orderStatus")
    public String getOrderStatus(){
        restTemplate.getForObject("http://product-service/products",String.class);
        return "Order Placed";
            }
}
