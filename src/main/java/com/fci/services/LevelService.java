package com.fci.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fci.dao.LevelRepo;
import com.fci.interfaces.ISMethods;
import com.fci.models.Level;
import com.fci.models.PageableFields;
import com.fci.models.Response;
import com.fci.models.Subject;

@Service
public class LevelService implements ISMethods<Level>, UnaryOperator<List<Subject>> {

	@Autowired
	LevelRepo repo;

	@Autowired
	SubjectService service;

	private final String entityType = "Season ";

	ArrayList<Subject> withIds;

	@Override
	public ResponseEntity<Response> save(Level entity) {
		if (repo.findByName(entity.getName()).isEmpty()) {
//			entity.setSubjects(apply(entity.getSubjects()));
			return ResponseEntity.status(HttpStatus.OK).body(
					new Response(entityType + "With Id= " + repo.save(entity).getId() + " Is Added Successfully"));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Response(entityType + "Is Previous Added"));
	}

	@Override
	public Page<Level> getAll(PageableFields fields) {
		if (fields.getDirection().equalsIgnoreCase("desc")) {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).descending()));
		} else {
			return this.repo.findAll(
					PageRequest.of(fields.getPage(), fields.getPageSize(), Sort.by(fields.getSort()).ascending()));
		}
	}

	@Override
	public Level getById(long id) {
		return repo.findById(id).get();
	}
	
	@Override
	public Page<Level> getByName(String name, PageableFields fields) {
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
	public long count() {
		return repo.count();
	}

	@Override
	public ResponseEntity<Response> update(Level entity) {
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

	/**
	 * use method in functional interface to be as bridge between entities not have
	 * id and entities with id<br>
	 * input: list of entities that not have any id for its records<br>
	 * return: list of entities that not have any id for its records<br>
	 * process: take entity with no id and handle record by name with its id
	 */
	@Override
	public List<Subject> apply(List<Subject> withoutIds) {
//		withIds = new ArrayList<>();
//		withoutIds.forEach(item -> withIds.add(service.getByName(item.getName()).get(0)));
//		return withIds;
		return null;
	}

}
