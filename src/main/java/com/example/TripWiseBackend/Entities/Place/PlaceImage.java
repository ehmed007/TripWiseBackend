package com.example.TripWiseBackend.Entities.Place;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class PlaceImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imageUrl;
    private String publicId;

    @JsonIgnoreProperties(value = {"profile","placeReviewList","placeImageList"})
    @ManyToOne
    @JoinColumn(name = "placeId",referencedColumnName = "id")
    private Place place;

    public PlaceImage() {
        super();
    }

    public PlaceImage(Integer id, String imageUrl, String publicId, Place place) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.publicId = publicId;
        this.place = place;
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
