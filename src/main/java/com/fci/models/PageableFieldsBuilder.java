package com.fci.models;

public class PageableFieldsBuilder {

	PageableFields fields;

	public PageableFieldsBuilder() {
		fields = new PageableFields();
	}

	public PageableFieldsBuilder page(int page) {
		fields.page = page;
		return this;
	}

	public PageableFieldsBuilder pageSize(int pageSize) {
		fields.pageSize = pageSize;
		return this;
	}

	public PageableFieldsBuilder sort(String sort) {
		fields.sort = sort;
		return this;
	}

	public PageableFieldsBuilder direction(String direction) {
		fields.direction = direction;
		return this;
	}

	public PageableFields build() {
		return fields;
	}
}
