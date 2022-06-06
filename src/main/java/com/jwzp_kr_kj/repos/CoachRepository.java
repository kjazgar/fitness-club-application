package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.models.records.CoachRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<CoachRecord, Integer>, PagingAndSortingRepository<CoachRecord, Integer> {

    @Query(value = "select c from coaches c where c.id = :id")
    Optional<CoachRecord> findById(int id);

    Page<CoachRecord> findAll(Pageable pageable);
}
