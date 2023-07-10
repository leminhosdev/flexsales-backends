package com.example.flexsaless.Entitiy;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 80)
    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false, unique = true)
    @Email
    private String clientEmail;
    @Size(min = 8, max = 100)
    @Column(nullable = false)
    private String clientPassword;
    @Column(nullable = false)
    private String clientArea;


}
