package com.HIT.reactintegration.controllers;

import com.HIT.reactintegration.services.RecordImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordController {

    @Autowired
    private RecordImpl recordService;

    @PostMapping(value = "/record/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createRecord(@RequestPart("record content") String recordContent){
        return ResponseEntity.status(HttpStatus.OK).body(recordService.createRecord(recordContent));
    }

}
