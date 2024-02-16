package com.example.TripWiseBackend.Repositories.Restaurant;

import com.example.TripWiseBackend.Entities.Restaurant.RestaurantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Integer> {
}
