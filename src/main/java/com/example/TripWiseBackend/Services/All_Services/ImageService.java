package com.example.TripWiseBackend.Services.All_Services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImageService {
    public Map addProfileImage(MultipartFile image, Integer profileId) throws Exception;

    public Map addHotelImage(MultipartFile image, Integer profileId) throws Exception;

    public Map addRestaurantImage(MultipartFile image, Integer profileId) throws Exception;

    public Map addPlaceImage(MultipartFile image, Integer serviceId) throws Exception;

}
