package com.jwzp_kr_kj.repos;

import com.jwzp_kr_kj.core.Coach;
import com.jwzp_kr_kj.core.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer> {

    @Query(value = "select c from coaches c where c.id = ?1")
    Optional<Coach> findById(int id);
}
