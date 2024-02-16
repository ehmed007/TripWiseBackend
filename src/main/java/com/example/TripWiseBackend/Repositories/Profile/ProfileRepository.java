package com.example.TripWiseBackend.Repositories.Profile;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    public Optional<Profile> findByUsername(String username);
}
