package com.projects.Food.Ordering.Website.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
public class ContactInformation {
    private String instaId;
    private Long contactNo;

    private String email;
}
