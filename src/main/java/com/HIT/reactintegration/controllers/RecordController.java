package com.HIT.reactintegration.controllers;

import com.HIT.reactintegration.dtos.RecordDTO;
import com.HIT.reactintegration.services.RecordImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecordController {

    @Autowired
    private RecordImpl recordService;

    @PostMapping(value = "/record/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRecord(@RequestBody RecordDTO recordContent){
        return ResponseEntity.status(HttpStatus.OK).body(recordService.createRecord(recordContent));
    }

    @GetMapping(value = "/record/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRecords(){
        return ResponseEntity.status(HttpStatus.OK).body(recordService.getAllRecords());
    }

}
