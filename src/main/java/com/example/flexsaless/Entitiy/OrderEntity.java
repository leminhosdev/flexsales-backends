package com.example.flexsaless.Entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate salesData;

    private BigDecimal totalPrice;
    private BigDecimal totalTaxesValue;
    private BigDecimal totalCommissionValue;
    private Integer productsAmount;
    private String name;
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonBackReference(value="orderList")
    private Client clientOrderOwner;


    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.EAGER)
    @Column
    private List<Product> productList;

}
