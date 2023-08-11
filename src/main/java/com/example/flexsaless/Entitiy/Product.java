package com.example.flexsaless.Entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String code;
    @Column(name = "name")
    private String name;
    private BigDecimal price;

    private BigDecimal commission;
    private BigDecimal taxes;

    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference
    private Client clientOwner;

    @ManyToOne
    @JoinColumn(name = "orderEntity_id")
    private OrderEntity orderEntity;
}
