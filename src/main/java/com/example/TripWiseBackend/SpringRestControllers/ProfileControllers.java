package com.example.TripWiseBackend.SpringRestControllers;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.UpdateProfileRequest;
import com.example.TripWiseBackend.Services.All_Services.ImageService;
import com.example.TripWiseBackend.Services.All_Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileControllers {
    @Autowired
    private ImageService imageService;

    @Autowired
    private ProfileService profileService;

    @PostMapping("/uploadProfileImage")
    public ResponseEntity<Object> uploadProfileImage(@RequestParam("image") MultipartFile image) throws Exception {
        Profile obj = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.imageService.addProfileImage(image, obj.getId());
        Map response = new HashMap<>();
        String url = (String) this.imageService.addProfileImage(image,obj.getId()).get("url");
        response.put("imageUrl",url);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/getProfile")
    public ResponseEntity<Profile> getProfile() {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(profile, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<Profile> updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest) throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Profile profile1 = this.profileService.update(updateProfileRequest, profile.getId());
        return new ResponseEntity<>(profile1, HttpStatusCode.valueOf(200));
    }


}
