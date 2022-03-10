package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Club;

import java.util.ArrayList;
import java.util.List;

public class ClubService {

    private final List<Club> listOfClubs;

    public ClubService(){
        listOfClubs = new ArrayList();
    }

    public void addClub(Club club) {
        listOfClubs.add(club);
    }

    public List<Club> getAllClubs() {
        return listOfClubs;
    }

    public String getClub(int id) {
        return listOfClubs.get(id).toString();
    }
}
