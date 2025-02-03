package com.ecommerce.OrderService.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/orderStatus")
    @CircuitBreaker(name="product service",fallbackMethod = "fallbackProductService")
    public ResponseEntity<String> getOrderStatus() {

                    String response = restTemplate.getForObject("http://ProductService/products", String.class);
                    return ResponseEntity.ok(response);

    }

    // Fallback method if ProductService is down
    public ResponseEntity<String> fallbackProductService(Throwable ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Product Service is temporarily down. Please try again later.");
    }
}
