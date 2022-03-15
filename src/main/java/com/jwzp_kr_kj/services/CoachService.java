package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Coach;
import com.jwzp_kr_kj.repos.CoachRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    public CoachRepository coachRepository;
    @Autowired
    public CoachService(CoachRepository coachRepository) {
         this.coachRepository = coachRepository;
    }

    public void addCoach(Coach coach){
        coachRepository.save(coach);
    }

//    public Coach findCoach(int id){
//        for(Coach c : listOfCoaches){
//            if (c.getId() == id){
//                return c;
//            }
//        }
//        return null;
//    }

    public void deleteCoach(int id){

    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Optional<Coach> getCoach(int id) {
        return coachRepository.findById(id);
    }
}
