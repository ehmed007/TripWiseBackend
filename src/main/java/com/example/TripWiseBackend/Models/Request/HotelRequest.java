package com.example.TripWiseBackend.Models.Request;

public class HotelRequest {

    private String hotelName;
    private String hotelDescription;
    private String hotelClass;
    private Boolean breakFastIncluded;
    private Integer minPrice;
    private Integer maxPrice;
    private Float hotelRating;
    private String hotelCity;
    private String hotelAddress;

    public HotelRequest() {
        super();
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

    @Override
    public String toString() {
        return "HotelRequest{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelDescription='" + hotelDescription + '\'' +
                ", hotelClass='" + hotelClass + '\'' +
                ", breakFastIncluded=" + breakFastIncluded +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", hotelRating=" + hotelRating +
                ", hotelCity='" + hotelCity + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                '}';
    }
}
