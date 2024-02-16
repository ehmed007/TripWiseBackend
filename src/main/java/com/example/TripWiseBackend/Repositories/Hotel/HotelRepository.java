package com.example.TripWiseBackend.Repositories.Hotel;

import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
