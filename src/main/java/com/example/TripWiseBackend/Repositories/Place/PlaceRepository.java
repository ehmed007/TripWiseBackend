package com.example.TripWiseBackend.Repositories.Place;

import com.example.TripWiseBackend.Entities.Place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

}
