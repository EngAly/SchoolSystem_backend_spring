package com.fci.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Guardianship;

@Repository
public interface GuardianshipRepo extends JpaRepository<Guardianship, Long> {

	// used in service only not visible to controller not need Pageable
	List<Guardianship> findByName(String name);

	Page<Guardianship> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
