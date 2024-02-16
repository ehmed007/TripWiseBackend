package com.example.TripWiseBackend.Repositories.Restaurant;

import com.example.TripWiseBackend.Entities.Restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
