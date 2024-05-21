package com.example.TripWiseBackend.Services.All_Services;


import com.example.TripWiseBackend.Entities.Place.Place;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.PlaceRequest;

import java.util.List;

public interface PlaceService {
    public Place addPlace(PlaceRequest placeRequest);
    public String deletePlace(Integer placeId) throws ResourceNotFoundException;
    public Place getPlace(Integer placeId) throws ResourceNotFoundException;
    public List<Place> getALlPlace();
}
