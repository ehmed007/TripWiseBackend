package com.example.TripWiseBackend.Services.All_Services_Impl;

import com.example.TripWiseBackend.Entities.Place.Place;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.PlaceRequest;
import com.example.TripWiseBackend.Repositories.Place.PlaceRepository;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Services.All_Services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public Place addPlace(PlaceRequest placeRequest) {
        Place place = new Place();
        place.setPlaceName(placeRequest.getPlaceName());
        place.setPlaceDescription(placeRequest.getPlaceDescription());
        place.setPlaceAddress(placeRequest.getPlaceAddress());
        place.setPlaceCity(placeRequest.getPlaceCity());
        place.setPostedAt(new Date());

        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        profile.addPlace(place);
        place.setProfile(profile);

        this.profileRepository.save(profile);
        this.placeRepository.save(place);
        return place;
    }

    @Override
    public String deletePlace(Integer placeId) throws ResourceNotFoundException {
        Place place = this.placeRepository.findById(placeId).orElseThrow(() -> new ResourceNotFoundException("Place","does not found!"));
        this.placeRepository.deleteById(placeId);
        return "Place deleted Successfully!";
    }

    @Override
    public Place getPlace(Integer placeId) throws ResourceNotFoundException {
        Place place = this.placeRepository.findById(placeId).orElseThrow(() -> new ResourceNotFoundException("Place","does not found!"));
        return place;
    }

    @Override
    public List<Place> getALlPlace() {
        return this.placeRepository.findAll();
    }
}
