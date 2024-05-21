package com.example.TripWiseBackend.SpringRestControllers;

import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import com.example.TripWiseBackend.Entities.Hotel.HotelRating;
import com.example.TripWiseBackend.Entities.Hotel.HotelReview;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Entities.Restaurant.Dish;
import com.example.TripWiseBackend.Entities.Restaurant.Restaurant;
import com.example.TripWiseBackend.Entities.Restaurant.RestaurantRating;
import com.example.TripWiseBackend.Entities.Restaurant.RestaurantReview;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.RestaurantRequest;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Repositories.Restaurant.DishRepository;
import com.example.TripWiseBackend.Repositories.Restaurant.RestaurantRepository;
import com.example.TripWiseBackend.Services.All_Services.ImageService;
import com.example.TripWiseBackend.Services.All_Services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurant")
public class RestaurantControllers {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private ImageService imageService;

    @PostMapping("/addRestaurant")
    public Restaurant addRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        return this.restaurantService.addRestaurant(restaurantRequest);
    }

    @DeleteMapping("/deleteRestaurant/{restaurantId}")
    public String deleteRestaurant(@PathVariable Integer restaurantId) throws ResourceNotFoundException {
        return this.restaurantService.deleteRestaurant(restaurantId);
    }

    @GetMapping("/getRestaurant/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable Integer restaurantId) throws ResourceNotFoundException {
        return this.restaurantService.getRestaurant(restaurantId);
    }

    @GetMapping("/getAllRestaurant")
    public List<Restaurant> getAllRestaurant() {
        return this.restaurantService.getAllRestaurant();
    }

    @PostMapping("/uploadRestaurantImage/{restaurantId}")
    public ResponseEntity<Object> uploadRestaurantImage(@RequestParam("image") MultipartFile image, @PathVariable int restaurantId) throws Exception {
        Map response = new HashMap<>();
        String url = (String) this.imageService.addRestaurantImage(image,restaurantId).get("url");
        response.put("imageUrl",url);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/addRestaurantReview/{restaurantId}")
    public String addRestaurantReview(@PathVariable int restaurantId, @RequestBody RestaurantReview restaurantReview) throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Restaurant restaurant = this.restaurantService.getRestaurant(restaurantId);
        restaurantReview.setProfile(profile);
        restaurantReview.setPostedAt(new Date());
        restaurantReview.setRestaurant(restaurant);
        restaurant.addRestaurantReview(restaurantReview);
        profile.addRestaurantReview(restaurantReview);
        this.restaurantRepository.save(restaurant);
        this.profileRepository.save(profile);
        return "Review Added Successfully!";
    }

    @PostMapping("/addRestaurantRating/{restaurantId}")
    public String addRestaurantRating(@PathVariable int restaurantId, @RequestBody RestaurantRating restaurantRating) throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Restaurant restaurant = this.restaurantService.getRestaurant(restaurantId);
        restaurant.addRestaurantRating(restaurantRating);
        this.restaurantRepository.save(restaurant);
        return "Rating Added Successfully!";
    }

    @PostMapping("/addDish/{restaurantId}")
    public String addDish(@PathVariable int restaurantId, @RequestBody Dish dish) throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Restaurant restaurant = this.restaurantService.getRestaurant(restaurantId);
//        restaurant.addDish(dish);
        dish.addRestaurant(restaurant);
        this.dishRepository.save(dish);
        return "Rating Added Successfully!";
    }

}
