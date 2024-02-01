package com.boot.usercontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.bindings.LoginForm;
import com.boot.bindings.RegForm;
import com.boot.bindings.ResetPassword;
import com.boot.constants.AppConstants;
import com.boot.dao.User;
import com.boot.props.PropsConfig;
import com.boot.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	private UserService ser;

	@Autowired
	private HttpSession session;
	@Autowired
	private PropsConfig props;

	@GetMapping("/index")
	public String showLogin(Model model) {
		model.addAttribute("log", new LoginForm());
		return AppConstants.LOGIN;
	}

	@GetMapping("/reg")
	public String showRegForm(Model model) {
		model.addAttribute("user", new RegForm());
		model.addAttribute("countries", ser.getCountries());
		return AppConstants.REG_FORM;
	}

	@GetMapping("/reset")
	public String showResetForm(Model model) {
		model.addAttribute("res", new ResetPassword());
		return AppConstants.RESET_PWD;
	}

	@PostMapping("/validateUser")
	public String handleLogin(@ModelAttribute("log") LoginForm formObj, Model model) {
		User userObj = ser.login(formObj);
		if (userObj == null) {
			model.addAttribute("loginStatus", props.getMessages().get("InvalidLogin"));
			return AppConstants.LOGIN;
		}
		if (userObj.getPwdUpdated().equals('N')) {
			ResetPassword rp = new ResetPassword();
			rp.setUserId(userObj.getUserId());
			model.addAttribute("res", rp);
			return AppConstants.RESET_PWD;
		}
		session.setAttribute("User-Id", userObj.getUserId());
		return "redirect:/build";
	}

	@PostMapping("/save")
	public String registerUser(@ModelAttribute("user") RegForm formObj, Model model) {

		boolean saveUser = ser.saveUser(formObj);
		if (saveUser)
			model.addAttribute(AppConstants.REG_STATUS, props.getMessages().get("RegSuccessMsg"));
		else
			model.addAttribute(AppConstants.REG_STATUS, props.getMessages().get("RegFailMsg"));

		return AppConstants.REG_FORM;
	}

	@PostMapping("/reset")
	public String updatePwd(@ModelAttribute("res") ResetPassword formObj, Model model) {

		if (!formObj.getNewPwd().equals(formObj.getCnfPwd())) {
			model.addAttribute("msg", props.getMessages().get("pwdCheckMsg"));
			return AppConstants.RESET_PWD;
		}
		boolean status = ser.resetPassword(formObj);

		if (status) {
			return "redirect:/build";
		}
		return AppConstants.RESET_PWD;
	}

	@ResponseBody
	@GetMapping("/states")
	public Map<Integer, String> loadStates(Integer cid) {
		return ser.getStates(cid);
	}

	@ResponseBody
	@GetMapping("/cities")
	public Map<Integer, String> loadCities(Integer sid) {
		return ser.getCities(sid);
	}

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/index";
	}

}
