package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Event;

import java.util.ArrayList;
import java.util.List;

public class EventService {

    public List<Event> listOfEvents = new ArrayList<>();

    public EventService(){
        listOfEvents = new ArrayList();
    }

    public void addEvent(Event event){
        listOfEvents.add(event);
    }

    public List<Event> getListOfEvents() {
        return listOfEvents;
    }

    public String getEvent(int id) {
        return listOfEvents.get(id).toString();
    }


}
