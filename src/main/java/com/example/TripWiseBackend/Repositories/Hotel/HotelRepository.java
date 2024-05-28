package com.example.TripWiseBackend.Repositories.Hotel;

import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import com.example.TripWiseBackend.Entities.Hotel.HotelReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query(value = "SELECT * FROM hotel WHERE profile_id = :profile_id", nativeQuery = true)
    public List<Hotel> getHotelByProfileId(@Param("profile_id") Integer profile_id);

    @Query(value = "SELECT * FROM hotel WHERE LOWER(hotel_city) LIKE LOWER(:city)", nativeQuery = true)
    public List<Hotel> getHotelByCity(@Param("city") String city);
}
