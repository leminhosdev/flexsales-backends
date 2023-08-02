package com.example.flexsaless.Repository;

import com.example.flexsaless.Entitiy.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByNameContainingIgnoreCaseOrCodeContainingIgnoreCaseAndClientOwnerId(
            String key1, String key2, Long clientId
    );
}
