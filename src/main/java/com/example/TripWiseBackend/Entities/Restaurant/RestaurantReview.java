package com.example.TripWiseBackend.Entities.Restaurant;

import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RestaurantReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String review;
    private Date postedAt;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","hotelList","hotelReviewList","restaurantList","restaurantReviewList","placeList","placeReviewList","thingsToDoList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","profile","restaurantReviewList","restaurantImageList","dishList","restaurantRatingList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public RestaurantReview() {
        super();
    }

    public RestaurantReview(Integer id, String review, Date postedAt, Profile profile, Restaurant restaurant) {
        this.id = id;
        this.review = review;
        this.postedAt = postedAt;
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
