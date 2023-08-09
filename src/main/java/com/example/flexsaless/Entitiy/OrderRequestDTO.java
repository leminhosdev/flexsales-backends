package com.example.flexsaless.Entitiy;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private OrderEntity orderEntity;
    private List<Product> productList;
}
