package com.fci.interfaces;

import org.springframework.data.domain.Page;

import com.fci.models.PageableFields;

public interface ISMethods1<T> { // interface service methods2

	public Page<T> getByGender(String gender, PageableFields fields);

	public Page<T> getByAge(byte start, byte end, PageableFields fields);

}
