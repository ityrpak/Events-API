package com.HIT.reactintegration.services;

import com.HIT.reactintegration.dtos.eventsdto.EventDTO;
import com.HIT.reactintegration.dtos.eventsdto.EventWDateDTO;
import com.HIT.reactintegration.entities.Event;
import com.HIT.reactintegration.exceptions.EntityNotFoundException;
import com.HIT.reactintegration.exceptions.NoRecordsFoundException;
import com.HIT.reactintegration.exceptions.EventNotSavedException;
import com.HIT.reactintegration.repositories.EventRepository;
import com.HIT.reactintegration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EventImpl implements IEvent {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Map createEvent(EventDTO eventDTO){
        Map responseMsg;
        try{
            Event event = new Event(
                    eventDTO.getEventTitle(),
                    eventDTO.getEventDescription(),
                    userRepository.findByNickname(eventDTO.getEventUserNickname()).orElseThrow());
            eventRepository.save(event);
            responseMsg = getSuccessResponse(Map.of("Success message", "Event saved"));
        } catch (DataIntegrityViolationException ex){
            throw new EventNotSavedException(ex.getCause().getMessage());
        } catch (NoSuchElementException ex){
            throw new EntityNotFoundException(eventDTO.getEventUserNickname());
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
                            .eventTitle(event.getEventTitle())
                            .eventImageUrl(event.getEventImageURL())
                            .createdAt(event.getCreatedAt())
                            .eventId(event.getId())
                            .build());
        });
        responseMsg = getSuccessResponse(allRecords);
        return responseMsg;
    }

    private Map<String, Map> getSuccessResponse(Map map) {
        return Map.of("data", map);
    }
}
