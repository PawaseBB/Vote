package com.bhu.V.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bhu.V.entity.User;
import com.bhu.V.repository.UserRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/registeruser")
	public String showRgisration(Model model) {
		model.addAttribute("user", new User());
		return "registrationuser";
	}
	
	 @PostMapping("/registeruser")
	    public String processRegistrationForm(@ModelAttribute("user") User user) {
	        userRepository.save(user);
	        return "redirect:/loginuser";
	    }
	 @GetMapping("/loginuser")
		public String showLogin() {
			return "loginuser";
		}
	 @PostMapping("/loginuser")
	 public String validateloginuser(@RequestParam String username, 
			 @RequestParam String password,  HttpSession session) {
		 User user = userRepository.findByUsername(username);
		 
			 if (user != null && user.getPassword().equals(password)) {
				 session.setAttribute("username", username);
				 return "userloginsuccessful";
			 } else {
				 return "redirect:/loginuser";
			 }
	 }
	
}
