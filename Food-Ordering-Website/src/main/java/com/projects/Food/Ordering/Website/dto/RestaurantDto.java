package com.projects.Food.Ordering.Website.dto;

import com.projects.Food.Ordering.Website.model.CategoryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;
import java.util.Objects;


@Data
@Embeddable
public class RestaurantDto {
    private Long id;
    private String title;
    @Column(length = 1000)
    private List<String> images; // Comma separated image URLs
    private String description;

    private String categoryName;
    private Long categoryId;








}
