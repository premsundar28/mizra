package org.example.controlller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.dto.RestaurantDTO;
import org.example.model.Restaurant;
import org.example.repository.RestaurantRespository;
import org.example.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RestaurantController {

    final private RestaurantService restaurantService;
    final private RestaurantRespository restaurantRespository;



    @PostMapping("/restro")
    public String addUser(@RequestBody RestaurantDTO restro) throws JsonProcessingException {

           restaurantService.addRestro(restro);
           return " restaurant added sucessfully ";


    }

        @GetMapping("getAllRestaurants")
        public List<Restaurant> getAllRestaurants(){
            return restaurantRespository.findAll();
        }




}
