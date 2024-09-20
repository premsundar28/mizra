package org.example.repository;

import org.example.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


import java.time.Duration;
import java.time.temporal.TemporalUnit;

@Repository
public class RestaurantCacheRepository {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final String RESTAURANT_KEY_PREFIX = "restro::";

    public void addRestro(Restaurant restaurant) {
    }

    public Restaurant getRestro(String username) {
      return   (Restaurant) redisTemplate.opsForValue().get(getKey(username));
    }

    public void add(Restaurant restro) {
        redisTemplate.opsForValue().set(getKey(restro.getRestaurantName()), restro, Duration.ofMinutes(10));
    }

    private String getKey(String userId){
        return RESTAURANT_KEY_PREFIX + userId;
    }
}
