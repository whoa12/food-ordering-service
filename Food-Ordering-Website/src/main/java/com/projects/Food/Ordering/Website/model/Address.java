package com.projects.Food.Ordering.Website.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_number", length = 50)
    private String houseNumber;

    @Column(length = 100)
    private String street;

    @Column(length = 100)
    private String area;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(length = 50)
    private String state;

    @Column(name = "postal_code", nullable = false, length = 20)
    private String postalCode;

    @Column(length = 50)
    private String country;
}