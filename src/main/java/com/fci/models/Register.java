package com.fci.models;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class Register {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 120, nullable = false)
	private String username;

	@Column(length = 10)
	private String gender, role;

	
	private String password, phone, address;

	private Date joinDate;

	public Register() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	 
	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Register [id=").append(id).append(", username=").append(username).append(", gender=")
				.append(gender).append(", role=").append(role).append(", password=").append(password).append(", phone=")
				.append(phone).append(", address=").append(address).append(", registerDate=").append(joinDate)
				.append("]");
		return builder.toString();
	}

}
