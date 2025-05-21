package com.projects.Food.Ordering.Website.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private double price;


    @Enumerated(EnumType.STRING)
    private Category foodCategory;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    private Restaurant restaurant;

    private boolean isVeg;
}
