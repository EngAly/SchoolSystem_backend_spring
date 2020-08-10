package com.fci.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Register;

@Repository
public interface RegisterRepo extends JpaRepository<Register, Long> {

	// used in service only not visible to controller not need Pageable
	List<Register> findByUsername(String name);

	Page<Register> findByUsernameContainingIgnoreCase(String name, Pageable pageable);

}
