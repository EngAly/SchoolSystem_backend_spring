package com.fci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fci.interfaces.ICMethods;
import com.fci.models.PageableFieldsBuilder;
import com.fci.models.Register;
import com.fci.models.Response;
import com.fci.services.RegisterService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "register")
public class RegisterController implements ICMethods<Register> {

	@Autowired
	RegisterService service;

	@Override
	public ResponseEntity<Response> add(Register user) {
		return service.save(user);
	}

	@Override
	public Page<Register> findAll(int page, int pageSize, String sort, String direction) {
		return service.getAll(
				new PageableFieldsBuilder().direction(direction).pageSize(pageSize).sort(sort).page(page).build());
	}

	@Override
	public Register findById(long id) {
		return service.getById(id);
	}

	@Override
	public Page<Register> findByName(String search, int page, int pageSize, String sort, String direction) {
		return service.getByName(search,
				new PageableFieldsBuilder().direction(direction).pageSize(pageSize).sort(sort).page(page).build());
	}

	@Override
	public ResponseEntity<Response> deleteById(long id) {
		return service.delete(id);
	}

	@Override
	public ResponseEntity<Response> update(Register entity) {
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
