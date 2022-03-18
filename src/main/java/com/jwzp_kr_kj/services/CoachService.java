package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.core.Coach;
import com.jwzp_kr_kj.repos.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public void addCoach(Coach coach) {
        coachRepository.save(coach);
    }

    public ResponseEntity<Object> deleteCoach(int id) {
        Optional<Coach> coach = getCoach(id);
        if (coach.isPresent()) {
            coachRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Object> updateCoach(int id, Coach newCoach){
        Optional<Object> updatedCoach = coachRepository.findById(id).map(coach -> {
            coach.setFirstName(newCoach.getFirstName());
            coach.setLastName(newCoach.getLastName());
            coach.setYearOfBirth(newCoach.getYearOfBirth());
            return coachRepository.save(coach);
        });
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Optional<Coach> getCoach(int id) {
        return coachRepository.findById(id);
    }
}
