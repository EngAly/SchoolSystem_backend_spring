package com.fci.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Subject;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {

	// used in service only not visible to controller not need Pageable
	List<Subject> findByName(String name);

	Page<Subject> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
