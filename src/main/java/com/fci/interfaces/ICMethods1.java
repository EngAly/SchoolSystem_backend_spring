package com.fci.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "default")
public interface ICMethods1<T> {

	@GetMapping("/byGender/{gender}")
	public Page<T> findByGender(String gender, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "8") int pageSize, @RequestParam(defaultValue = "id") String sort,
			@RequestParam(defaultValue = "asc") String direction);

	@GetMapping("/byAge/{age}")
	public Page<T> findByAge(byte start, byte end, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "8") int pageSize, @RequestParam(defaultValue = "age") String sort,
			@RequestParam(defaultValue = "asc") String direction);

}
