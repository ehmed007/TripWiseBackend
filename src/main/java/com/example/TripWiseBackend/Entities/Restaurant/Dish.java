package com.example.TripWiseBackend.Entities.Restaurant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String dishName;

    @JsonIgnoreProperties(value = {"profile","restaurantReviewList","restaurantImageList","dishList","cuisineList"})
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name = "Restaurant_Dish",
            joinColumns = @JoinColumn(name = "Restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "Dish_id")
    )
    private List<Restaurant> restaurantList;

    public Dish() {
        super();
    }

    public Dish(Integer id, String dishName, List<Restaurant> restaurantList) {
        this.id = id;
        this.dishName = dishName;
        this.restaurantList = restaurantList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public void addRestaurant(Restaurant restaurant) {
        if (this.restaurantList == null) {
            this.restaurantList = new ArrayList<>();
            this.restaurantList.add(restaurant);
        }
        this.restaurantList.add(restaurant);
    }
}
