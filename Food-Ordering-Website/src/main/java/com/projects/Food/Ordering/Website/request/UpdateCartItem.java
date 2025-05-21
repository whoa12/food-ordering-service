package com.projects.Food.Ordering.Website.request;

import lombok.Data;

@Data
public class UpdateCartItem {
    private Long cartItemId;
    private int quantity;
}
