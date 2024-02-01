package com.boot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.boot.bindings.LoginForm;
import com.boot.bindings.RegForm;
import com.boot.bindings.ResetPassword;
import com.boot.constants.AppConstants;
import com.boot.dao.Cities;
import com.boot.dao.Countries;
import com.boot.dao.States;
import com.boot.dao.User;
import com.boot.repo.CitiesRepo;
import com.boot.repo.CountriesRepo;
import com.boot.repo.StatesRepo;
import com.boot.repo.UserRepo;
import com.boot.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	private CountriesRepo countriesRepo;
	private StatesRepo statesRepo;
	private CitiesRepo citiesRepo;
	private UserRepo userRepo;
	private EmailUtils emailUtil;
	Random r = new Random();

	public UserServiceImpl(CountriesRepo countriesRepo, StatesRepo statesRepo, CitiesRepo citiesRepo, UserRepo userRepo,
			EmailUtils emailUtil) {

		this.countriesRepo = countriesRepo;
		this.statesRepo = statesRepo;
		this.citiesRepo = citiesRepo;
		this.userRepo = userRepo;
		this.emailUtil = emailUtil;
	}

	@Override
	public Map<Integer, String> getCountries() {
		Map<Integer, String> countriesMap = new HashMap<>();
		List<Countries> countriesList = countriesRepo.findAll();
		countriesList.forEach(c -> countriesMap.put(c.getCid(), c.getCountryName()));

		return countriesMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		Map<Integer, String> statesMap = new HashMap<>();
		List<States> statesList = statesRepo.findByCid(countryId);
		statesList.forEach(s -> statesMap.put(s.getSid(), s.getStateName()));

		return statesMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		Map<Integer, String> citesMap = new HashMap<>();
		List<Cities> citiesList = citiesRepo.findBySid(stateId);
		citiesList.forEach(c -> citesMap.put(c.getCityId(), c.getCityName()));

		return citesMap;
	}

	@Override
	public User getUser(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public boolean saveUser(RegForm formObj) {

		User user = new User();
		user.setPwdUpdated('N');
		user.setPwd(generatePassword());
		BeanUtils.copyProperties(formObj, user);

		userRepo.save(user);

		String sub = AppConstants.EMAIL_SUB;
		String body = "Your Password : " + user.getPwd();
		return emailUtil.sendMail(sub, body, user.getEmail());

	}

	@Override
	public User login(LoginForm formObj) {

		return userRepo.findByEmailAndPwd(formObj.getEmail(), formObj.getPwd());

	}

	@Override
	public boolean resetPassword(ResetPassword formObj) {

		Optional<User> user = userRepo.findById(formObj.getUserId());
		if (user.isPresent()) {
			User userObj = user.get();
			userObj.setCid(formObj.getUserId());
			userObj.setPwd(formObj.getCnfPwd());
			userObj.setPwdUpdated('Y');
			userRepo.save(userObj);
			return true;
		}
		return false;
	}

	private String generatePassword() {
		final String str = "0123456789abcdefghijklmnpqrstuvwxyz";
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 6; i++) {
			sb.append(str.charAt(r.nextInt(str.length() - 1)));
		}
		return sb.toString();
	}

}
