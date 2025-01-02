package com.interland.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.interland.training.entity.StudentPK;
import com.interland.training.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, StudentPK>, JpaSpecificationExecutor<Student> {

}
