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

    public List<Coach> getAllCoaches() {
        return listOfCoaches;
    }

    public String getCoach(int id) {
        return listOfCoaches.get(id).toString();
    }
}
