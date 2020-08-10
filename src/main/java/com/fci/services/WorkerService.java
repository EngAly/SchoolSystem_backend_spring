package com.fci.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fci.dao.WorkerRepo;
import com.fci.interfaces.ISMethods;
import com.fci.interfaces.ISMethods1;
import com.fci.models.PageableFields;
import com.fci.models.Response;
import com.fci.models.Worker;

@Service
public class WorkerService implements ISMethods<Worker>, ISMethods1<Worker> {

	@Autowired
	WorkerRepo repo;

	private final String entityType = "Worker ";

	@Autowired
	GuardianshipService service;

	public ResponseEntity<Response> save(Worker entity) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(entityType + "With Id= " + repo.save(entity).getId() + " Is Added Successfully"));
	}

	@Override
	public Page<Worker> getAll(PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Worker getById(long id) {
		return repo.findById(id).get();
	}
	
	@Override
	public Page<Worker> getByGender(String gender, PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByGenderIgnoreCase(gender,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByGenderIgnoreCase(gender,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Page<Worker> getByAge(byte start, byte end, PageableFields fields) {
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
	public Page<Worker> getByName(String name, PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByNameContainingIgnoreCase(name,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByNameContainingIgnoreCase(name,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
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
	public ResponseEntity<Response> update(Worker entity) {
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

	@Override
	public ResponseEntity<Response> deleteAll() {
		repo.deleteAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response("All " + entityType.trim() + "s" + " Are Removed"));
	}

}
