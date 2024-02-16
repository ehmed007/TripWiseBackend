package com.example.TripWiseBackend.Repositories.Restaurant;

import com.example.TripWiseBackend.Entities.Restaurant.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {

}
