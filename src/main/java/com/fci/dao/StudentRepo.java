package com.fci.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

	// used in service only not visible to controller not need Pageable
	List<Student> findByName(String name);

	Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

	Page<Student> findByGender(String gender, Pageable pageable);

	Page<Student> findByAgeGreaterThanEqualAndAgeLessThanEqual(byte start, byte end, Pageable pageable);

}
