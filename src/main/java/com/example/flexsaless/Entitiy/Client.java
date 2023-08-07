package com.example.flexsaless.Entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("productsList")
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 3, max = 80)
    @Column(nullable = false)
    private String clientName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Size(min = 8, max = 100)
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String clientArea;

    @Column
    private Integer sales;
    @Column
    private Double profit;
    @Column
    private Double revenue;
    @Column
    private LocalDate salesData;
    @OneToMany(mappedBy = "clientOwner", fetch = FetchType.EAGER)
    @Column
    @JsonManagedReference
    private List<Product> productsList;
    @OneToMany(mappedBy = "clientOrderOwner", fetch = FetchType.EAGER)
    @Column
    @JsonManagedReference(value="orderList")
    private List<OrderEntity> orderEntityList;
    @OneToOne
    @JsonManagedReference
    private ExcelFile excelFile;


    public Client(Long id, String clientName, String email, String password) {
        this.id = id;
        this.clientName = clientName;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
