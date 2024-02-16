package com.example.TripWiseBackend.Entities.Place;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String placeName;
    private String placeDescription;
    private String placeRating;
    private String placeCity;
    private String placeAddress;
    private Date postedAt;

    @JsonIgnoreProperties(value = {"hotelList","hotelReviewList","restaurantList","restaurantReviewList","placeList","placeReviewList"})
    @ManyToOne
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profile profile;

    @JsonIgnoreProperties(value = {"profile","place"})
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceReview> placeReviewList;

    @JsonIgnoreProperties(value = {"place"})
    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<PlaceImage> placeImageList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "place_id", referencedColumnName = "id")
    private List<PlaceRating> placeRatingList;

    public Place() {
        super();
    }

    public Place(Integer id, String placeName, String placeDescription, String placeRating, String placeCity, String placeAddress, Date postedAt, Profile profile, List<PlaceReview> placeReviewList, List<PlaceImage> placeImageList, List<PlaceRating> placeRatingList) {
        this.id = id;
        this.placeName = placeName;
        this.placeDescription = placeDescription;
        this.placeRating = placeRating;
        this.placeCity = placeCity;
        this.placeAddress = placeAddress;
        this.postedAt = postedAt;
        this.profile = profile;
        this.placeReviewList = placeReviewList;
        this.placeImageList = placeImageList;
        this.placeRatingList = placeRatingList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public String getPlaceRating() {
        return placeRating;
    }

    public void setPlaceRating(String placeRating) {
        this.placeRating = placeRating;
    }

    public String getPlaceCity() {
        return placeCity;
    }

    public void setPlaceCity(String placeCity) {
        this.placeCity = placeCity;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
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

    public List<PlaceReview> getPlaceReviewList() {
        return placeReviewList;
    }

    public void setPlaceReviewList(List<PlaceReview> placeReviewList) {
        this.placeReviewList = placeReviewList;
    }

    public List<PlaceImage> getPlaceImageList() {
        return placeImageList;
    }

    public void setPlaceImageList(List<PlaceImage> placeImageList) {
        this.placeImageList = placeImageList;
    }

    public List<PlaceRating> getPlaceRatingList() {
        return placeRatingList;
    }

    public void setPlaceRatingList(List<PlaceRating> placeRatingList) {
        this.placeRatingList = placeRatingList;
    }

    public void addPlaceReview(PlaceReview placeReview) {
        if (this.placeReviewList == null) {
            this.placeReviewList = new ArrayList<>();
            this.placeReviewList.add(placeReview);
        } else {
            this.placeReviewList.add(placeReview);
        }
    }

    public void addPlaceImage(PlaceImage placeImage) {
        if (this.placeImageList == null) {
            this.placeImageList = new ArrayList<>();
            this.placeImageList.add(placeImage);
        } else {
            this.placeImageList.add(placeImage);
        }
    }

    public void addPlaceRating(PlaceRating placeRating) {
        if (this.placeRatingList == null) {
            this.placeRatingList = new ArrayList<>();
            this.placeRatingList.add(placeRating);
        } else {
            this.placeRatingList.add(placeRating);
        }
    }

}
