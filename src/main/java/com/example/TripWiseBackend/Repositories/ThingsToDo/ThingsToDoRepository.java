package com.example.TripWiseBackend.Repositories.ThingsToDo;

import com.example.TripWiseBackend.Entities.ThingsToDo.ThingsToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThingsToDoRepository extends JpaRepository<ThingsToDo, Integer> {
}
