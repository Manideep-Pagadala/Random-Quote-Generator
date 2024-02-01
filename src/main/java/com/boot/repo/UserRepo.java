package com.boot.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.dao.User;

public interface UserRepo extends JpaRepository<User, Serializable> {

	User findByEmail(String email);
	User findByEmailAndPwd(String email, String pwd);
}
