package com.example.TripWiseBackend.Services.All_Services_Impl;

import com.cloudinary.Cloudinary;
import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import com.example.TripWiseBackend.Entities.Hotel.HotelImage;
import com.example.TripWiseBackend.Entities.Place.Place;
import com.example.TripWiseBackend.Entities.Place.PlaceImage;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Entities.Restaurant.Restaurant;
import com.example.TripWiseBackend.Entities.Restaurant.RestaurantImage;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Repositories.Hotel.HotelRepository;
import com.example.TripWiseBackend.Repositories.Place.PlaceRepository;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Repositories.Restaurant.RestaurantRepository;
import com.example.TripWiseBackend.Services.All_Services.HotelService;
import com.example.TripWiseBackend.Services.All_Services.ImageService;
import com.example.TripWiseBackend.Services.All_Services.PlaceService;
import com.example.TripWiseBackend.Services.All_Services.RestaurantService;
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
    private HotelRepository hotelRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private RestaurantService restaurantService;

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
    public Map addHotelImage(MultipartFile image, Integer hotelId) throws Exception {
        this.cloudinary.api().createFolder("TripWise/Hotel_TripWise",Map.of());
        Map options = new HashMap<>();
        options.put("folder","TripWise/Hotel_TripWise");

        Hotel hotel = this.hotelService.getHotel(hotelId);
        Map<?,?> response = cloudinary.uploader().upload(image.getBytes(), options);
        HotelImage hotelImage = new HotelImage();
        hotelImage.setImageUrl((String) response.get("url"));
        hotelImage.setPublicId((String) response.get("public_id"));
        hotel.addHotelImage(hotelImage);
        this.hotelRepository.save(hotel);
        return response;
    }

    @Override
    public Map addRestaurantImage(MultipartFile image, Integer restaurantId) throws Exception {
        this.cloudinary.api().createFolder("TripWise/Restaurant_TripWise",Map.of());
        Map options = new HashMap<>();
        options.put("folder","TripWise/Restaurant_TripWise");

        Restaurant restaurant = this.restaurantService.getRestaurant(restaurantId);
        Map<?,?> response = cloudinary.uploader().upload(image.getBytes(), options);
        RestaurantImage restaurantImage = new RestaurantImage();
        restaurantImage.setImageUrl((String) response.get("url"));
        restaurantImage.setPublicId((String) response.get("public_id"));

        restaurant.addRestaurantImage(restaurantImage);
        this.restaurantRepository.save(restaurant);
        return response;
    }

    @Override
    public Map addPlaceImage(MultipartFile image, Integer placeId) throws Exception {
        this.cloudinary.api().createFolder("TripWise/Place_TripWise",Map.of());
        Map options = new HashMap<>();
        options.put("folder","TripWise/Place_TripWise");

        Place place = this.placeService.getPlace(placeId);
        Map<?,?> response = cloudinary.uploader().upload(image.getBytes(), options);
        PlaceImage placeImage = new PlaceImage();
        placeImage.setImageUrl((String) response.get("url"));
        placeImage.setPublicId((String) response.get("public_id"));
        place.addPlaceImage(placeImage);
        this.placeRepository.save(place);
        return response;
    }
}
