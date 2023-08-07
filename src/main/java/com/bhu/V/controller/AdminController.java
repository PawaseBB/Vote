package com.bhu.V.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bhu.V.entity.Admin;
import com.bhu.V.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	 @Autowired
	    private AdminRepository adminRepository;

	    @GetMapping("/registeradmin")
	    public String showRegistrationForm(org.springframework.ui.Model model) {
	        model.addAttribute("admin", new Admin());
	        return "registrationadmin";
	    }

	    @PostMapping("/registeradmin")
	    public String processRegistrationForm(@ModelAttribute("admin") Admin admin) {
	        adminRepository.save(admin);
	        return "redirect:/loginadmin";
	    }

	    @GetMapping("/loginadmin")
	    public String showLoginForm() {
	        return "loginadmin";
	    }

	    @PostMapping("/loginadmin")
	    public String processLoginForm(@RequestParam String adminname, 
	    		@RequestParam String password, HttpSession session) {
	        Admin admin = adminRepository.findByAdminname(adminname);
	        if (admin != null && admin.getPassword().equals(password)) {
	        	 session.setAttribute("adminname", adminname);
	            return "adminloginsuccessful"; // Redirect to user dashboard or homepage
	        }
	        return "redirect:/login?error=true";
	    }
}
