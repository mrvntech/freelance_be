package com.example.freelance_be.repositories;

import com.example.freelance_be.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
//    @Query(value = "Select * from job " +
//            "join people on ... where job_id = 1 ",
//            countQuery = "select count(*) from ....",
//            nativeQuery = true
//    )
//    Page<Job> searchAndPageJob(Pageable pageable, String keySearch);
}
