package com.HIT.reactintegration.services;

import com.HIT.reactintegration.dtos.eventsdto.EventDTO;
import com.HIT.reactintegration.dtos.responsesdto.SuccessResponseDTO;

import java.util.Map;

public interface IEventService {
    SuccessResponseDTO createEvent(EventDTO eventContent);

    SuccessResponseDTO getAllEvents();
}
