package com.example.TripWiseBackend.Services.All_Services_Impl;

import com.example.TripWiseBackend.Entities.Enums.Role;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.RegisterRequest;
import com.example.TripWiseBackend.Models.Request.UpdateProfileRequest;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Services.All_Services.ProfileService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Profile insert(Profile profile) {
        return this.profileRepository.save(profile);
    }

    @Override
    public Profile update(Integer profileId, Profile profile) throws ResourceNotFoundException {
        Profile profile1 = this.profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("User"," does not exist!"));
        profile1.setFirstName(profile.getFirstName());
        profile1.setLastName(profile.getLastName());
        profile1.setGender(profile.getGender());
        profile1.setRole(profile.getRole());
        profile1.setPassword(profile.getPassword());
        profile1.setImgPublicId(profile.getImgPublicId());
        profile1.setImgUrl(profile.getImgUrl());
        profile1.setHotelList(profile.getHotelList());
        profile1.setHotelReviewList(profile.getHotelReviewList());
        profile1.setPlaceList(profile.getPlaceList());
        profile1.setPlaceReviewList(profile.getPlaceReviewList());
        profile1.setRestaurantList(profile.getRestaurantList());
        profile1.setRestaurantReviewList(profile.getRestaurantReviewList());
        return this.profileRepository.save(profile1);
    }

    @Override
    public Profile getOneById(Integer profileId) throws ResourceNotFoundException {
        Profile profile1 = this.profileRepository
                .findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("User"," does not exist!"));
        return profile1;
    }

    @Override
    public List<Profile> getAll() {
        return this.profileRepository.findAll();
    }

    @Override
    public Boolean deleteById(Integer profileId) {
        try {
            Profile profile = this.getOneById(profileId);
            this.profileRepository.deleteById(profileId);
            return true;
        } catch (ResourceNotFoundException e) {
            return false;
        }
    }

    @Override
    public Profile register(RegisterRequest registerRequest) {
        Profile profile = new Profile();
        profile.setFirstName(registerRequest.getFirstname());
        profile.setLastName(registerRequest.getLastname());
        profile.setRole(Role.USER);
        profile.setUsername(registerRequest.getEmail());
        profile.setPassword(registerRequest.getPassword());
        profile.setGender(registerRequest.getGender());
        return this.profileRepository.save(profile);
    }

    @Override
    public String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters
                .concat(lowerCaseLetters)
                .concat(numbers)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars
                .stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }

    @Override
    public Profile getOneByUsername(String username) throws ResourceNotFoundException {
        Profile profile = this.profileRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User"," does not exist!"));
        return profile;
    }



    @Override
    public String resetPassword(String username) throws ResourceNotFoundException {
        Profile profile = this.getOneByUsername(username);
        String password = generateCommonLangPassword();
        profile.setPassword(password);
        this.profileRepository.save(profile);
        return password;
    }

    @Override
    public Profile update(UpdateProfileRequest updateProfileRequest, Integer profileId) throws ResourceNotFoundException {
        Profile profile = this.getOneById(profileId);
        profile.setFirstName(updateProfileRequest.getFirstName());
        profile.setLastName(updateProfileRequest.getLastName());
        profile.setGender(updateProfileRequest.getGender());
        return this.profileRepository.save(profile);
    }
}
