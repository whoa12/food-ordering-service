package com.projects.Food.Ordering.Website.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projects.Food.Ordering.Website.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private String password;

    private String email;

    private USER_ROLE role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses ;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders ;

    @ElementCollection


    private List<RestaurantDto> favRestaurants ;





}
