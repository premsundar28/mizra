package org.example.repository;

import org.example.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRespository  extends JpaRepository<Restaurant,Long> {

    Restaurant findByRestaurantName(String username);
}
