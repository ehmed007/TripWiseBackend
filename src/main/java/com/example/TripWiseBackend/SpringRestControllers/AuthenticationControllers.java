package com.example.TripWiseBackend.SpringRestControllers;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Models.Request.EmailRequest;
import com.example.TripWiseBackend.Models.Request.LoginRequest;
import com.example.TripWiseBackend.Models.Request.RegisterRequest;
import com.example.TripWiseBackend.Services.All_Services.AuthenticationService;
import com.example.TripWiseBackend.Services.All_Services.ImageService;
import com.example.TripWiseBackend.Services.All_Services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationControllers {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/say")
    public String sayHello() {
        return "Helo from Server: I am actively running on port 8080";
    }

    @PostMapping("/signup")
    public ResponseEntity<Map> registerUser(@RequestBody RegisterRequest registerRequest) {
        Map response = this.authenticationService.doRegister(registerRequest);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/login")
    public ResponseEntity<Map> loginUser(@RequestBody LoginRequest loginRequest) {
        Map response = this.authenticationService.doLogin(loginRequest.getEmail(), loginRequest.getPassword());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }


    @PostMapping("/forgetPassword")
    public ResponseEntity<Object> forgetPassword(@RequestBody EmailRequest emailRequest) throws ResourceNotFoundException, ResourceNotFoundException {
        String password = this.profileService.resetPassword(emailRequest.getEmail());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getEmail());
        message.setSubject("Forget Password?");
        message.setText("This is your new password : "+password+"\nPlease change it as soon as possible.");
        this.javaMailSender.send(message);
        Map response = new HashMap<>();
        response.put("message","Password Reset Successfully, Please check your mail.");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/check")
    public String check() {
        return "Hello World!";
    }

}
