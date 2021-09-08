package com.HIT.reactintegration.controllers;

import com.HIT.reactintegration.services.RecordImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {

    @Autowired
    private RecordImpl recordService;

    @PostMapping("/record/create")
    ResponseEntity<?> createRecord(){
        return ResponseEntity.status(HttpStatus.OK).body(recordService.createRecord());
    }

}
