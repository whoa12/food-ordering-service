package com.projects.Food.Ordering.Website.request;

import com.projects.Food.Ordering.Website.model.Category;
import lombok.Data;

import java.util.List;

@Data

public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private long restaurantId;
    private boolean veg;
}
