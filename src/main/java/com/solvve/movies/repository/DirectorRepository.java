package com.solvve.movies.repository;

import com.solvve.movies.domain.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DirectorRepository extends JpaRepository<Director, UUID> {
}
