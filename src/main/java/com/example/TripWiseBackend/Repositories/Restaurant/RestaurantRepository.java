package com.example.TripWiseBackend.Repositories.Restaurant;

import com.example.TripWiseBackend.Entities.Hotel.HotelReview;
import com.example.TripWiseBackend.Entities.Place.Place;
import com.example.TripWiseBackend.Entities.Restaurant.Restaurant;
import com.example.TripWiseBackend.Entities.Restaurant.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query(value = "SELECT * FROM restaurant WHERE profile_id = :profile_id", nativeQuery = true)
    public List<Restaurant> getRestaurantByProfileId(@Param("profile_id") Integer profile_id);

    @Query(value = "SELECT * FROM restaurant WHERE LOWER(restaurant_city) LIKE LOWER(:city)", nativeQuery = true)
    public List<Restaurant> getRestaurantByCity(@Param("city") String city);

}
