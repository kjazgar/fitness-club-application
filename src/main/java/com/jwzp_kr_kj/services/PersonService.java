package com.jwzp_kr_kj.services;

import com.jwzp_kr_kj.Logs;
import com.jwzp_kr_kj.models.data.EventData;
import com.jwzp_kr_kj.models.records.ClubRecord;
import com.jwzp_kr_kj.models.records.CoachRecord;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.models.records.PersonRecord;
import com.jwzp_kr_kj.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    public PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonRecord> getAllPeople() {
        return personRepository.findAll();
    }

    public List<PersonRecord> getAllPeopleOnEvent(int instanceEventId) {
        return personRepository.findByInstanceEventId(instanceEventId);
    }

    public ResponseEntity<Object> addPerson(PersonRecord personRecord){
        personRepository.save(personRecord);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
};