package com.projects.Food.Ordering.Website.request;

import com.projects.Food.Ordering.Website.model.Address;
import com.projects.Food.Ordering.Website.model.ContactInformation;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private ContactInformation contactInformation;
    private String openingHours;
    private List<String> images;
    private Date registrationDate;
}
