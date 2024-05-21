package com.example.TripWiseBackend.Services.All_Services_Impl;

import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.HotelRequest;
import com.example.TripWiseBackend.Repositories.Hotel.HotelImageRepository;
import com.example.TripWiseBackend.Repositories.Hotel.HotelRatingRepository;
import com.example.TripWiseBackend.Repositories.Hotel.HotelRepository;
import com.example.TripWiseBackend.Repositories.Hotel.HotelReviewRepository;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Services.All_Services.HotelService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private HotelReviewRepository hotelReviewRepository;
    @Autowired
    private HotelImageRepository hotelImageRepository;
    @Autowired
    private HotelRatingRepository hotelRatingRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Transactional
    @Override
    public Hotel addHotel(HotelRequest hotelRequest) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelRequest.getHotelName());
        hotel.setHotelDescription(hotelRequest.getHotelDescription());
        hotel.setHotelClass(hotelRequest.getHotelClass());
        hotel.setBreakFastIncluded(hotelRequest.getBreakFastIncluded());
        hotel.setMinPrice(hotelRequest.getMinPrice());
        hotel.setMaxPrice(hotelRequest.getMaxPrice());
        hotel.setHotelCity(hotelRequest.getHotelCity());
        hotel.setHotelAddress(hotelRequest.getHotelAddress());
        hotel.setPostedAt(new Date());
        Profile currentUser = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        currentUser.getHotelList().add(hotel);
        hotel.setProfile(currentUser);
        this.profileRepository.save(currentUser);
        return hotel;
    }

    @Override
    public Hotel editHotel(Integer hotelId, HotelRequest hotelRequest) throws ResourceNotFoundException {
        Hotel hotel = this.getHotel(hotelId);
        hotel.setHotelName(hotelRequest.getHotelName());
        hotel.setHotelDescription(hotelRequest.getHotelDescription());
        hotel.setHotelClass(hotelRequest.getHotelClass());
        hotel.setBreakFastIncluded(hotelRequest.getBreakFastIncluded());
        hotel.setMinPrice(hotelRequest.getMinPrice());
        hotel.setMaxPrice(hotelRequest.getMaxPrice());
        hotel.setHotelCity(hotelRequest.getHotelCity());
        hotel.setHotelAddress(hotelRequest.getHotelAddress());
        Profile currentUser = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        currentUser.getHotelList().add(hotel);
        hotel.setProfile(currentUser);
        this.profileRepository.save(currentUser);
        return hotel;
    }

    @Override
    public String removeHotel(Integer hotelId) throws ResourceNotFoundException {
        Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("hotel","does not found!"));
        this.hotelRepository.deleteById(hotelId);
        return "deleted Successfully!";
    }

    @Override
    public Hotel getHotel(Integer hotelId) throws ResourceNotFoundException {
        Hotel hotel = this.hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("hotel","does not found!"));
        return hotel;
    }

    @Override
    public List<Hotel> getAllHotel() {
        return this.hotelRepository.findAll();
    }

}
