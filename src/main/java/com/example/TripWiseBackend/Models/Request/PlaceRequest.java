package com.example.TripWiseBackend.Models.Request;

public class PlaceRequest {
    private String placeName;
    private String placeDescription;
    private String placeCity;
    private String placeAddress;

    public PlaceRequest() {
        super();
    }

    public PlaceRequest(String placeName, String placeDescription, String placeCity, String placeAddress) {
        this.placeName = placeName;
        this.placeDescription = placeDescription;
        this.placeCity = placeCity;
        this.placeAddress = placeAddress;
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
}
