package com.example.TripWiseBackend.Services.All_Services;

import com.example.TripWiseBackend.Entities.ThingsToDo.ThingsToDo;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;

import java.util.List;

public interface ThingsToDoService {
    public ThingsToDo addThingsToDo(ThingsToDo thingsToDo);
    public ThingsToDo editThingsToDo(ThingsToDo thingsToDo, Integer thingsToDoId) throws ResourceNotFoundException;
    public String deleteThingsToDo(Integer thingsToDoId) throws ResourceNotFoundException;
    public ThingsToDo getOne(Integer thingsToDoId) throws ResourceNotFoundException;
    public List<ThingsToDo> getAll();
}
