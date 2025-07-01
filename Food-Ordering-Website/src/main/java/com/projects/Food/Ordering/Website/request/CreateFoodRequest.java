package com.projects.Food.Ordering.Website.request;

import com.projects.Food.Ordering.Website.model.CategoryEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.List;

@Data

public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private CategoryEntity categoryEntity;
    @Column(length = 1000)
    private List<String> images;
    private Long restaurantId;
    private boolean veg;
}
