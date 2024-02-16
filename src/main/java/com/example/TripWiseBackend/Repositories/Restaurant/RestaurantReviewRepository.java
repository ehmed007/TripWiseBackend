package com.example.TripWiseBackend.Repositories.Restaurant;

import com.example.TripWiseBackend.Entities.Restaurant.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Integer> {
}
