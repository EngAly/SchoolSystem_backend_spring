package com.fci.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fci.dao.TeacherRepo;
import com.fci.interfaces.ISMethods;
import com.fci.interfaces.ISMethods1;
import com.fci.models.PageableFields;
import com.fci.models.Response;
import com.fci.models.Teacher;

@Service
public class TeacherService implements ISMethods<Teacher>, ISMethods1<Teacher> {

	@Autowired
	TeacherRepo repo;

	@Autowired
	TeacherService service;

	private final String entityType = "Teacher ";

	@Override
	public ResponseEntity<Response> save(Teacher entity) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(entityType + "With Id= " + repo.save(entity).getId() + " Is Added Successfully"));
	}

	@Override
	public Page<Teacher> getAll(PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Teacher getById(long id) {
		return repo.findById(id).get();
	}
	
	@Override
	public Page<Teacher> getByName(String name, PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByNameContainingIgnoreCase(name,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByNameContainingIgnoreCase(name,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Page<Teacher> getByGender(String gender, PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByGender(gender,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByGender(gender,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Page<Teacher> getByAge(byte start, byte end, PageableFields fields) {
		start = (byte) (start > end ? (start + end - (end = start)) : start);
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByAgeGreaterThanEqualAndAgeLessThanEqual(start, end,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByAgeGreaterThanEqualAndAgeLessThanEqual(start, end,
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
	public ResponseEntity<Response> update(Teacher entity) {
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