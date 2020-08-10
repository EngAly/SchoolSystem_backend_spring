package com.fci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fci.interfaces.ICMethods;
import com.fci.models.Guardianship;
import com.fci.models.PageableFieldsBuilder;
import com.fci.models.Response;
import com.fci.services.GuardianshipService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "guardianship")
public class GuardianshipController implements ICMethods<Guardianship> {

	@Autowired
	GuardianshipService service;

	@Override
	public ResponseEntity<Response> add(Guardianship guardianship) {
		return service.save(guardianship);
	}

	@Override
	public Page<Guardianship> findAll(int page, int pageSize, String sort, String direction) {
		return service.getAll(
				new PageableFieldsBuilder().direction(direction).sort(sort).page(page).pageSize(pageSize).build());
	}

	@Override
	public Guardianship findById(long id) {
		return service.getById(id);
	}

	@Override
	public Page<Guardianship> findByName(String search, int page, int pageSize, String sort, String direction) {
		return service.getByName(search,
				new PageableFieldsBuilder().direction(direction).sort(sort).page(page).pageSize(pageSize).build());
	}

	@Override
	public ResponseEntity<Response> deleteById(long id) {
		return service.delete(id);

	}

	@Override
	public ResponseEntity<Response> update(Guardianship guardianship) {
		return service.update(guardianship);
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
