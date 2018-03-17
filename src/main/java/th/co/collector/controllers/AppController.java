package th.co.collector.controllers;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import th.co.collector.entities.user.User;
import th.co.collector.repositories.UserRepository;
import th.co.collector.repositories.UserRoleRepository;

@Controller
public class AppController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	String index(Model model, Principal principal, Authentication authentication) {
		model.addAttribute("message", "Hello Worlds");
		model.addAttribute("authorities", authentication.getName());
		return "index";
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	String user(Model model, Principal principal, Authentication authentication) {
		
		model.addAttribute("userList", userRepository.findAll());
		return "user/user";
	}
	
	@RequestMapping(value="/user/{id}/role", method=RequestMethod.GET)
	String userRole(Model model, @PathVariable Long id) {
		model.addAttribute("roleList", userRoleRepository.findByUser(userRepository.findById(id).get()));
		return "user/role";
	}

}
