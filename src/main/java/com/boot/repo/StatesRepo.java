package com.boot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.dao.States;

public interface StatesRepo extends JpaRepository<States, Integer> {

	List<States> findByCid(Integer cid);
	

}
