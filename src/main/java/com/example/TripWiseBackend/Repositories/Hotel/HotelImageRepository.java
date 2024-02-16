package com.example.TripWiseBackend.Repositories.Hotel;

import com.example.TripWiseBackend.Entities.Hotel.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, Integer> {
}
