package com.example.TripWiseBackend.Repositories.Place;

import com.example.TripWiseBackend.Entities.Place.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Integer> {
}
