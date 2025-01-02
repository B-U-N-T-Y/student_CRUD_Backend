package com.interland.training.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_TABLE")
public class UserCredential {
	
	@Id
	@Column(name = "userName")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserCredential() {
		super();
	}

	public UserCredential(String userName) {
		super();
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserCredential [userName=" + userName + "]";
	}
	
}
