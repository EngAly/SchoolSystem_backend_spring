package com.fci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fci.interfaces.ICMethods;
import com.fci.models.Level;
import com.fci.models.PageableFieldsBuilder;
import com.fci.models.Response;
import com.fci.services.LevelService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "level")
public class LevelController implements ICMethods<Level> {

	@Autowired
	LevelService service;

	@Override
	public ResponseEntity<Response> add(Level entity) {
		return service.save(entity);
	}

	@Override
	public Page<Level> findAll(int page, int pageSize, String sort, String direction) {
		return service.getAll(
				new PageableFieldsBuilder().direction(direction).sort(sort).page(page).pageSize(pageSize).build());
	}

	@Override
	public Level findById(long id) {
		return service.getById(id);
	}
	
	@Override
	public Page<Level> findByName(String search, int page, int pageSize, String sort, String direction) {
		return service.getByName(search,
				new PageableFieldsBuilder().direction(direction).sort(sort).pageSize(pageSize).page(page).build());
	}

	@Override
	public ResponseEntity<Response> deleteById(long id) {
		return service.delete(id);
	}

	@Override
	public ResponseEntity<Response> update(Level entity) {
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
