package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.core.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value = "select e from events e where e.coachId = ?1")
    List<Event> findByCoachId(int coachId);

    @Query(value = "select e from events e where e.clubId = ?1")
    List<Event> findByClubId(int clubId);

}
