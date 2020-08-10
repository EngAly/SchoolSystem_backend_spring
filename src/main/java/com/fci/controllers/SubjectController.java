package com.fci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fci.interfaces.ICMethods;
import com.fci.models.PageableFieldsBuilder;
import com.fci.models.Response;
import com.fci.models.Subject;
import com.fci.services.SubjectService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "subject")
public class SubjectController implements ICMethods<Subject> {

	@Autowired
	SubjectService service;

	@Override
	public ResponseEntity<Response> add(Subject subject) {
		return service.save(subject);
	}

	@Override
	public Page<Subject> findAll(int page, int pageSize, String sort, String direction) {
		return service.getAll(
				new PageableFieldsBuilder().direction(direction).pageSize(pageSize).sort(sort).page(page).build());
	}

	@Override
	public Subject findById(long id) {
		return service.getById(id);
	}

	@Override
	public Page<Subject> findByName(String search, int page, int pageSize, String sort, String direction) {
		return service.getByName(search,
				new PageableFieldsBuilder().direction(direction).sort(sort).pageSize(pageSize).page(page).build());
	}

	@Override
	public ResponseEntity<Response> deleteById(long id) {
		return service.delete(id);
	}

	@Override
	public ResponseEntity<Response> update(Subject entity) {
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
