package com.example.TripWiseBackend.Entities.Restaurant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cuisine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cuisineName;


    @JsonIgnoreProperties(value = {"profile","restaurantReviewList","restaurantImageList","dishList","cuisineList"})
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name = "Restaurant_Cuisine",
            joinColumns = @JoinColumn(name = "Restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "Cuisine_id")
    )
    private List<Restaurant> restaurantList;

    public Cuisine() {
        super();
    }

    public Cuisine(Integer id, String cuisineName, List<Restaurant> restaurantList) {
        this.id = id;
        this.cuisineName = cuisineName;
        this.restaurantList = restaurantList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
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
