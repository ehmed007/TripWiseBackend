package com.example.TripWiseBackend.SpringRestControllers;

import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import com.example.TripWiseBackend.Entities.Hotel.HotelRating;
import com.example.TripWiseBackend.Entities.Hotel.HotelReview;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.HotelRequest;
import com.example.TripWiseBackend.Repositories.Hotel.HotelRepository;
import com.example.TripWiseBackend.Repositories.Hotel.HotelReviewRepository;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Services.All_Services.HotelService;
import com.example.TripWiseBackend.Services.All_Services.ImageService;
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
@RequestMapping("/hotel")
public class HotelControllers {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private HotelReviewRepository hotelReviewRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @PostMapping("/addHotel")
    public Hotel addHotel(@RequestBody HotelRequest hotelRequest) {
        return this.hotelService.addHotel(hotelRequest);
    }

    @PutMapping("/editHotel/{hotelId}")
    public Hotel editHotel(@RequestBody HotelRequest hotelRequest,@PathVariable int hotelId) throws ResourceNotFoundException {
        return this.hotelService.editHotel(hotelId, hotelRequest);
    }

    @GetMapping("/getHotel/{hotelId}")
    public Hotel getOneHotel(@PathVariable int hotelId) throws ResourceNotFoundException {
        return this.hotelService.getHotel(hotelId);
    }

    @GetMapping("/getAllHotel")
    public List<Hotel> getAllHotel() {
        return this.hotelService.getAllHotel();
    }

    @DeleteMapping("/deleteHotel/{hotelId}")
    public String deleteHotel(@PathVariable Integer hotelId) throws ResourceNotFoundException {
        return this.hotelService.removeHotel(hotelId);
    }

    @PostMapping("/uploadHotelImage/{hotelId}")
    public ResponseEntity<Object> uploadHotelImage(@RequestParam("image") MultipartFile image, @PathVariable int hotelId) throws Exception {
        Map response = new HashMap<>();
        String url = (String) this.imageService.addHotelImage(image,hotelId).get("url");
        response.put("imageUrl",url);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/addHotelReview/{hotelId}")
    public String addHotelReview(@PathVariable int hotelId, @RequestBody HotelReview hotelReview) throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Hotel hotel = this.hotelService.getHotel(hotelId);
        hotelReview.setProfile(profile);
        hotelReview.setPostedAt(new Date());
        hotelReview.setHotel(hotel);
        hotel.addHotelReview(hotelReview);
        profile.addHotelReview(hotelReview);
        this.hotelRepository.save(hotel);
        this.profileRepository.save(profile);
        return "Review Added Successfully!";
    }

    @PostMapping("/addHotelRating/{hotelId}")
    public String addHotelRating(@PathVariable int hotelId, @RequestBody HotelRating hotelRating) throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Hotel hotel = this.hotelService.getHotel(hotelId);
        hotel.addHotelRating(hotelRating);
        this.hotelRepository.save(hotel);
        return "Rating Added Successfully!";
    }
                
    @GetMapping("/getAllHotelByProfile")
    public List<Hotel> getAllHotelByProfile() throws ResourceNotFoundException {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.hotelRepository.getHotelByProfileId(profile.getId());
    }

    @GetMapping("/getAllHotelReviewsByProfile")
    public List<HotelReview> getHotelReviewsByProfile() {
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.hotelReviewRepository.getHotelReviewsByProfileId(profile.getId());
    }

    @GetMapping("/getAllHotelReviewsByHotelId/{hotelId}")
    public List<HotelReview> getHotelReviewsByHotel(@PathVariable Integer hotelId) throws ResourceNotFoundException {
        Hotel hotel = this.hotelService.getHotel(hotelId);
        return this.hotelReviewRepository.getHotelReviewsByHotelId(hotel.getId());
    }


}
