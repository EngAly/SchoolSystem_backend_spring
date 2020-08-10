package com.fci.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.fci.models.PageableFields;
import com.fci.models.Response;

public interface ISMethods<T> { // interface service methods1

	public ResponseEntity<Response> save(T entity);

	public Page<T> getAll(PageableFields fields);

	public T getById(long id);

	public Page<T> getByName(String name, PageableFields fields);
//	public Page<T> getByName(String name, int page, String sort, String direction);

	public long count();

	public ResponseEntity<Response> delete(long id);

	public ResponseEntity<Response> deleteAll();

	public ResponseEntity<Response> update(T entity);

//	default public List<T> getByGender(String gender) {
//		return null;
//	}

}
