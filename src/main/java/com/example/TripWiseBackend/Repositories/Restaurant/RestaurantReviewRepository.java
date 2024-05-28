package com.example.TripWiseBackend.Repositories.Restaurant;

import com.example.TripWiseBackend.Entities.Restaurant.RestaurantReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantReviewRepository extends JpaRepository<RestaurantReview, Integer> {

    @Query(value = "SELECT * FROM restaurant_review WHERE restaurant_id = :restaurant_id", nativeQuery = true)
    public List<RestaurantReview> getRestaurantReviewsByRestaurantId(@Param("restaurant_id") Integer restaurant_id);
}
