package com.example.TripWiseBackend.Repositories.Place;

import com.example.TripWiseBackend.Entities.Place.PlaceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceImageRepository extends JpaRepository<PlaceImage, Integer> {
}
