package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.core.Club;
import com.jwzp_kr_kj.core.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClubRepository extends JpaRepository<Club, Integer> {
    @Query(value = "select c from clubs c where c.id = ?1")
    Optional<Club> findById(int id);
}
