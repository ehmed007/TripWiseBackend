package com.example.TripWiseBackend.Repositories.Place;

import com.example.TripWiseBackend.Entities.Place.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Integer> {
    @Query(value = "SELECT * FROM place_review WHERE place_id = :place_id", nativeQuery = true)
    public List<PlaceReview> getPlaceReviewsByPlaceId(@Param("place_id") Integer place_id);
}
