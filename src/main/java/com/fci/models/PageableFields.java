package com.fci.models;

public class PageableFields {

	protected int page;
	protected int pageSize;
	protected String sort;
	protected String direction;

	public int getPage() {
		return page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public String getSort() {
		return sort;
	}

	public String getDirection() {
		return direction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageableFields [page=").append(page).append(", pageSize=").append(pageSize).append(", sort=")
				.append(sort).append(", direction=").append(direction).append("]");
		return builder.toString();
	}

}
