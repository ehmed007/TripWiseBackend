package com.example.TripWiseBackend.Entities.Hotel;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String hotelName;
    private String hotelDescription;
    private String hotelClass;
    private Boolean breakFastIncluded;
    private Integer minPrice;
    private Integer maxPrice;
    private Float hotelRating;
    private String hotelCity;
    private String hotelAddress;
    private Date postedAt;


    @JsonIgnoreProperties(value = {"hotelList","hotelReviewList","restaurantList","restaurantReviewList","placeList","placeReviewList"})
    @ManyToOne
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profile profile;

    @JsonIgnoreProperties(value = {"profile","hotel"})
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelReview> hotelReviewList;

    @JsonIgnoreProperties(value = {"hotel"})
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<HotelImage> hotelImageList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private List<HotelRating> hotelRatingList;


    public Hotel() {
        super();
    }

    public Hotel(Integer id, String hotelName, String hotelDescription, String hotelClass, Boolean breakFastIncluded, Integer minPrice, Integer maxPrice, Float hotelRating, String hotelCity, String hotelAddress, Date postedAt, Profile profile, List<HotelReview> hotelReviewList, List<HotelImage> hotelImageList, List<HotelRating> hotelRatingList) {
        this.id = id;
        this.hotelName = hotelName;
        this.hotelDescription = hotelDescription;
        this.hotelClass = hotelClass;
        this.breakFastIncluded = breakFastIncluded;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.hotelRating = hotelRating;
        this.hotelCity = hotelCity;
        this.hotelAddress = hotelAddress;
        this.postedAt = postedAt;
        this.profile = profile;
        this.hotelReviewList = hotelReviewList;
        this.hotelImageList = hotelImageList;
        this.hotelRatingList = hotelRatingList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public String getHotelClass() {
        return hotelClass;
    }

    public void setHotelClass(String hotelClass) {
        this.hotelClass = hotelClass;
    }

    public Boolean getBreakFastIncluded() {
        return breakFastIncluded;
    }

    public void setBreakFastIncluded(Boolean breakFastIncluded) {
        this.breakFastIncluded = breakFastIncluded;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Float getHotelRating() {
        return hotelRating;
    }

    public void setHotelRating(Float hotelRating) {
        this.hotelRating = hotelRating;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
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

    public List<HotelReview> getHotelReviewList() {
        return hotelReviewList;
    }

    public void setHotelReviewList(List<HotelReview> hotelReviewList) {
        this.hotelReviewList = hotelReviewList;
    }

    public List<HotelImage> getHotelImageList() {
        return hotelImageList;
    }

    public void setHotelImageList(List<HotelImage> hotelImageList) {
        this.hotelImageList = hotelImageList;
    }

    public List<HotelRating> getHotelRatingList() {
        return hotelRatingList;
    }

    public void setHotelRatingList(List<HotelRating> hotelRatingList) {
        this.hotelRatingList = hotelRatingList;
    }

    public void addHotelReview(HotelReview hotelReview) {
        if (this.hotelReviewList == null) {
            this.hotelReviewList = new ArrayList<>();
            this.hotelReviewList.add(hotelReview);
        } else {
            this.hotelReviewList.add(hotelReview);
        }
    }

    public void addHotelImage(HotelImage hotelImage) {
        if (this.hotelImageList == null) {
            this.hotelImageList = new ArrayList<>();
            this.hotelImageList.add(hotelImage);
        } else {
            this.hotelImageList.add(hotelImage);
        }
    }

    public void addHotelRating(HotelRating hotelRating) {
        if (this.hotelRatingList == null) {
            this.hotelRatingList = new ArrayList<>();
            this.hotelRatingList.add(hotelRating);
        } else {
            this.hotelRatingList.add(hotelRating);
        }
    }


}
