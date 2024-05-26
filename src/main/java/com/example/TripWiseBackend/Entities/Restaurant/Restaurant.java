package com.example.TripWiseBackend.Entities.Restaurant;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String restaurantName;
    private String restaurantDescription;
    private Float minPrice;
    private Float maxPrice;
    private String restaurantCity;
    private String restaurantAddress;
    private Date postedAt;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","hotelList","hotelReviewList","restaurantList","restaurantReviewList","placeList","placeReviewList","thingsToDoList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","restaurant","profile"})
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RestaurantReview> restaurantReviewList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private List<RestaurantImage> restaurantImageList;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private List<RestaurantRating> restaurantRatingList;

    public Restaurant() {
        super();
    }

    public Restaurant(Integer id, String restaurantName, String restaurantDescription, Float minPrice, Float maxPrice, String restaurantCity, String restaurantAddress, Date postedAt, Profile profile, List<RestaurantReview> restaurantReviewList, List<RestaurantImage> restaurantImageList, List<RestaurantRating> restaurantRatingList) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.restaurantCity = restaurantCity;
        this.restaurantAddress = restaurantAddress;
        this.postedAt = postedAt;
        this.profile = profile;
        this.restaurantReviewList = restaurantReviewList;
        this.restaurantImageList = restaurantImageList;
        this.restaurantRatingList = restaurantRatingList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
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

    public List<RestaurantReview> getRestaurantReviewList() {
        return restaurantReviewList;
    }

    public void setRestaurantReviewList(List<RestaurantReview> restaurantReviewList) {
        this.restaurantReviewList = restaurantReviewList;
    }

    public List<RestaurantImage> getRestaurantImageList() {
        return restaurantImageList;
    }

    public void setRestaurantImageList(List<RestaurantImage> restaurantImageList) {
        this.restaurantImageList = restaurantImageList;
    }

    public List<RestaurantRating> getRestaurantRatingList() {
        return restaurantRatingList;
    }

    public void setRestaurantRatingList(List<RestaurantRating> restaurantRatingList) {
        this.restaurantRatingList = restaurantRatingList;
    }

    public void addRestaurantReview(RestaurantReview restaurantReview) {
        if (this.restaurantReviewList == null) {
            this.restaurantReviewList = new ArrayList<>();
            this.restaurantReviewList.add(restaurantReview);
        }
        this.restaurantReviewList.add(restaurantReview);
    }

    public void addRestaurantImage(RestaurantImage restaurantImage) {
        if (this.restaurantImageList == null) {
            this.restaurantImageList = new ArrayList<>();
            this.restaurantImageList.add(restaurantImage);
        }
        this.restaurantImageList.add(restaurantImage);
    }

    public void addRestaurantRating(RestaurantRating restaurantRating) {
        if (this.restaurantRatingList == null) {
            this.restaurantRatingList = new ArrayList<>();
            this.restaurantRatingList.add(restaurantRating);
        } else {
            this.restaurantRatingList.add(restaurantRating);
        }
    }

}
