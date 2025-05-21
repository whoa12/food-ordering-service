package com.projects.Food.Ordering.Website.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long restaurantId;
    @OneToOne
    private User owner;
    private String description;
    private String name;
    private String cuisineType;
    @OneToOne
    private Address address;

    @Embedded
    private ContactInformation contactInfo;

    private String openingHours;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    @Column(length = 1000)
    private List<String> images = new ArrayList<>();

    private boolean open;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Food> foodItems = new ArrayList<>();


    private LocalDate registrationDate;

}
