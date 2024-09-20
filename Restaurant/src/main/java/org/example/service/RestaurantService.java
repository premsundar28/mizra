package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.dto.RestaurantDTO;
import org.example.dto.mapper.RestaurantMapper;
import org.example.model.Menu;
import org.example.model.Restaurant;
import org.example.repository.RestaurantCacheRepository;
import org.example.repository.RestaurantRespository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantMapper restaurantMapper;  // MapStruct Mapper
    private final RestaurantRespository restaurantRespository;  // JPA Repository
    private final RestaurantCacheRepository restaurantCacheRepository;  // Cache repository

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addRestro(RestaurantDTO restaurantDTO) throws JsonProcessingException {
        // Convert RestaurantDTO to Restaurant entity using MapStruct
        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantDTO);

        // Ensure that all Menu items are correctly associated with the Restaurant
        for (Menu menu : restaurant.getMenu()) {
            if (menu.getId() != 0) {
                menu = entityManager.merge(menu);  // Manually merge detached Menu entities if necessary
            }
            menu.setRestaurant(restaurant);  // Set the bidirectional relationship
        }

        // Save the Restaurant entity to the database
        restaurantRespository.save(restaurant);

        // Optionally, save the Restaurant entity to a cache
        restaurantCacheRepository.addRestro(restaurant);
    }
}