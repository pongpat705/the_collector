package th.co.collector.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.co.collector.components.UserBean;
import th.co.collector.entities.user.User;
import th.co.collector.entities.user.UserRole;
import th.co.collector.repositories.user.UserRepository;
import th.co.collector.repositories.user.UserRoleRepository;
import th.co.collector.services.UserService;

@RestController
@RequestMapping(value = "/service")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRoleRepository roleRepository;
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public void addUser(HttpServletRequest request, 
			  				@RequestBody UserBean userBean) throws Exception {
		userService.addUser(userBean);
	}
	
	@RequestMapping(value = "/patchUser/{userId}", method = RequestMethod.PATCH)
	public void patchUser(HttpServletRequest request, 
			  				@RequestBody UserBean userBean,
			  				@PathVariable(name="userId") Long userId) throws Exception {
		userService.patchUser(userBean, userId);
	}
	@RequestMapping(value = "/deleteUsers/{userId}", method = RequestMethod.PATCH)
	public void deleteUsers(HttpServletRequest request, 
			  				@PathVariable(name="userId") Long userId) throws Exception {
		User user = userRepository.findById(userId).get();
		for (UserRole xRole : user.getUserRole()) {
			roleRepository.delete(xRole);
		}
		userRepository.deleteById(userId);
	}
	
	@RequestMapping(value = "/getUserForPatch/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserBean> getUserForPatch(HttpServletRequest request, 
			@PathVariable(name="userId") Long userId) throws Exception {
		User user = userRepository.findById(userId).get();
		UserBean userBean = new UserBean();
		userBean.setName(user.getName());
		userBean.setUserName(user.getUserName());
		userBean.setDivision(user.getDivision());
		userBean.setPassword(user.getPassword());
		
		List<String> role = new ArrayList<String>();
		userBean.setRole(role);
		for (UserRole xRole : user.getUserRole()) {
			userBean.getRole().add(xRole.getRole().name());
		}
		
		return new ResponseEntity<UserBean>(userBean, HttpStatus.OK);
	}
}
