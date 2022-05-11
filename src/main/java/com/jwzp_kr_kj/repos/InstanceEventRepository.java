package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.models.records.InstanceEventRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InstanceEventRepository extends JpaRepository<InstanceEventRecord, Integer> {

    @Query("DELETE FROM instanceEvents e WHERE e.date < ?1")
    List<EventRecord> archiveAllOlderThanMonth(LocalDate date);

    @Query(value = "insert into instanceEvents e values (?1, ?2)", nativeQuery = true)
    void addData(int eventId, String time);

}
