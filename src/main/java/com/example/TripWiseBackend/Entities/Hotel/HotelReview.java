package com.example.TripWiseBackend.Entities.Hotel;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class HotelReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String review;
    private Date postedAt;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","hotelList","hotelReviewList","restaurantList","restaurantReviewList","placeList","placeReviewList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","profile","hotelReviewList","hotelImageList","hotelRatingList"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public HotelReview() {
        super();
    }

    public HotelReview(Integer id, String review, Date postedAt, Profile profile, Hotel hotel) {
        this.id = id;
        this.review = review;
        this.postedAt = postedAt;
        this.profile = profile;
        this.hotel = hotel;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "HotelReview{" +
                "id=" + id +
                ", review='" + review + '\'' +
                ", postedAt=" + postedAt +
                ", profile=" + profile +
                '}';
    }
}
