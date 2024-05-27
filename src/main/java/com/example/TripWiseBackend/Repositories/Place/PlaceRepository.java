package com.example.TripWiseBackend.Repositories.Place;

import com.example.TripWiseBackend.Entities.Place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    @Query(value = "SELECT * FROM place WHERE profile_id = :profile_id", nativeQuery = true)
    public List<Place> getPlaceByProfileId(@Param("profile_id") Integer profile_id);

}
