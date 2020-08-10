package com.fci.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Guardian;

@Repository
public interface GuardianRepo extends JpaRepository<Guardian, Long> {

	// used in service only not visible to controller not need Pageable
	List<Guardian> findByName(String name);

	Page<Guardian> findByNameContainingIgnoreCase(String name, Pageable pageable);

	Page<Guardian> findByGender(String gender, Pageable pageable);

	Page<Guardian> findByAgeGreaterThanEqualAndAgeLessThanEqual(byte start, byte end, Pageable pageable);

}
