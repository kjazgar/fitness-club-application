package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.models.records.InstanceEventRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InstanceEventRepository extends JpaRepository<InstanceEventRecord, Integer> {

    @Query("select e from instanceEvents e where e.eventId = ?1")
    InstanceEventRecord selectInstanceEventById(int id);

    @Query("DELETE FROM instanceEvents e WHERE e.date < ?1")
    void archiveAllOlderThanMonth(LocalDate date);

    @Query("DELETE FROM instanceEvents e WHERE e.id = ?1")
    void cancelEventById(int id);

    @Modifying
    @Query(value = "insert into instanceEvents (EVENTID, DATE, LIMITOFPARTICIPANTS, OCCUPIED) values (?1, ?2, ?3, 0)", nativeQuery = true)
    void addData(int eventId, String time, int limit);

    @Query("SELECT ie FROM instanceEvents ie LEFT JOIN events e ON ie.eventId = e.id WHERE ie.date = ?1 AND e.clubId = ?2" )
    List<InstanceEventRecord> findEventByDateAndClub(LocalDateTime date, int clubId);

}
