package com.fci.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fci.dao.GuardianRepo;
import com.fci.dao.StudentRepo;
import com.fci.interfaces.BusinessAbstracts;
import com.fci.interfaces.ISMethods1;
import com.fci.models.Grade;
import com.fci.models.PageableFields;
import com.fci.models.Response;
import com.fci.models.Student;

@Service
public class StudentService implements BusinessAbstracts<Student>, ISMethods1<Student> {

	@Autowired
	private StudentRepo repo;

	@Autowired
	private GuardianRepo guardianRepo;

	private final String entityType = "Student ";

	@Override
	public ResponseEntity<Response> save(Student entity) {
		System.out.println(entity);
		// after save entity will return same entity addition to id
//		test if Guardian not pre founded so add it first to return entity with id
		if (entity.getGuardian().getId() == null) {
			entity.setGuardian(guardianRepo.save(entity.getGuardian()));
		}
		if (entity.getGrades().size() > 0) {
			List<Grade> grades = new ArrayList<Grade>(entity.getGrades());
			entity.getGrades().clear();

//          add student to each grade
			grades.forEach(grade -> {
				grade.setStudent(entity);
			});

//			add each grade to student
			grades.forEach(grade -> {
				entity.getGrades().add(grade);
			});
		}
		System.out.println(entity);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(entityType + "With Id= " + repo.save(entity).getId() + " Is Added Successfully"));
	}

	@Override
	public Page<Student> getAll(PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Student getById(long id) {
		return repo.findById(id).get();
	}

	@Override
	public Page<Student> getByName(String name, PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByNameContainingIgnoreCase(name,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByNameContainingIgnoreCase(name,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Page<Student> getByGender(String gender, PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByGender(gender,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByGender(gender,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Page<Student> getByAge(byte start, byte end, PageableFields fields) {
		start = (byte) (start > end ? (start + end - (end = start)) : start);
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByAgeGreaterThanEqualAndAgeLessThanEqual(start, end,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByAgeGreaterThanEqualAndAgeLessThanEqual(start, end,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	public Page<Student> getByLevel(String level, PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByLevel_NameContainingIgnoreCase(level,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByLevel_NameContainingIgnoreCase(level,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public long count() {
		return repo.count();
	}

	@Override
	public ResponseEntity<Response> delete(long id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response(entityType + "With Id= " + id + " Is Removed Successfully"));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Response(entityType + "With Id= " + id + " Is Not Found"));
	}

	@Override
	public ResponseEntity<Response> deleteAll() {
		repo.deleteAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response("All " + entityType.trim() + "s" + " Are Removed"));
	}

	@Override
	public ResponseEntity<Response> update(Student entity) {
		if (entity.getId() == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response(entityType + "Is Not Found"));
		} else if (!repo.existsById(entity.getId())) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response(entityType + "With Id= " + entity.getId() + " Is Not Found"));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(entityType + "With Id= " + repo.save(entity).getId() + " Is Updated Successfully"));
	}

}
