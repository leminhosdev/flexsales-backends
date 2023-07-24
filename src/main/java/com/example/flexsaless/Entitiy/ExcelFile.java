package com.example.flexsaless.Entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;
    @Lob
    private byte[] data;
    @JsonBackReference
    @OneToOne
    private Client clientOwner;

    public ExcelFile(String name, String type, byte[] data, Client clientOwner) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.clientOwner = clientOwner;
    }
}
