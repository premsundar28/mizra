package org.example.dto.mapper;

import org.example.dto.RestaurantDTO;
import org.example.model.Restaurant;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDTO toRestaurantDTO(Restaurant restaurant);

    Restaurant toRestaurant(RestaurantDTO restaurantDTO);
}