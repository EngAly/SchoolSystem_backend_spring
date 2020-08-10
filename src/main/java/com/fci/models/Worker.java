package com.fci.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Worker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 120, nullable = false)
	private String name;

	private String address, phone;

	@Column(length = 10)
	private String gender;

	private byte age;

	private Date joinDate;

	@Column(columnDefinition = "text")
	private String description;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Guardianship.class)
	@JoinTable(name = "worker_guardianship", joinColumns = @JoinColumn(name = "worker_id"), inverseJoinColumns = @JoinColumn(name = "guardianship_id"))
	private List<Guardianship> guardianships = new ArrayList<>();

	public Worker() {
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

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public List<Guardianship> getGuardianships() {
		return guardianships;
	}

	public void setGuardianships(List<Guardianship> guardianships) {
		this.guardianships = guardianships;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Worker [id=").append(id).append(", name=").append(name).append(", address=").append(address)
				.append(", phone=").append(phone).append(", gender=").append(gender).append(", guardianships=")
				.append(guardianships).append("]");
		return builder.toString();
	}
}
