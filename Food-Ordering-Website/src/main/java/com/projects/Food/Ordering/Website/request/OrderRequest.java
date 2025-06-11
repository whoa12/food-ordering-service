package com.projects.Food.Ordering.Website.request;

import com.projects.Food.Ordering.Website.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;
}
