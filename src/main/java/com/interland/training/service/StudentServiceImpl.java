package com.interland.training.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.interland.training.dto.ServiceResponse;
import com.interland.training.dto.StudentDTO;
import com.interland.training.entity.Student;
import com.interland.training.entity.StudentPK;
import com.interland.training.repository.StudentRepository;
import com.interland.training.repository.specification.StudentSpecification;
import com.interland.training.utils.Constants;

@Service
public class StudentServiceImpl implements StudentService {
	
	 private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);
	 
	 @Autowired
	 StudentRepository repo;
	 
	 @Override
		public Student fetchById(StudentPK getById) {

			try {
				if (repo.existsById(getById)) {
					logger.info("FETCHED SUUCCESSFULLY");
					return repo.findById(getById).get();
				} else {
					return null;
				}

			} catch (Exception e) {
				logger.error("Error Occured: "+e.getMessage());
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public ServiceResponse createUser(StudentDTO entity) {
			
			try {
				StudentPK id = new StudentPK(entity.getRollno(),entity.getDepartment());			
				if (repo.existsById(id)) {
					logger.warn("User Already Exists.");					
					
					return new ServiceResponse("Failed", "Already Exists", null);
					
				} else {
					Student newEntity = new Student();
					newEntity.setId(id);
					BeanUtils.copyProperties(entity, newEntity);
					// Adding status value to Active 
					newEntity.setStatus("Active");
					
					repo.save(newEntity);
					logger.info("ADDED SUCCESSFULLY");
					
					JSONObject json = new JSONObject();
					json.put("rollno", newEntity.getId().getRollno());
					json.put("department", newEntity.getId().getDepartment());
					json.put("course", newEntity.getCourse());
					json.put("name", newEntity.getName());
					json.put("gender", newEntity.getGender());
					json.put("dob", newEntity.getDob());
					json.put("grade", newEntity.getGrade());
					json.put("status", newEntity.getStatus());
					List<JSONObject> obj = List.of(json);
					
					return new ServiceResponse("SUCCESSFUL", "ADDED SUCCESSFULLY", obj);
				}
			}catch (Exception e) {
				logger.error("Error Occured: "+e.getMessage());
			}
			
			return null;
		}
		
		@Override
		public ServiceResponse updateUser(StudentPK updateById, StudentDTO entity) {

			try {

				Optional<Student> existingEntityOpt = repo.findById(updateById);
				if (existingEntityOpt.isPresent()) {
					Student existingEntity = existingEntityOpt.get();

					BeanUtils.copyProperties(entity, existingEntity);
					
					repo.save(existingEntity);
					return new ServiceResponse("SUCCESSFUL", "UPDATED", null);
				
				} else {
					return new ServiceResponse("UNSUCCESSFUL", "Student does not Exists", null);
				}
			} catch (Exception e) {
				logger.error("Error Occured: "+e.getMessage());
			}

			return null;
		}
		
		@Override
		public ServiceResponse deleteUser(StudentPK deleteById) {
			try {

				Optional<Student> existingEntityOpt = repo.findById(deleteById);
				if (existingEntityOpt.isPresent()) {
					Student existingEntity = existingEntityOpt.get();
					
					existingEntity.setStatus("Deleted");
					
					repo.save(existingEntity);
					return new ServiceResponse("SUCCESSFUL", "Deleted", null);
				
				} else {
					return new ServiceResponse("UNSUCCESSFUL", "Student does not Exists", null);
				}
			} catch (Exception e) {
				logger.error("Error Occured: "+e.getMessage());
			}
			return null;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public JSONObject searchStreamDefinitionByLimit(String searchParam , int start, int pageSize) {
			JSONObject result = new JSONObject();
			try {
				Pageable pageable = PageRequest.of(start , pageSize);
				System.out.println(pageable);
				Page<Student> streamList = repo.findAll(StudentSpecification.getStreamBySearchSpec(searchParam),
						pageable);
				JSONArray array = new JSONArray();
				long count = repo.count(StudentSpecification.getStreamBySearchSpec(searchParam));
			
				for (Student studentsEntity : streamList) {
					JSONObject obj = new JSONObject();
					obj.put("rollno", studentsEntity.getId().getRollno());
					obj.put("department", studentsEntity.getId().getDepartment());
					obj.put("course", studentsEntity.getCourse());
					obj.put("name", studentsEntity.getName());
					obj.put("gender", studentsEntity.getGender());
					obj.put("dob", studentsEntity.getDob());
					obj.put("grade", studentsEntity.getGrade());
					obj.put("status", studentsEntity.getStatus());
					array.add(obj);
				}
				result.put(Constants.AA_DATA, array);
				result.put(Constants.TOTAL_DISPLAY_RECORD, count);
				result.put(Constants.TOTAL_RECORD, count);
			} catch (Exception e) {
				logger.error("Error : " + e.getMessage(), e);
				result.put(Constants.AA_DATA, new JSONArray());
				result.put(Constants.TOTAL_DISPLAY_RECORD, 0);
				result.put(Constants.TOTAL_RECORD, 0);
			}

			return result;
		}

		

}
