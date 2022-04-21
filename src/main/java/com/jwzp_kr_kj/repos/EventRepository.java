package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.models.records.EventRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventRecord, Integer> {

    @Query(value = "select e from events e where e.coachId = ?1")
    List<EventRecord> findByCoachId(int coachId);

    @Query(value = "select e from events e where e.clubId = ?1")
    List<EventRecord> findByClubId(int clubId);

}
