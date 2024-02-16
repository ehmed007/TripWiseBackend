package com.example.TripWiseBackend.SpringRestControllers;

import com.cloudinary.Cloudinary;
import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import com.example.TripWiseBackend.Entities.Hotel.HotelImage;
import com.example.TripWiseBackend.Entities.Hotel.HotelReview;
import com.example.TripWiseBackend.Entities.Place.Place;
import com.example.TripWiseBackend.Entities.Place.PlaceReview;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Entities.Restaurant.Restaurant;
import com.example.TripWiseBackend.Entities.Restaurant.RestaurantReview;
import com.example.TripWiseBackend.Repositories.Hotel.HotelRepository;
import com.example.TripWiseBackend.Repositories.Hotel.HotelReviewRepository;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Repositories.Restaurant.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controllers {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelReviewRepository hotelReviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private Cloudinary cloudinary;

    @GetMapping("/")
    public List Home() {
        Profile profile = new Profile();
        profile.setFirstName("ahmed");
        profile.setLastName("rasheed");

        Hotel hotel = new Hotel();
        hotel.setHotelName("ahmeds hotel");
        hotel.setHotelCity("lahore");
        hotel.setProfile(profile);



        HotelReview hotelReview = new HotelReview();
        hotelReview.setReview("this was beautifull");
        hotelReview.setRating(5F);

        hotelReview.setProfile(profile);
        hotelReview.setHotel(hotel);


        hotel.addHotelReview(hotelReview);

        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName("Ahmeds restaurants");
        restaurant.setProfile(profile);

        RestaurantReview restaurantReview = new RestaurantReview();
        restaurantReview.setReview("bhtreen ha yrr");
        restaurantReview.setProfile(profile);
        restaurantReview.setRestaurant(restaurant);

        Place place = new Place();
        place.setPlaceName("Minar e pakistan");
        place.setProfile(profile);

        PlaceReview placeReview = new PlaceReview();
        placeReview.setReview("bht pyaara ha bhai");
        placeReview.setProfile(profile);
        placeReview.setPlace(place);

        profile.addHotel(hotel);
        profile.addHotelReview(hotelReview);
        profile.addRestaurant(restaurant);
        profile.addRestaurantReview(restaurantReview);
        profile.addPlace(place);
        profile.addPlaceReview(placeReview);

        this.profileRepository.save(profile);

//        return this.profileRepository.findAll();
        return this.hotelRepository.findAll();
    }

    @GetMapping("/delete")
    public String delete() {
        this.hotelRepository.deleteById(1);
        return "deleted";
    }


    @PostMapping("/uploadImage")
    public Map uploadImage(@RequestParam("image")MultipartFile image) throws Exception {
        this.cloudinary.api().createFolder("Personal",Map.of());
        Map options = new HashMap<>();
        options.put("folder","Personal");
        Map<?,?> response = cloudinary.uploader().upload(image.getBytes(), options);
        String url = (String) response.get("url");
        String public_id = (String) response.get("public_id");

        Hotel hotel = this.hotelRepository.getReferenceById(1);

        HotelImage hotelImage = new HotelImage();
        hotelImage.setHotel(hotel);
        hotelImage.setImageUrl(url);
        hotelImage.setPublicId(public_id);

        hotel.addHotelImage(hotelImage);

        this.hotelRepository.save(hotel);

        return response;
    }

}
