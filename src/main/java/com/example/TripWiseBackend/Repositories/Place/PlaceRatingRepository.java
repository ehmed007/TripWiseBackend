package com.example.TripWiseBackend.Repositories.Place;

import com.example.TripWiseBackend.Entities.Place.PlaceRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRatingRepository extends JpaRepository<PlaceRating, Integer> {
}
