package com.example.TripWiseBackend.Entities.Restaurant;

import com.example.TripWiseBackend.Entities.Place.PlaceRating;
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
    private Float restaurantRating;
    private String restaurantCity;
    private String restaurantAddress;
    private Date postedAt;


    @JsonIgnoreProperties(value = {"hotelList","hotelReviewList","restaurantList","restaurantReviewList","placeList","placeReviewList"})
    @ManyToOne
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profile profile;

    @JsonIgnoreProperties(value = {"restaurant","profile"})
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantReview> restaurantReviewList;

    @JsonIgnoreProperties(value = {"restaurant"})
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantImage> restaurantImageList;

    @JsonIgnoreProperties(value = {"restaurantList"})
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name = "Restaurant_Dish",
            joinColumns = @JoinColumn(name = "Dish_id"),
            inverseJoinColumns = @JoinColumn(name = "Restaurant_id")
    )
    private List<Dish> dishList;

    @JsonIgnoreProperties(value = {"restaurantList"})
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name = "Restaurant_Cuisine",
            joinColumns = @JoinColumn(name = "Cuisine_id"),
            inverseJoinColumns = @JoinColumn(name = "Restaurant_id")
    )
    private List<Cuisine> cuisineList;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private List<RestaurantRating> restaurantRatingList;


    public Restaurant() {
        super();
    }

    public Restaurant(Integer id, String restaurantName, String restaurantDescription, Float minPrice, Float maxPrice, Float restaurantRating, String restaurantCity, String restaurantAddress, Date postedAt, Profile profile, List<RestaurantReview> restaurantReviewList, List<RestaurantImage> restaurantImageList, List<Dish> dishList, List<Cuisine> cuisineList, List<RestaurantRating> restaurantRatingList) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.restaurantRating = restaurantRating;
        this.restaurantCity = restaurantCity;
        this.restaurantAddress = restaurantAddress;
        this.postedAt = postedAt;
        this.profile = profile;
        this.restaurantReviewList = restaurantReviewList;
        this.restaurantImageList = restaurantImageList;
        this.dishList = dishList;
        this.cuisineList = cuisineList;
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

    public Float getRestaurantRating() {
        return restaurantRating;
    }

    public void setRestaurantRating(Float restaurantRating) {
        this.restaurantRating = restaurantRating;
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

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public List<Cuisine> getCuisineList() {
        return cuisineList;
    }

    public void setCuisineList(List<Cuisine> cuisineList) {
        this.cuisineList = cuisineList;
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

    public void addDish(Dish dish) {
        if (this.dishList == null) {
            this.dishList = new ArrayList<>();
            this.dishList.add(dish);
        }
        this.dishList.add(dish);
    }

    public void addCuisine(Cuisine cuisine) {
        if (this.cuisineList == null) {
            this.cuisineList = new ArrayList<>();
            this.cuisineList.add(cuisine);
        }
        this.cuisineList.add(cuisine);
    }

    public void addRating(RestaurantRating restaurantRating) {
        if (this.restaurantRatingList == null) {
            this.restaurantRatingList = new ArrayList<>();
            this.restaurantRatingList.add(restaurantRating);
        } else {
            this.restaurantRatingList.add(restaurantRating);
        }
    }

}
