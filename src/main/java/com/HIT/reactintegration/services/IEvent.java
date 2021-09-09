package com.HIT.reactintegration.services;

import com.HIT.reactintegration.dtos.EventDTO;

import java.util.Map;

public interface IEvent {
    Map createEvent(EventDTO eventContent);

    Map getAllEvents();
}
