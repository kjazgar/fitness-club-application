package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.models.records.ClubRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<ClubRecord, Integer> {
    @Query(value = "select c from clubs c where c.id = ?1")
    Optional<ClubRecord> findById(int id);
}
