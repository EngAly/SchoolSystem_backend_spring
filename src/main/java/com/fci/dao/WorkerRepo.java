package com.fci.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Worker;

@Repository
public interface WorkerRepo extends JpaRepository<Worker, Long> {

	Page<Worker> findByNameContainingIgnoreCase(String name, Pageable pageable);

	Page<Worker> findByGenderIgnoreCase(String gender, Pageable pageable);

	Page<Worker> findByAgeGreaterThanEqualAndAgeLessThanEqual(byte start, byte end, Pageable pageable);

}
