package com.example.TripWiseBackend.Models.Request;

public class RestaurantRequest {
    private String restaurantName;
    private String restaurantDescription;
    private Float minPrice;
    private Float maxPrice;
    private String restaurantCity;
    private String restaurantAddress;

    public RestaurantRequest() {
        super();
    }

    public RestaurantRequest(String restaurantName, String restaurantDescription, Float minPrice, Float maxPrice, String restaurantCity, String restaurantAddress) {
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.restaurantCity = restaurantCity;
        this.restaurantAddress = restaurantAddress;
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
}
