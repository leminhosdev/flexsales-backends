package com.example.flexsaless.Controller;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.OrderEntity;
import com.example.flexsaless.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/saveorder")
    public ResponseEntity<OrderEntity> saveOrder(@RequestBody OrderEntity orderEntity){

        this.orderService.save(orderEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderEntity);
    }

}
