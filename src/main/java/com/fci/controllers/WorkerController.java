package com.fci.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fci.interfaces.ICMethods;
import com.fci.interfaces.ICMethods1;
import com.fci.models.PageableFieldsBuilder;
import com.fci.models.Response;
import com.fci.models.Worker;
import com.fci.services.WorkerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "worker")
public class WorkerController implements ICMethods<Worker>, ICMethods1<Worker> {

	@Autowired
	WorkerService service;

	@Override
	public ResponseEntity<Response> add(Worker worker) {
		return service.save(worker);
	}

	@Override
	public Page<Worker> findAll(int page, int pageSize, String sort, String direction) {
		return service.getAll(
				new PageableFieldsBuilder().direction(direction).pageSize(pageSize).sort(sort).page(page).build());
	}

	@Override
	public Worker findById(long id) {
		return service.getById(id);
	}

	@Override
	public Page<Worker> findByName(String search, int page, int pageSize, String sort, String direction) {
		return service.getByName(search,
				new PageableFieldsBuilder().direction(direction).sort(sort).pageSize(pageSize).page(page).build());
	}

	@Override
	public Page<Worker> findByGender(String gender, int page, int pageSize, String sort, String direction) {
		return service.getByGender(gender,
				new PageableFieldsBuilder().direction(direction).sort(sort).pageSize(pageSize).page(page).build());
	}

	@Override
	public Page<Worker> findByAge(byte start, byte end, int page, int pageSize, String sort, String direction) {
		return service.getByAge(start, end,
				new PageableFieldsBuilder().direction(direction).sort(sort).pageSize(pageSize).page(page).build());
	}

	@Override
	public ResponseEntity<Response> deleteById(long id) {
		return service.delete(id);
	}

	@Override
	public ResponseEntity<Response> update(Worker entity) {
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
