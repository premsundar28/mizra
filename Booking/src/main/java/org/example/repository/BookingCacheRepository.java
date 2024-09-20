package org.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingCacheRepository {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private static final String RESTAURANT_KEY_PREFIX = "restro::";






    private String getKey(String userId){
        return RESTAURANT_KEY_PREFIX + userId;
    }
}
