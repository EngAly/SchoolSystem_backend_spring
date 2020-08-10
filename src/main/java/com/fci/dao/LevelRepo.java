package com.fci.dao;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Level;

/**
 * if you want to use pagination in search you must use Pageable instead of
 * PageRequest it more working<br>
 * PageRequest will result errors and not work
 * 
 */
@Repository
public interface LevelRepo extends JpaRepository<Level, Long> {

	// used in service only not visible to controller not need Pageable
	List<Level> findByName(String name);

	Page<Level> findByNameContainingIgnoreCase(String search, Pageable pageable);

}
