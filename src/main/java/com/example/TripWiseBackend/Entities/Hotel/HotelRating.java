package com.example.TripWiseBackend.Entities.Hotel;

import jakarta.persistence.*;

@Entity
public class HotelRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float rating;

    public HotelRating(Integer id, Float rating) {
        this.id = id;
        this.rating = rating;
    }

    public HotelRating() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
