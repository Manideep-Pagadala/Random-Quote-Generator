package com.boot.service;

import java.util.Map;

import com.boot.bindings.LoginForm;
import com.boot.bindings.RegForm;
import com.boot.bindings.ResetPassword;
import com.boot.dao.User;

public interface UserService {

	public Map<Integer, String> getCountries();

	public Map<Integer, String> getStates(Integer countryId);

	public Map<Integer, String> getCities(Integer stateId);

	public User getUser(String email);

	public boolean saveUser(RegForm formObj);

	public User login(LoginForm formObj);

	public boolean resetPassword(ResetPassword formObj);

	

}
