package com.HIT.reactintegration.services;

import com.HIT.reactintegration.dtos.RecordDTO;
import com.HIT.reactintegration.entities.Record;
import com.HIT.reactintegration.exceptions.NoRecordsFoundException;
import com.HIT.reactintegration.exceptions.RecordNotSavedException;
import com.HIT.reactintegration.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    @Override
    public HashMap<Integer, RecordDTO> getAllRecords() {
        List<Record> all = recordRepository.findAll();
        if (all.isEmpty()) throw new NoRecordsFoundException("Records");

        AtomicInteger recordNumber = new AtomicInteger(1);
        HashMap<Integer, RecordDTO> allRecords = new HashMap<>();
        all.stream().forEach(record -> {
            allRecords.put(
                    recordNumber.getAndIncrement(),
                    RecordDTO
                            .builder()
                            .recordContent(record.getRecordContent())
                            .createdAt(record.getCreatedAt())
                            .build());
        });
        return allRecords;
    }
}
