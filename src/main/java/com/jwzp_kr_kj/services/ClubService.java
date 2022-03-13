package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Club;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public Club getClub(int id) {
        return listOfClubs.get(id);
    }
}
