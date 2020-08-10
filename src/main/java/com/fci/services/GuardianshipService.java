package com.fci.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fci.dao.GuardianshipRepo;
import com.fci.interfaces.ISMethods;
import com.fci.models.Guardianship;
import com.fci.models.PageableFields;
import com.fci.models.Response;

@Service
public class GuardianshipService implements ISMethods<Guardianship> {

	@Autowired
	GuardianshipRepo repo;

	private final String entityType = "Guardianship";

	@Override
	public long count() {
		return repo.count();
	}

	@Override
	public Page<Guardianship> getAll(PageableFields fields) {

		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Guardianship getById(long id) {
		return repo.findById(id).get();
	}

	@Override
	public Page<Guardianship> getByName(String name, PageableFields fields) {
		System.out.println(fields.toString());
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return repo.findByNameContainingIgnoreCase(name,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return repo.findByNameContainingIgnoreCase(name,
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public ResponseEntity<Response> save(Guardianship entity) {
		if (repo.findByName(entity.getName()).isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(
					new Response(entityType + " With Id= " + repo.save(entity).getId() + " Is Added Successfully"));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Response(entityType + "Is Previous Added"));
	}

	@Override
	public ResponseEntity<Response> update(Guardianship entity) {
		if (entity.getId() == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response(entityType + "Is Not Found"));
		} else if (!repo.existsById(entity.getId())) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new Response(entityType + " With Id= " + entity.getId() + " Is Not Found"));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(entityType + " With Id= " + repo.save(entity).getId() + " Is Updated Successfully"));
	}

	@Override
	public ResponseEntity<Response> delete(long id) {
		if (repo.findById(id).isPresent()) {
			repo.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response(entityType + " With Id= " + id + " Is Removed Successfully"));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Response(entityType + " With Id= " + id + " Is Not Found"));
	}

	@Override
	public ResponseEntity<Response> deleteAll() {
		repo.deleteAll();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response("All " + entityType.trim() + "s" + " Are Removed"));
	}

}
