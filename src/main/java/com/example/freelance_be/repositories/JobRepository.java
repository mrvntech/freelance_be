package com.example.freelance_be.repositories;

import com.example.freelance_be.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
//    @Query(value = "select j from job j " +
//            "inner join j.category c " +
//            "where " +
//            "( :category_id is null or c.id = :category_id) " +
//            "and ( :category_name is null or c.name = :category_name)" +
//            "and ( :client_id is null or j.customer.id = :client_id) " +
//            "and ( :freelancer_id is null or j.freelancer.id = :freelancer_id)"
//    )
//    List<Job> findAll(
//            @Param("category_id") Long categoryId,
//            @Param("client_id") Long clientId,
//            @Param("freelancer_id") Long freelancerId,
//            @Param("category_name") String categoryName
//    );
//    @Query(value = "select j, c from job j " +
//            "inner join j.category c " +
//            "where j.id = :id and c.name = :category_name")
//    Optional<Job> findJob(@Param("id") Long id, @Param("category_name") String categoryName );
}
