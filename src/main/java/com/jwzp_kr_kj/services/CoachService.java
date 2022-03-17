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

    public Optional<Coach> findCoach(int id) {
        return coachRepository.findById(id);
    }

    public ResponseEntity<Object> deleteCoach(int id) {
        Optional<Coach> coach = findCoach(id);
        if (coach.isPresent()) {
            coachRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Optional<Coach> getCoach(int id) {
        return coachRepository.findById(id);
    }
}
