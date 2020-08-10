package com.fci.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fci.models.Response;

@RequestMapping(path = "default")
public interface ICMethods<T> { // Interface Controller Methods

	@PostMapping("/add")
	public ResponseEntity<Response> add(@RequestBody T entity);

	@GetMapping("/byId/{id}")
	public T findById(@PathVariable("id") long id);

	@GetMapping({ "", "/" })

	public Page<T> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int pageSize,
			@RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "asc") String direction);

	@GetMapping("/count")
	public long count();

	@GetMapping("/byName/{search}")
	public Page<T> findByName(@PathVariable String search, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "8") int pageSize, @RequestParam(defaultValue = "name") String sort,
			@RequestParam(defaultValue = "asc") String direction);

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteById(@PathVariable("id") long id);

	@DeleteMapping("/eraseAll")
	public ResponseEntity<Response> deleteAll();

	@PutMapping("/update")
	public ResponseEntity<Response> update(@RequestBody T entity);

}
