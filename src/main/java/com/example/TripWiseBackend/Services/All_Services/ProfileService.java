package com.example.TripWiseBackend.Services.All_Services;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.RegisterRequest;
import com.example.TripWiseBackend.Models.Request.UpdateProfileRequest;

import java.util.List;

public interface ProfileService {
    public Profile insert(Profile profile);
    public Profile update(Integer profileId,Profile profile) throws ResourceNotFoundException;
    public Profile getOneById(Integer profileId) throws ResourceNotFoundException;
    public List<Profile> getAll();
    public Boolean deleteById(Integer profileId) throws ResourceNotFoundException;
    public Profile register(RegisterRequest registerRequest);

    public String generateCommonLangPassword();
    public Profile getOneByUsername(String username) throws ResourceNotFoundException;

    public String resetPassword(String username) throws ResourceNotFoundException;
    public Profile update(UpdateProfileRequest updateProfileRequest, Integer profileId) throws ResourceNotFoundException;
}
