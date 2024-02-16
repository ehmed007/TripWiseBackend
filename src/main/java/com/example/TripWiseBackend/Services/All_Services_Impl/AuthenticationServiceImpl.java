package com.example.TripWiseBackend.Services.All_Services_Impl;

import com.example.TripWiseBackend.Entities.Enums.Role;
import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Models.Request.RegisterRequest;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Services.All_Services.AuthenticationService;
import com.example.TripWiseBackend.Services.All_Services.JwtService;
import com.example.TripWiseBackend.Services.All_Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JwtService jwtService;

    @Override
    public Map doLogin(String username, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Profile profile = this.profileRepository.findByUsername(username).get();
        String token = this.jwtService.generateToken(profile);
        Map response = new HashMap<>();
        response.put("token",token);
        response.put("message","login successfully!");
        return response;
    }

    @Override
    public Map doRegister(RegisterRequest registerRequest) {
        this.profileService.register(registerRequest);
        Map response = new HashMap<>();
        response.put("message","user registered successfully");
        return response;
    }


}
