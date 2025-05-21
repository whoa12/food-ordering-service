package com.projects.Food.Ordering.Website.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;


@Data
@Embeddable
public class RestaurantDto {
    private Long id;
    private String title;
    private List<String> images; // Comma separated image URLs
    private String description;


}
