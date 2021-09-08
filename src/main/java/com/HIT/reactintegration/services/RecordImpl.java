package com.HIT.reactintegration.services;

import com.HIT.reactintegration.dtos.RecordDTO;
import com.HIT.reactintegration.dtos.RecordWDateDTO;
import com.HIT.reactintegration.entities.Record;
import com.HIT.reactintegration.exceptions.NoRecordsFoundException;
import com.HIT.reactintegration.exceptions.RecordNotSavedException;
import com.HIT.reactintegration.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RecordImpl implements IRecord{

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Map createRecord(RecordDTO recordContent){
        Map responseMsg;
        try{
            Record record = new Record(recordContent.getRecordContent());
            recordRepository.save(record);
            responseMsg = getSuccessResponse(Map.of("Success message", "Record saved"));
        } catch (DataIntegrityViolationException ex){
            throw new RecordNotSavedException(ex.getCause().getMessage());
        }
        return responseMsg;
    }

    @Override
    public Map getAllRecords() {
        Map responseMsg;
        List<Record> all = recordRepository.findAll();
        if (all.isEmpty()) throw new NoRecordsFoundException("Records");

        AtomicInteger recordNumber = new AtomicInteger(1);
        HashMap<Integer, RecordWDateDTO> allRecords = new HashMap<>();
        all.stream().forEach(record -> {
            allRecords.put(
                    recordNumber.getAndIncrement(),
                    RecordWDateDTO
                            .builder()
                            .recordContent(record.getRecordContent())
                            .createdAt(record.getCreatedAt())
                            .build());
        });
        responseMsg = getSuccessResponse(allRecords);
        return responseMsg;
    }

    private Map<String, Map> getSuccessResponse(Map map) {
        return Map.of("data", map);
    }
}
