package com.example.flexsaless.Repositoriy;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.OrderEntity;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;


    @Test
    void save_PersistClient_WhenSucessful(){
        OrderEntity orderToBeSaved = this.createOrder();
        OrderEntity orderSaved =  this.orderRepository.save(orderToBeSaved);
        Assertions.assertNotNull(orderSaved);
        Assertions.assertNotNull(orderSaved.getId());
        Assertions.assertNotNull(orderSaved.getClientOrderOwner());
        Assertions.assertFalse(orderSaved.getProductList().isEmpty());
        Assertions.assertEquals(orderSaved, orderToBeSaved);
    }
    @Test
    void update_UpdateClient_WhenSucessful(){
        OrderEntity orderToBeSaved = this.createOrder();
        OrderEntity orderSaved =  this.orderRepository.save(orderToBeSaved);
        orderSaved.setProductsAmount(299);
        OrderEntity orderUpdated =  this.orderRepository.save(orderSaved);

        Assertions.assertNotNull(orderSaved);
        Assertions.assertNotNull(orderSaved.getId());
        Assertions.assertNotNull(orderSaved.getClientOrderOwner());
        Assertions.assertFalse(orderSaved.getProductList().isEmpty());
        Assertions.assertEquals(orderSaved, orderToBeSaved);
        Assertions.assertNotNull(orderUpdated);
        Assertions.assertNotNull(orderUpdated.getId());

    }
    @Test
    void delete_DeleteClient_WhenSucessful(){
        OrderEntity orderToBeSaved = this.createOrder();
        OrderEntity orderSaved =  this.orderRepository.save(orderToBeSaved);
        this.orderRepository.delete(orderSaved);
        Optional<OrderEntity> productOptional = this.orderRepository.findById(orderSaved.getId());
        Assertions.assertTrue(productOptional.isEmpty());

    }


    private OrderEntity createOrder() {
        Product product = createProduct();
        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
        LocalDate date = LocalDate.parse("2023-10-10");
        return OrderEntity.builder().clientOrderOwner(product.getClientOwner())
                .productList(productList).productsAmount(10)
                .salesData(date).totalPrice(new BigDecimal(200))
                .totalTaxesValue(new BigDecimal(200)).totalCommissionValue(new BigDecimal(200)).build();

    }
    public Product createProduct(){

        Client clientOwner = Client.builder().clientName("Peoples").clientArea("Business").email("ada3ff@gmail.com")
                .password("topasdm3das").build();

        return   Product.builder().price(new BigDecimal(200)).code("adasd3d")
                .name("ropeiro dois 2")
                .commission(new BigDecimal(10)).taxes(new BigDecimal(2))
                .clientOwner(clientOwner).build();
    }

    }
