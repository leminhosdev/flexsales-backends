package com.example.flexsaless.Controller;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.OrderEntity;
import com.example.flexsaless.Entitiy.OrderRequestDTO;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/saveorder")
    public ResponseEntity<OrderEntity> saveOrder(@RequestBody OrderRequestDTO orderRequestDTO){


        this.orderService.save(orderRequestDTO.getOrderEntity(), orderRequestDTO.getProductList());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRequestDTO.getOrderEntity());
    }

}
