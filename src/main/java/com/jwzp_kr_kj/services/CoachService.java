package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Coach;

import java.util.ArrayList;
import java.util.List;

public class CoachService {

    private final List<Coach> listOfCoaches;

    public CoachService() {
         listOfCoaches = new ArrayList();
    }

    public void addCoach(Coach coach){
        listOfCoaches.add(coach);
    }

    public Coach findCoach(int id){
        for(Coach c : listOfCoaches){
            if (c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public void deleteCoach(int id){

    }

    public List<Coach> getAllCoaches() {
        return listOfCoaches;
    }

    public String getCoach(int id) {
        return listOfCoaches.get(id).toString();
    }
}
