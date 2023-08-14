package com.example.flexsaless.Repositoriy;

import com.example.flexsaless.Repository.ClientRepository;
import com.example.flexsaless.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository clientRepository;

}
