package com.example.TripWiseBackend.Services.All_Services_Impl;

import com.cloudinary.Cloudinary;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Services.All_Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiSecret;

    @Value("${cloudinary.api-secret}")
    private String apiKey;


    @Override
    public Map addProfileImage(MultipartFile image, Integer profileId) throws Exception {
        this.cloudinary.api().createFolder("TripWise/Profile_TripWise",Map.of());
        Map options = new HashMap<>();
        options.put("folder","TripWise/Profile_TripWise");

        Profile profile = this.profileRepository.findById(profileId).orElseThrow(() -> new ResourceNotFoundException("Profile "+profileId,"does not exist!"));
        if (profile.getImgUrl() != null) {
            cloudinary.uploader().destroy(profile.getImgPublicId(),Map.of());
        }
        Map<?,?> response = cloudinary.uploader().upload(image.getBytes(), options);
        System.out.println(response);
        profile.setImgUrl((String) response.get("url"));
        profile.setImgPublicId((String) response.get("public_id"));
        this.profileRepository.save(profile);
        return response;
    }

    @Override
    public Map addHotelImage(MultipartFile image, Integer profileId) throws Exception {
        return null;
    }

    @Override
    public Map addRestaurantImage(MultipartFile image, Integer profileId) throws Exception {
        return null;
    }

    @Override
    public Map addPlaceImage(MultipartFile image, Integer serviceId) throws Exception {
        return null;
    }
}
