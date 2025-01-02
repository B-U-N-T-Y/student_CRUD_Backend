package com.interland.training.dto;

//import java.sql.Date;
import java.time.LocalDate;

//import java.util.Date;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class StudentDTO {
	
	@Min(value= 1, message = "RollNo cannot be null" )
	private int rollno;

	@NotEmpty(message="Department cannot be null")
	private String department;
	@NotEmpty(message="Department cannot be null")
	@Size(min = 1,max = 50,message= "Length of Course must be between 1 to 50 characters")
	private String course;

	@NotEmpty(message= "Name cannot be empty")
	private String name;
	
	@NotEmpty(message= "Gender cannot be empty")
	private String gender;
	
	@NotNull(message= "Date of birth cannot be empty")
	@Past(message= "Date of Birth should be a past date")
	private LocalDate dob;
	
	@Size(min = 1,max = 1,message= "Length of Grade must be 1 characters.")
	private String grade;
	
	private String status;

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	

}
