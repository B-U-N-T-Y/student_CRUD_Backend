package com.interland.training.entity;

//import java.sql.Date;
import java.time.LocalDate;
//import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="STUDENT")

public class Student {
	
	@Id
	@EmbeddedId
	private StudentPK id;
	private String course;
	private String name;
	private String gender;
	private LocalDate dob;
	private String grade;
	private String status;
	
	public StudentPK getId() {
		return id;
	}
	public void setId(StudentPK id) {
		this.id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", course=" + course + ", name=" + name + ", gender=" + gender + ", dob=" + dob
				+ ", grade=" + grade + ", status=" + status + "]";
	}
	
	

}
