package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Coach;

import java.util.ArrayList;
import java.util.List;

public class CoachService {

    private static final List<Coach> listOfCoaches = new ArrayList();

    public static void add(Coach coach){
        listOfCoaches.add(coach);
    }
}
