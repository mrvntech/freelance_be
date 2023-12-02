package com.example.freelance_be.repositories;

import com.example.freelance_be.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
//    @Query(value = "select a from application a " +
//            "where a.user.id = :userId"
//    )
    List<Application> findByUserId(Long userId);
    List<Application> findByJobId(Long jobId);

}
