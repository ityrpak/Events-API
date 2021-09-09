package com.HIT.reactintegration.services;

import com.HIT.reactintegration.dtos.EventDTO;
import com.HIT.reactintegration.dtos.EventWDateDTO;
import com.HIT.reactintegration.entities.Event;
import com.HIT.reactintegration.exceptions.NoRecordsFoundException;
import com.HIT.reactintegration.exceptions.EventNotSavedException;
import com.HIT.reactintegration.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EventImpl implements IEvent {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Map createEvent(EventDTO eventContent){
        Map responseMsg;
        try{
            Event event = new Event(eventContent.getEventContent());
            eventRepository.save(event);
            responseMsg = getSuccessResponse(Map.of("Success message", "Event saved"));
        } catch (DataIntegrityViolationException ex){
            throw new EventNotSavedException(ex.getCause().getMessage());
        }
        return responseMsg;
    }

    @Override
    public Map getAllEvents() {
        Map responseMsg;
        List<Event> all = eventRepository.findAll();
        if (all.isEmpty()) throw new NoRecordsFoundException("Records");

        AtomicInteger recordNumber = new AtomicInteger(1);
        HashMap<Integer, EventWDateDTO> allRecords = new HashMap<>();
        all.stream().forEach(event -> {
            allRecords.put(
                    recordNumber.getAndIncrement(),
                    EventWDateDTO
                            .builder()
                            .eventContent(event.getEventTitle())
                            .createdAt(event.getCreatedAt())
                            .build());
        });
        responseMsg = getSuccessResponse(allRecords);
        return responseMsg;
    }

    private Map<String, Map> getSuccessResponse(Map map) {
        return Map.of("data", map);
    }
}
