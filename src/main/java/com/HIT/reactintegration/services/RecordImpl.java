package com.HIT.reactintegration.services;

import com.HIT.reactintegration.entities.Record;
import com.HIT.reactintegration.exceptions.RecordNotSavedException;
import com.HIT.reactintegration.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RecordImpl implements IRecord{

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public String createRecord(String recordContent){
        String responseMsg;
        try{
            Record record = new Record(recordContent);
            recordRepository.save(record);
            responseMsg = "Record saved";
        } catch (DataIntegrityViolationException ex){
            throw new RecordNotSavedException(ex.getCause().getMessage());
        }
        return responseMsg;
    }
}
