package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Club;

import java.util.ArrayList;
import java.util.List;

public class ClubService {

    private static final List<Club> listOfClubs = new ArrayList<>();

    public void addClub(Club club){
        listOfClubs.add(club);
    }
}
