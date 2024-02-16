package com.example.TripWiseBackend.Repositories.Hotel;

import com.example.TripWiseBackend.Entities.Hotel.HotelReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelReviewRepository extends JpaRepository<HotelReview, Integer> {

}
