package org.example.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {
    private long id;
    private String dishName;
    private double price;
    private String description;
    private long restaurantId;  // Use restaurant ID for association
}