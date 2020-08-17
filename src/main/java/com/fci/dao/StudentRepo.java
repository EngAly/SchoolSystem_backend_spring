package com.fci.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fci.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

	// used in service only not visible to controller not need Pageable
	List<Student> findByName(String name);

	Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

	Page<Student> findByGender(String gender, Pageable pageable);

	Page<Student> findByAgeGreaterThanEqualAndAgeLessThanEqual(byte start, byte end, Pageable pageable);

	Page<Student> findByLevel_NameContainingIgnoreCase(String level, Pageable pageable);

//	ist that content male count and female count
	@Query(value = "SELECT s.gender,count(*) FROM student as s" + " GROUP BY s.gender", nativeQuery = true)
	List<Object> countTotalStudentByGender();

//	each level and its student count<br> example: [{primary,4},{secondary,10}]
	@Query(value = "SELECT s.level_id,count(*) FROM student as s" + " GROUP BY s.level_id", nativeQuery = true)
	List<Object> countTotalStudentByLevel();

	Page<Student> findByJoinDateGreaterThanEqualAndJoinDateLessThanEqual(Date start, Date end, Pageable pageable);
}
