package com.interland.training.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.interland.training.dto.ServiceResponse;
import com.interland.training.dto.StudentDTO;
import com.interland.training.dto.User;
import com.interland.training.entity.Student;
import com.interland.training.entity.StudentPK;
import com.interland.training.entity.UserCredential;
import com.interland.training.repository.UserRepository;
import com.interland.training.service.StudentService;

import jakarta.validation.Valid;


@RestController
@RequestMapping ("/student")
@CrossOrigin(origins = "http://localhost:4200")

public class StudentController {
	
	 private static final Logger logger = LogManager.getLogger(StudentController.class);
	 
	 @Autowired
	 StudentService service;
	 
	 @Autowired
	 RestTemplate restTemplate;
		
	 @Autowired
	 UserRepository userRepository;
	 
	 @GetMapping("/get/{rollno}/{department}")
		public Student demo(@PathVariable int rollno, @PathVariable String department) {
			StudentPK  getById = new StudentPK(rollno, department);
			return service.fetchById(getById);
		}
	 
	 @PostMapping("/add")
		public ResponseEntity<ServiceResponse> adduser (@Valid @RequestBody StudentDTO entity)  {
			return new ResponseEntity<ServiceResponse>(service.createUser(entity), HttpStatus.OK) ;
		}
	 @PutMapping("/update/{rollno}/{department}")
		public ResponseEntity<ServiceResponse> updateuser(@PathVariable int rollno, @PathVariable String department,@Valid @RequestBody StudentDTO entity) {
			StudentPK  updateById = new StudentPK(rollno, department);
			return new ResponseEntity<ServiceResponse>(service.updateUser(updateById,entity), HttpStatus.OK) ;
		}
	 
	 @PutMapping("/delete")
		public ResponseEntity<ServiceResponse> deleteuser(@Valid @RequestBody StudentDTO entity) {
			StudentPK  deleteById = new StudentPK(entity.getRollno(), entity.getDepartment());
			return new ResponseEntity<ServiceResponse>(service.deleteUser(deleteById), HttpStatus.OK) ;
		}
	 
	 @GetMapping("/search")
		public ResponseEntity<JSONObject> searchByPageGroup(@RequestParam String searchParam,
				@RequestParam("iDisplayStart") String iDisplayStart, @RequestParam("iDisplayLength") String iDisplayLength)
	 {

			JSONObject list = new JSONObject();
			try {
				list = service.searchStreamDefinitionByLimit(searchParam, Integer.parseInt(iDisplayStart),
						Integer.parseInt(iDisplayLength));
			} catch (Exception e) {
				logger.error("Error : " + e.getMessage(), e);
			}

			return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
		}
	 
	 @SuppressWarnings("unchecked")
		@PostMapping("/getaccesstoken")
		public Object getAccessToken(@RequestBody User user) {
			
			Optional<UserCredential> users= userRepository.findById(user.getUserName());
			
			JSONObject jsonObject=new JSONObject();
			
			if(users.isPresent()) {
				String url="http://localhost:8080/realms/MyRealm/protocol/openid-connect/token";
				MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
				formData.add("grant_type", "password");
				formData.add("client_id", "SpringBootKeyCloak");
				formData.add("username", user.getUserName());
				formData.add("password", user.getPassword());
				formData.add("client_secret", "8BUzQsnfkRn6wVkpu2gxg2kMKkPzMvNM");
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);
				return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
			}else {
				jsonObject.put("Data", "User Doesn't Exist !!");
				return new ServiceResponse("Failed",null,List.of(jsonObject));
			}
	 }
		
	
	

}
