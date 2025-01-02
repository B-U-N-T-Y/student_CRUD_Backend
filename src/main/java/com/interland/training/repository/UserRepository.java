package com.interland.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.interland.training.entity.UserCredential;


public interface UserRepository extends JpaRepository<UserCredential, String> {

}
