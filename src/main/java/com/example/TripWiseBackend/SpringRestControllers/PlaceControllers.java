package com.example.TripWiseBackend.SpringRestControllers;

import com.example.TripWiseBackend.Entities.Place.Place;
import com.example.TripWiseBackend.Entities.Place.PlaceRating;
import com.example.TripWiseBackend.Entities.Place.PlaceReview;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.PlaceRequest;
import com.example.TripWiseBackend.Repositories.Place.PlaceRepository;
import com.example.TripWiseBackend.Repositories.Place.PlaceReviewRepository;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Services.All_Services.ImageService;
import com.example.TripWiseBackend.Services.All_Services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/place")
public class PlaceControllers {

    @Autowired
    private PlaceService placeService;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PlaceReviewRepository placeReviewRepository;

    @PostMapping("/addPlace")
    public Place addPlace(@RequestBody PlaceRequest placeRequest) {
        return this.placeService.addPlace(placeRequest);
    }

    @DeleteMapping("/deletePlace/{placeId}")
    public String deletePlace(@PathVariable Integer placeId) throws ResourceNotFoundException {
        return this.placeService.deletePlace(placeId);
    }

    @GetMapping("/getPlace/{placeId}")
    public Place getPlace(@PathVariable Integer placeId) throws ResourceNotFoundException {
        return this.placeService.getPlace(placeId);
    }

    @GetMapping("/getAllPlace")
    public List<Place> getAllPlace() {
        return this.placeService.getALlPlace();
    }

    @PostMapping("/uploadPlaceImage/{placeId}")
    public ResponseEntity<Object> uploadPlaceImage(@RequestParam("image") MultipartFile image, @PathVariable int placeId) throws Exception {
        Map response = new HashMap<>();
        String url = (String) this.imageService.addPlaceImage(image,placeId).get("url");
        response.put("imageUrl",url);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/addPlaceReview/{placeId}")
    public String addPlaceReview(@PathVariable int placeId, @RequestBody PlaceReview placeReview) throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Place place = this.placeService.getPlace(placeId);
        placeReview.setProfile(profile);
        placeReview.setPostedAt(new Date());
        placeReview.setPlace(place);
        place.addPlaceReview(placeReview);
        profile.addPlaceReview(placeReview);
        this.placeRepository.save(place);
        this.profileRepository.save(profile);
        return "Review Added Successfully!";
    }

    @PostMapping("/addPlaceRating/{placeId}")
    public String addPlaceRating(@PathVariable int placeId, @RequestBody PlaceRating placeRating) throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Place place = this.placeService.getPlace(placeId);
        place.addPlaceRating(placeRating);
        this.placeRepository.save(place);
        return "Rating Added Successfully!";
    }

    @GetMapping("/getAllPlaceByProfile")
    public List<Place> getAllPlaceByProfile() throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.placeRepository.getPlaceByProfileId(profile.getId());
    }

    @GetMapping("/getAllPlaceReviewsByPlaceId/{placeId}")
    public List<PlaceReview> getAllPlaceReviewsByPlace(@PathVariable Integer placeId) throws ResourceNotFoundException {
        Place place = this.placeService.getPlace(placeId);
        return this.placeReviewRepository.getPlaceReviewsByPlaceId(place.getId());
    }

}
