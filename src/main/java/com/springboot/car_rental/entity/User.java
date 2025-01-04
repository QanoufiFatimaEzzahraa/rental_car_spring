package com.springboot.car_rental.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.car_rental.enums.UserRole;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role; // "CLIENT" ou "ADMIN"

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;
    private String address;

    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Reservation> history = new ArrayList<>();
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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