package com.example.TripWiseBackend.Entities.Hotel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class HotelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imageUrl;
    private String publicId;

    @JsonIgnoreProperties(value = {"profile","hotelReviewList","hotelImageList"})
    @ManyToOne
    @JoinColumn(name = "hotelId",referencedColumnName = "id")
    private Hotel hotel;

    public HotelImage() {
        super();
    }

    public HotelImage(Integer id, String imageUrl, String publicId, Hotel hotel) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.publicId = publicId;
        this.hotel = hotel;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
