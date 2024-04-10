package com.bfl.ui.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bfl.ui.auth.model.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	
	User findByEmail(String email);
	
}
