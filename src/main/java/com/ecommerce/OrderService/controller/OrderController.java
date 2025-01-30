package com.ecommerce.OrderService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/orderStatus")
    public String getOrderStatus(){
        String response=restTemplate.getForObject("http://ProductService/products",String.class);
        return "Order Placed"+response;
            }
}
