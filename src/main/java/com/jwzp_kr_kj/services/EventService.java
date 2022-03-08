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

    public String getEventById(int id) {
        return listOfEvents.get(id).toString();
    }

    public List<Event> getEventsByClubId(int clubId){

        List<Event> eventsFromClubList = new ArrayList<>();
        for(Event event : listOfEvents){
            if(event.getClubId() == clubId){
                eventsFromClubList.add(event);
            }
        }

        return eventsFromClubList;
    }

    public List<Event> getEventsByCoachId(int coachId){

        List<Event> eventsForCoachList = new ArrayList<>();
        for(Event event : listOfEvents){
            if(event.getCoachId() == coachId){
                eventsForCoachList.add(event);
            }
        }

        return eventsForCoachList;
    }


}
