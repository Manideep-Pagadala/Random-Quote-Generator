package com.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.dao.Countries;
import java.util.List;

public interface CountriesRepo extends JpaRepository<Countries, Integer> {

	List<Countries> findByCountryName(String countryName);
}
