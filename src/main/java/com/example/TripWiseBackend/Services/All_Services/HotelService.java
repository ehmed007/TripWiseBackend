package com.example.TripWiseBackend.Services.All_Services;

import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.HotelRequest;

import java.util.List;

public interface HotelService {
    public Hotel addHotel(HotelRequest hotelRequest);
    public Hotel editHotel(Integer hotelId,HotelRequest hotelRequest) throws ResourceNotFoundException;
    public String removeHotel(Integer hotelId) throws ResourceNotFoundException;
    public Hotel getHotel(Integer hotelId) throws ResourceNotFoundException;
    public List<Hotel> getAllHotel();
}
