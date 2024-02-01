package com.boot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.dao.Cities;

public interface CitiesRepo extends JpaRepository<Cities, Integer> {

	List<Cities> findBySid(Integer sid);
}
