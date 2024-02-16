package com.example.TripWiseBackend.Entities.Restaurant;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RestaurantReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String review;
    private Date postedAt;
    private Float rating;

    @ManyToOne
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "restaurantId", referencedColumnName = "id")
    private Restaurant restaurant;

    public RestaurantReview() {
        super();
    }

    public RestaurantReview(Integer id, String review, Date postedAt, Float rating, Profile profile, Restaurant restaurant) {
        this.id = id;
        this.review = review;
        this.postedAt = postedAt;
        this.rating = rating;
        this.profile = profile;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Date postedAt) {
        this.postedAt = postedAt;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
