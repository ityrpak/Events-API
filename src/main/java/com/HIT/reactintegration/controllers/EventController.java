package com.HIT.reactintegration.controllers;

import com.HIT.reactintegration.dtos.eventsdto.EventDTO;
import com.HIT.reactintegration.services.EventImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Event", description = "Records related endpoints")
public class EventController {

    @Autowired
    private EventImpl eventService;

    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createEvent(@RequestBody @Validated EventDTO eventContent){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.createEvent(eventContent));
    }

    @GetMapping(value = "/events", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllEvents(){
        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAllEvents());
    }

}
