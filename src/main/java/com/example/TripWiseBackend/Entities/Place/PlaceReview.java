package com.example.TripWiseBackend.Entities.Place;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PlaceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String review;
    private Date postedAt;
    
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","hotelList","hotelReviewList","restaurantList","restaurantReviewList","placeList","placeReviewList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profile profile;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","profile","placeReviewList","placeImageList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placeId", referencedColumnName = "id")
    private Place place;

    public PlaceReview() {
        super();
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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
