package com.example.flexsaless.Util;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.Product;

import java.math.BigDecimal;

public class ProductCreator {

    public static Product createProductToBeSaved(){
        return Product.builder().taxes(new BigDecimal(200))
                .price(new BigDecimal(200)).code("qAE123")
                .commission(new BigDecimal(10)).taxes(new BigDecimal(10))
                .amount(2).clientOwner(ClientCreator.createValidClient()).build();
    }

    public static Product createValidProduct(){
        return Product.builder().taxes(new BigDecimal(10))
                .price(new BigDecimal(200)).code("qAE123")
                .clientOwner(ClientCreator.createValidClient())
                .commission(new BigDecimal(10))
                .amount(2).id(20L).build();
    }

    public static Product createValidUpdatedProduct(){
        return Product.builder().taxes(new BigDecimal(10))
                .price(new BigDecimal(2000)).code("qAE1993")
                .clientOwner(ClientCreator.createValidClient())
                .commission(new BigDecimal(10))
                .amount(2).id(20L).build();
    }
}
