package com.interland.training.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.interland.training.entity.Student;

public class StudentSpecification {

	private static Logger logger = LogManager.getLogger(StudentSpecification.class);

	// page all
	public static Specification<Student> getStreamBySearchSpec(String searchParam) {

		return new Specification<Student>() {

			private static final long serialVersionUID = 1L;

			@SuppressWarnings({ "unused" })
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate finalPredicate = null;
				JSONParser parser = new JSONParser();
				JSONObject searchObject;

				try {

					Predicate statusDeletePredicate = null;
					if (StringUtils.hasLength(searchParam)) {
						searchObject = (JSONObject) parser.parse(searchParam);
						JSONObject searchData = (JSONObject) searchObject.get("streamSearchObj");

						String rollno = (String) searchObject.get("rollno");
						String department = (String) searchObject.get("department");
						String course = (String) searchObject.get("course");
						String name = (String) searchObject.get("name");
						String gender = (String) searchObject.get("gender");
						String dob = (String) searchObject.get("dob");
						String grade = (String) searchObject.get("grade");
						String status = (String) searchObject.get("status");
						String startDate = (String) searchObject.get("startDate");
						String endDate = (String) searchObject.get("endDate");
						
						Integer rollno1 = null;
						if(rollno != null) {
							rollno1 = Integer.parseInt(rollno);
						}

						if (StringUtils.hasLength(rollno)) {
							Predicate rollnoPredicate = criteriaBuilder.equal(root.get("id").get("rollno"),
									rollno1);
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, rollnoPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(rollnoPredicate);
							}

						}

						if (StringUtils.hasLength(department)) {
							Predicate departmentPredicate = criteriaBuilder.like(root.get("id").get("department"),
									department + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, departmentPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(departmentPredicate);
							}
						}
						
						if (StringUtils.hasLength(course)) {
							Predicate coursePredicate = criteriaBuilder.like(root.get("course"), course + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, coursePredicate);
							} else {
								finalPredicate = criteriaBuilder.and(coursePredicate);
							}
						}

						if (StringUtils.hasLength(name)) {
							Predicate namePredicate = criteriaBuilder.like(root.get("name"), name + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, namePredicate);
							} else {
								finalPredicate = criteriaBuilder.and(namePredicate);
							}
						}

						if (StringUtils.hasLength(gender)) {
							Predicate genderPredicate = criteriaBuilder.like(root.get("gender"), gender + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, genderPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(genderPredicate);
							}
						}
						
						LocalDate dob1= null;
						

						if (StringUtils.hasLength(dob)) {
							
							//convert string to LocalDate
							dob1 = LocalDate.parse(dob);
							
							
							Predicate dobPredicate = criteriaBuilder.equal(root.get("dob"), dob1);
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, dobPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(dobPredicate);
							}
						}
						
						LocalDate start = null;
						LocalDate end = null;
						
						if (StringUtils.hasLength(startDate) && StringUtils.hasLength(endDate)) {
							
							//convert string to LocalDate
							start = LocalDate.parse(startDate);
							end = LocalDate.parse(endDate);
							
							
							Predicate dobPredicate = criteriaBuilder.between(root.get("dob"), start, end);
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, dobPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(dobPredicate);
							}
						}
						

						if (StringUtils.hasLength(grade)) {
							Predicate gradePredicate = criteriaBuilder.like(root.get("grade"), grade + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, gradePredicate);
							} else {
								finalPredicate = criteriaBuilder.and(gradePredicate);
							}
						}

						if (StringUtils.hasLength(status)) {
							Predicate gradePredicate = criteriaBuilder.like(root.get("status"), status + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, gradePredicate);
							} else {
								finalPredicate = criteriaBuilder.and(gradePredicate);
							}
						}

					}
				} catch (ParseException  e) {
					logger.error("Error : " + e.getMessage(), e);
				}catch (Exception  e) {
					logger.error("Error : " + e.getMessage(), e);
				}
				

				return finalPredicate;
			}
		};

	}

}
