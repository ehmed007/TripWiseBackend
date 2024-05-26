package com.example.TripWiseBackend.SpringRestControllers;


import com.example.TripWiseBackend.Entities.ThingsToDo.ThingsToDo;
import com.example.TripWiseBackend.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.example.TripWiseBackend.Repositories.Profile.ProfileRepository;
import com.example.TripWiseBackend.Services.All_Services.ThingsToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/things")
public class ThingsToDoControllers {

    @Autowired
    private ThingsToDoService thingsToDoService;

    @PostMapping("/addThings")
    public ThingsToDo addThings(@RequestBody ThingsToDo thingsToDo) {
        return this.thingsToDoService.addThingsToDo(thingsToDo);
    }

    @PutMapping("/editThings/{thingsToDoId}")
    public ThingsToDo editThings(@RequestBody ThingsToDo thingsToDo, @PathVariable Integer thingsToDoId) throws ResourceNotFoundException {
        return this.thingsToDoService.editThingsToDo(thingsToDo, thingsToDoId);
    }

    @DeleteMapping("/deleteThings/{thingsToDoId}")
    public String deleteThings(@PathVariable Integer thingsToDoId) throws ResourceNotFoundException {
        return this.thingsToDoService.deleteThingsToDo(thingsToDoId);
    }

    @GetMapping("/getOne/{thingsToDoId}")
    public ThingsToDo getOne(@PathVariable Integer thingsToDoId) throws ResourceNotFoundException {
        return this.thingsToDoService.getOne(thingsToDoId);
    }

    @GetMapping("/getAll")
    public List<ThingsToDo> getAll() {
        return this.thingsToDoService.getAll();
    }

}
