package com.example.TripWiseBackend.Services.All_Services_Impl;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Entities.Restaurant.Restaurant;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.RestaurantRequest;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Repositories.Restaurant.RestaurantRepository;
import com.example.TripWiseBackend.Services.All_Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public Restaurant addRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantRequest.getRestaurantName());
        restaurant.setRestaurantAddress(restaurantRequest.getRestaurantAddress());
        restaurant.setRestaurantCity(restaurantRequest.getRestaurantCity());
        restaurant.setRestaurantDescription(restaurantRequest.getRestaurantDescription());
        restaurant.setMinPrice(restaurantRequest.getMinPrice());
        restaurant.setMaxPrice(restaurantRequest.getMaxPrice());
        restaurant.setPostedAt(new Date());

        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        profile.addRestaurant(restaurant);
        restaurant.setProfile(profile);

        this.profileRepository.save(profile);
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public String deleteRestaurant(Integer restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","does not found!"));
        this.restaurantRepository.deleteById(restaurantId);
        return "Restaurant deleted Successfully!";
    }

    @Override
    public Restaurant getRestaurant(Integer restaurantId) throws ResourceNotFoundException {
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant","does not found!"));
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return this.restaurantRepository.findAll();
    }

}
