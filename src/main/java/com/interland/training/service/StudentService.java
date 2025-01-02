package com.interland.training.service;

import org.json.simple.JSONObject;

import com.interland.training.dto.ServiceResponse;
import com.interland.training.dto.StudentDTO;
import com.interland.training.entity.Student;
import com.interland.training.entity.StudentPK;

public interface StudentService {
	
	Student fetchById(StudentPK getById);
	
	ServiceResponse createUser(StudentDTO entity);
	
	ServiceResponse updateUser(StudentPK updateById, StudentDTO entity);
	
	JSONObject searchStreamDefinitionByLimit(String searchParam, int start, int pageSize);

	ServiceResponse deleteUser(StudentPK deleteById);

}
