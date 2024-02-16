package com.example.TripWiseBackend.Repositories.Hotel;

import com.example.TripWiseBackend.Entities.Hotel.HotelRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRatingRepository extends JpaRepository<HotelRating, Integer> {

}
