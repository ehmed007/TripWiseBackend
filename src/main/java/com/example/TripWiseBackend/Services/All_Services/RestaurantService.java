package com.example.TripWiseBackend.Services.All_Services;

import com.example.TripWiseBackend.Entities.Restaurant.Restaurant;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.RestaurantRequest;

import java.util.List;

public interface RestaurantService {
    public Restaurant addRestaurant(RestaurantRequest restaurantRequest);
    public String deleteRestaurant(Integer restaurantId) throws ResourceNotFoundException;
    public Restaurant getRestaurant(Integer restaurantId) throws ResourceNotFoundException;
    public List<Restaurant> getAllRestaurant();
}
