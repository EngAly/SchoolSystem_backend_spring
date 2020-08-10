package com.fci.controllers;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fci.dao.TeacherRepo;
import com.fci.interfaces.ICMethods;
import com.fci.interfaces.ICMethods1;
import com.fci.models.PageableFieldsBuilder;
import com.fci.models.Response;
import com.fci.models.Teacher;
import com.fci.services.TeacherService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "teacher")
public class TeacherController implements ICMethods<Teacher>, ICMethods1<Teacher> {

	@Autowired
	TeacherRepo r;

	@Autowired
	TeacherService service;

	@Autowired
	EntityManager em;

	@Override
	public ResponseEntity<Response> add(Teacher entity) {
		return service.save(entity);
	}

	@Override
	public Page<Teacher> findAll(int page, int pageSize, String sort, String direction) {
		return service.getAll(
				new PageableFieldsBuilder().direction(direction).pageSize(pageSize).sort(sort).page(page).build());
	}

	@Override
	public Teacher findById(long id) {
		return service.getById(id);
	}

	@Override
	public Page<Teacher> findByName(String search, int page, int pageSize, String sort, String direction) {
		return service.getByName(search,
				new PageableFieldsBuilder().direction(direction).sort(sort).pageSize(pageSize).page(page).build());
	}

	@Override
	public Page<Teacher> findByAge(byte start, byte end, int page, int pageSize, String sort, String direction) {
		return service.getByAge(start, end,
				new PageableFieldsBuilder().direction(direction).sort(sort).pageSize(pageSize).page(page).build());
	}

	@Override
	public Page<Teacher> findByGender(String gender, int page, int pageSize, String sort, String direction) {
		return service.getByGender(gender,
				new PageableFieldsBuilder().direction(direction).sort(sort).pageSize(pageSize).page(page).build());
	}

	@Override
	public ResponseEntity<Response> deleteById(long id) {
		return service.delete(id);
	}

	@Override
	public ResponseEntity<Response> update(Teacher entity) {
		return service.update(entity);
	}

	@Override
	public long count() {
		return service.count();
	}

	@Override
	public ResponseEntity<Response> deleteAll() {
		return service.deleteAll();
	}
}
