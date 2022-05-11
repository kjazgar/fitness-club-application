package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.records.EventRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventRecord, Integer>, PagingAndSortingRepository<EventRecord, Integer> {

    @Query(value = "select e from events e where e.coachId = ?1")
    List<EventRecord> findByCoachId(int coachId);

    @Query(value = "select e from events e where e.clubId = ?1")
    List<EventRecord> findByClubId(int clubId);

    Page<EventRecord> findAll(Pageable pageable);

    @Query(value = "select e from events e")
    List<EventRecord> getSchedule();

    @Query(value = "select e from events e where e.dayOfTheWeek = ?1")
    List<EventRecord> getScheduleForADay(String day);

}
