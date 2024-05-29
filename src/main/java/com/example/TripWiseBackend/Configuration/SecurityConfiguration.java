package com.example.TripWiseBackend.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtFilterAuthentication jwtAuthenticationFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests()
//                .requestMatchers("barber/addBarber").hasRole("ADMIN")
//                .requestMatchers("barber//uploadBarberImage/**").hasRole("ADMIN")
                .requestMatchers("hotel/getAllHotel").permitAll()
                .requestMatchers("place/getAllPlace").permitAll()
                .requestMatchers("restaurant/getAllRestaurant").permitAll()

                .requestMatchers("hotel/getHotel/**").permitAll()
                .requestMatchers("place/getPlace/**").permitAll()
                .requestMatchers("restaurant/getRestaurant/**").permitAll()

                .requestMatchers("hotel/getAllHotelReviewsByHotelId/**").permitAll()
                .requestMatchers("place/getAllPlaceReviewsByPlaceId/**").permitAll()
                .requestMatchers("restaurant/getAllRestaurantReviewsByRestaurantId/**").permitAll()

                .requestMatchers("hotel/getAllHotelByCity/**").permitAll()
                .requestMatchers("place/getAllPlaceByCity/**").permitAll()
                .requestMatchers("restaurant/getAllRestaurantByCity/**").permitAll()


                .requestMatchers("auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(this.authenticationProvider);
        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}




