package com.HIT.reactintegration.services;

import com.HIT.reactintegration.dtos.RecordDTO;

import java.util.HashMap;

public interface IRecord {
    String createRecord(String recordContent);

    HashMap<Integer, RecordDTO> getAllRecords();
}
