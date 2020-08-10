package com.fci.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "text")
	private String grade;

	@Column(nullable = false)
	private int year;

	@Column(nullable = false)
	private byte month;

//	by default fetch = FetchType.EAGER
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "student_id")
	private Student student = new Student();

	public Grade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getYear() {
		return year;
	}

	@JsonIgnore
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public byte getMonth() {
		return month;
	}

	public void setMonth(byte month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", grade=" + grade + ", year=" + year + ", month=" + month + "]";
	}

}
