package com.fci.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.Teacher;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

	// used in service only not visible to controller not need Pageable
	List<Teacher> findByName(String name);

	Page<Teacher> findByNameContainingIgnoreCase(String name, Pageable pageable);

	Page<Teacher> findByGender(String gender, Pageable pageable);

	Page<Teacher> findByAgeGreaterThanEqualAndAgeLessThanEqual(byte start, byte end, Pageable pageable);

////	@Query(value = "select id,  name, seasons.id from teacher", nativeQuery = true)
//	@Query(value = "select id,  name, seasons from teacher", nativeQuery = true)
//	List<TeacherData> findAllBy();

}
