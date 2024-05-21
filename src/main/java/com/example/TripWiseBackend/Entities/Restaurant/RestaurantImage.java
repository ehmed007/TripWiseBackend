package com.example.TripWiseBackend.Entities.Restaurant;

import jakarta.persistence.*;

@Entity
public class RestaurantImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imageUrl;
    private String publicId;

    public RestaurantImage() {
        super();
    }

    public RestaurantImage(Integer id, String imageUrl, String publicId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.publicId = publicId;
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

}
