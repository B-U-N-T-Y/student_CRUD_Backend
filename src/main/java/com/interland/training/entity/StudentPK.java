package com.interland.training.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class StudentPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="rollno")
	private int rollno;
	@Column(name="department")
	private String department;
	@Override
	public int hashCode() {
		return Objects.hash(department, rollno);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentPK other = (StudentPK) obj;
		return Objects.equals(department, other.department) && rollno == other.rollno;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "StudentPK [rollno=" + rollno + ", department=" + department + "]";
	}
	public StudentPK(int rollno, String department) {
		super();
		this.rollno = rollno;
		this.department = department;
	}
	public StudentPK() {
		super();
		// TODO Auto-generated constructor stub
	}

}
