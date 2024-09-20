package org.example.dto;

import lombok.*;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {
    private long id;
    private String restaurantName;
    private String houseNo;
    private String locality;
    private String city;
    private String state;
    private String description;
    private String ownerName;
    private String phoneNumber;
    private String email;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private List<MenuDTO> menu;
}