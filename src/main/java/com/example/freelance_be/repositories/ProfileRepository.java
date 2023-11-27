package com.example.freelance_be.repositories;

import com.example.freelance_be.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
