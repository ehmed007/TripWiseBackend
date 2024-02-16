package com.example.TripWiseBackend.Services.All_Services;

import com.example.TripWiseBackend.Models.Request.RegisterRequest;

import java.util.Map;

public interface AuthenticationService {
    public Map doLogin(String username, String password);
    public Map doRegister(RegisterRequest registerRequest);
}
