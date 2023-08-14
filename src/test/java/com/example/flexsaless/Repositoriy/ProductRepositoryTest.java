package com.example.flexsaless.Repositoriy;

import com.example.flexsaless.Entitiy.Client;
import com.example.flexsaless.Entitiy.Product;
import com.example.flexsaless.Repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void save_PersistClient_WhenSucessful(){
        Product productTobeSaved = this.createProduct();
        Product productSaved =  this.productRepository.save(productTobeSaved);
        Assertions.assertNotNull(productSaved);
        Assertions.assertNotNull(productSaved.getId());
        Assertions.assertEquals(productSaved, productTobeSaved);
    }
    @Test
    void update_UpdateClient_WhenSucessful(){
        Product productTobeSaved = this.createProduct();
        Product productSaved =  this.productRepository.save(productTobeSaved);
        productSaved.setName("see this name");
        Product productUpdated =  this.productRepository.save(productSaved);

        Assertions.assertNotNull(productUpdated);
        Assertions.assertNotNull(productUpdated.getId());
        Assertions.assertEquals(productSaved, productTobeSaved);
    }
    @Test
    void delete_DeleteClient_WhenSucessful(){
        Product productTobeSaved = this.createProduct();
        Product productSaved =  this.productRepository.save(productTobeSaved);
        this.productRepository.delete(productSaved);
        Optional<Product> productOptional = this.productRepository.findById(productSaved.getId());
        Assertions.assertTrue(productOptional.isEmpty());

    }

    private Product createProduct(){

        Client clientOwner = Client.builder().clientName("Peoples").clientArea("Business").email("ada3ff@gmail.com")
                .password("topasdm3das").build();

      return   Product.builder().price(new BigDecimal(200)).code("adasd3d")
                .name("ropeiro dois 2")
                .commission(new BigDecimal(10)).taxes(new BigDecimal(2))
                .clientOwner(clientOwner).build();
    }
}
