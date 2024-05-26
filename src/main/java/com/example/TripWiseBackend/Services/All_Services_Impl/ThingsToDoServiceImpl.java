package com.example.TripWiseBackend.Services.All_Services_Impl;

import com.example.TripWiseBackend.Entities.Profile.Profile;
import com.example.TripWiseBackend.Entities.ThingsToDo.ThingsToDo;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Repositories.ThingsToDo.ThingsToDoRepository;
import com.example.TripWiseBackend.Services.All_Services.ThingsToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThingsToDoServiceImpl implements ThingsToDoService {

    @Autowired
    private ThingsToDoRepository thingsToDoRepository;

    @Autowired
    private ProfileRepository profileRepository;


    @Override
    public ThingsToDo addThingsToDo(ThingsToDo thingsToDo) {
        thingsToDo.setCompleted(false);
        Profile profile = (Profile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        profile.addThingsToDo(thingsToDo);
        this.profileRepository.save(profile);
        return this.thingsToDoRepository.save(thingsToDo);
    }

    @Override
    public ThingsToDo editThingsToDo(ThingsToDo thingsToDo, Integer thingsToDoId) throws ResourceNotFoundException {
        ThingsToDo thingsToDo1 = this.thingsToDoRepository.findById(thingsToDoId).orElseThrow(() -> new ResourceNotFoundException("ThingsToDo","does not found!"));
        thingsToDo1.setTitle(thingsToDo.getTitle());
        thingsToDo1.setDescription(thingsToDo.getDescription());
        thingsToDo1.setCompleted(thingsToDo.getCompleted());
        return this.thingsToDoRepository.save(thingsToDo1);
    }

    @Override
    public String deleteThingsToDo(Integer thingsToDoId) throws ResourceNotFoundException {
        ThingsToDo thingsToDo1 = this.thingsToDoRepository.findById(thingsToDoId).orElseThrow(() -> new ResourceNotFoundException("ThingsToDo","does not found!"));
        this.thingsToDoRepository.deleteById(thingsToDoId);
        return "Successfully deleted!";
    }

    @Override
    public ThingsToDo getOne(Integer thingsToDoId) throws ResourceNotFoundException {
        ThingsToDo thingsToDo1 = this.thingsToDoRepository.findById(thingsToDoId).orElseThrow(() -> new ResourceNotFoundException("ThingsToDo","does not found!"));
        return thingsToDo1;
    }

    @Override
    public List<ThingsToDo> getAll() {
        return this.thingsToDoRepository.findAll();
    }
}
