package com.example.TripWiseBackend.Entities.Restaurant;

import jakarta.persistence.*;

@Entity
public class RestaurantImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imageUrl;
    private String publicId;

    @ManyToOne
    @JoinColumn(name = "restaurantId",referencedColumnName = "id")
    private Restaurant restaurant;

    public RestaurantImage() {
        super();
    }

    public RestaurantImage(Integer id, String imageUrl, String publicId, Restaurant restaurant) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.publicId = publicId;
        this.restaurant = restaurant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
