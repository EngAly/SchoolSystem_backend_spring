package com.fci.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Level {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 120, nullable = false)
	private String name;

	@Column(columnDefinition = "text")
	private String description;

	private byte floor;

	private short maxSize, currentSize;

//	@ManyToMany(mappedBy = "levels", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//	private List<Teacher> teachers;

	public Level() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getFloor() {
		return floor;
	}

	public void setFloor(byte floor) {
		this.floor = floor;
	}

	public short getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(short maxSize) {
		this.maxSize = maxSize;
	}

	public short getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(short currentSize) {
		this.currentSize = currentSize;
	}

//	@JsonIgnore
//	public List<Teacher> getTeachers() {
//		return teachers;
//	}
//
//	public void setTeachers(List<Teacher> teachers) {
//		this.teachers = teachers;
//	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Season [id=").append(id).append(", name=").append(name).append(", description=")
				.append(description).append(", floor=").append(floor).append(", maxSize=").append(maxSize)
				.append(", currentSize=").append(currentSize).append("]");
		return builder.toString();
	}

}
