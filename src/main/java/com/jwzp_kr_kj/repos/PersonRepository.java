package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.models.DayOfTheWeek;
import com.jwzp_kr_kj.models.records.EventRecord;
import com.jwzp_kr_kj.models.records.PersonRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<PersonRecord, Integer>, PagingAndSortingRepository<PersonRecord, Integer> {

    @Query(value = "select e from persons e where e.instanceEventId = ?1")
    List<PersonRecord> findByEventId(int instanceEventId);

    Page<PersonRecord> findAll(Pageable pageable);

    @Query(value = "select p from persons p where p.instanceEventId = ?1")
    List<PersonRecord> findByInstanceEventId(int instanceEventId);
}