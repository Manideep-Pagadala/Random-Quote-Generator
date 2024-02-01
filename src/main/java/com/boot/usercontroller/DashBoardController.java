package com.boot.usercontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.service.DashboardResponseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashBoardController {

	@Autowired
	private DashboardResponseService ser;

	@GetMapping("/build")
	public String buildDashBoard(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("User-Id");
		if (userId == null) {
			return "redirect:/index";
		}

		model.addAttribute("q", ser.getQuote());
		return "dashBoard";
	}

	@GetMapping("/newQuote")
	@ResponseBody
	public String generateNewQuote(Model model, HttpSession session) {

		return ser.getQuote();
	}
}
