package com.example.TripWiseBackend.Entities.Profile;

import com.example.TripWiseBackend.Entities.Enums.Role;
import com.example.TripWiseBackend.Entities.Hotel.Hotel;
import com.example.TripWiseBackend.Entities.Hotel.HotelRating;
import com.example.TripWiseBackend.Entities.Hotel.HotelReview;
import com.example.TripWiseBackend.Entities.Place.Place;
import com.example.TripWiseBackend.Entities.Place.PlaceReview;
import com.example.TripWiseBackend.Entities.Restaurant.Restaurant;
import com.example.TripWiseBackend.Entities.Restaurant.RestaurantReview;
import com.example.TripWiseBackend.Entities.ThingsToDo.ThingsToDo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Entity
public class Profile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;
    private String firstName;
    private String lastName;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String gender;
    private String imgUrl;
    private String imgPublicId;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","profile", "hotelReviewList","hotelImageList","hotelRatingList"})
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "profile", fetch = FetchType.EAGER)
    private List<Hotel> hotelList;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<HotelReview> hotelReviewList;

    // For Restaurant
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","profile","restaurantReviewList","restaurantImageList","dishList","restaurantRatingList"})
    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Restaurant> restaurantList;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","profile","restaurant",})
    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<RestaurantReview> restaurantReviewList;

    // For Place
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","profile","placeReviewList","placeImageList","placeRatingList"})
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "profile", fetch = FetchType.EAGER)
    private List<Place> placeList;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler","profile","place"})
    @OneToMany(mappedBy = "profile",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<PlaceReview> placeReviewList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private List<ThingsToDo> thingsToDoList;


    public Profile() {
        super();
    }

    public Profile(Integer id, String username, String firstName, String lastName, String password, Role role, String gender, String imgUrl, String imgPublicId, List<Hotel> hotelList, List<HotelReview> hotelReviewList, List<Restaurant> restaurantList, List<RestaurantReview> restaurantReviewList, List<Place> placeList, List<PlaceReview> placeReviewList, List<ThingsToDo> thingsToDoList) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.imgUrl = imgUrl;
        this.imgPublicId = imgPublicId;
        this.hotelList = hotelList;
        this.hotelReviewList = hotelReviewList;
        this.restaurantList = restaurantList;
        this.restaurantReviewList = restaurantReviewList;
        this.placeList = placeList;
        this.placeReviewList = placeReviewList;
        this.thingsToDoList = thingsToDoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    @JsonIgnore
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @JsonIgnore
    public String getImgPublicId() {
        return imgPublicId;
    }

    public void setImgPublicId(String imgPublicId) {
        this.imgPublicId = imgPublicId;
    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public List<HotelReview> getHotelReviewList() {
        return hotelReviewList;
    }

    public void setHotelReviewList(List<HotelReview> hotelReviewList) {
        this.hotelReviewList = hotelReviewList;
    }

    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public List<RestaurantReview> getRestaurantReviewList() {
        return restaurantReviewList;
    }

    public void setRestaurantReviewList(List<RestaurantReview> restaurantReviewList) {
        this.restaurantReviewList = restaurantReviewList;
    }

    public List<Place> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<Place> placeList) {
        this.placeList = placeList;
    }

    public List<PlaceReview> getPlaceReviewList() {
        return placeReviewList;
    }

    public void setPlaceReviewList(List<PlaceReview> placeReviewList) {
        this.placeReviewList = placeReviewList;
    }


    public List<ThingsToDo> getThingsToDoList() {
        return thingsToDoList;
    }

    public void setThingsToDoList(List<ThingsToDo> thingsToDoList) {
        this.thingsToDoList = thingsToDoList;
    }

    // For Hotel
    public void addHotelReview(HotelReview hotelReview) {
        if (this.hotelReviewList == null) {
            this.hotelReviewList = new ArrayList<>();
            this.hotelReviewList.add(hotelReview);
        }
        this.hotelReviewList.add(hotelReview);
    }

    public void addHotel(Hotel hotel) {
        if (this.hotelList == null) {
            this.hotelList = new ArrayList<>();
            this.hotelList.add(hotel);
        }
        this.hotelList.add(hotel);
    }

    // For Restaurant

    public void addRestaurant(Restaurant restaurant) {
        if (this.restaurantList == null) {
            this.restaurantList = new ArrayList<>();
            this.restaurantList.add(restaurant);
        }
        this.restaurantList.add(restaurant);
    }

    public void addRestaurantReview(RestaurantReview restaurantReview) {
        if (this.restaurantReviewList == null) {
            this.restaurantReviewList = new ArrayList<>();
            this.restaurantReviewList.add(restaurantReview);
        }
        this.restaurantReviewList.add(restaurantReview);
    }


    // For Place

    public void addPlace(Place place) {
        if (this.placeList == null) {
            this.placeList = new ArrayList<>();
            this.placeList.add(place);
        }
        this.placeList.add(place);
    }

    public void addPlaceReview(PlaceReview placeReview) {
        if (this.placeReviewList == null) {
            this.placeReviewList = new ArrayList<>();
            this.placeReviewList.add(placeReview);
        }
        this.placeReviewList.add(placeReview);
    }

    public void addThingsToDo(ThingsToDo thingsToDo) {
        if (this.thingsToDoList == null) {
            this.thingsToDoList = new ArrayList<>();
            this.thingsToDoList.add(thingsToDo);
        }
        this.thingsToDoList.add(thingsToDo);
    }

}
