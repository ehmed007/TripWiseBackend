package com.example.TripWiseBackend.Repositories.Hotel;

import com.example.TripWiseBackend.Entities.Hotel.HotelReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelReviewRepository extends JpaRepository<HotelReview, Integer> {
    @Query(value = "SELECT * FROM hotel_review WHERE profile_id = :profile_id", nativeQuery = true)
    public List<HotelReview> getHotelReviewsByProfileId(@Param("profile_id") Integer profile_id);

    @Query(value = "SELECT * FROM hotel_review WHERE hotel_id = :hotel_id", nativeQuery = true)
    public List<HotelReview> getHotelReviewsByHotelId(@Param("hotel_id") Integer hotel_id);

}
