package th.co.collector.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import th.co.collector.constants.Role;
import th.co.collector.entities.parameter.SystemParameter;
import th.co.collector.repositories.parameter.SystemParameterRepository;
import th.co.collector.repositories.user.UserRepository;
import th.co.collector.repositories.user.UserRoleRepository;

@Controller
public class AppController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserRoleRepository userRoleRepository;
	
	@Autowired
	SystemParameterRepository systemParameterRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	String index(Model model, Principal principal, Authentication authentication, HttpServletRequest httpServletRequest) {
		model.addAttribute("message", "Hello Worlds");
		model.addAttribute("authorities",authentication);
		
		List<SystemParameter> moneyControlList = (List<SystemParameter>) systemParameterRepository.findByParamGroupOrderBySortNumber("MONEY_CONTROL_FORM");
		Map<String, SystemParameter> moneyControlMap = moneyControlList.stream().collect(Collectors.toMap(SystemParameter::getParamCode, x->x));
		model.addAttribute("moneyControlFroms", moneyControlMap);
		
		List<SystemParameter> balanceReportList = (List<SystemParameter>) systemParameterRepository.findByParamGroupOrderBySortNumber("BALANCE_REPORT");
		Map<String, SystemParameter> balanceReportMap = balanceReportList.stream().collect(Collectors.toMap(SystemParameter::getParamCode, x->x));
		model.addAttribute("balanceReportMap", balanceReportMap);
		
		List<SystemParameter> functionList = (List<SystemParameter>) systemParameterRepository.findByParamGroupOrderBySortNumber("FUNCTION");
		Map<String, SystemParameter> functionMap = functionList.stream().collect(Collectors.toMap(SystemParameter::getParamCode, x->x));
		model.addAttribute("functions", functionMap);
		
		List<SystemParameter> balanceList = (List<SystemParameter>) systemParameterRepository.findByParamGroupOrderBySortNumber("DROPDOWN_BALANCE");
		model.addAttribute("dropdown_balance", balanceList);
		Map<String, SystemParameter> balanceMap = balanceList.stream().collect(Collectors.toMap(SystemParameter::getParamCode, x->x));
		model.addAttribute("map_balance", balanceMap);
		
		List<SystemParameter> mobilizeList = (List<SystemParameter>) systemParameterRepository.findByParamGroupOrderBySortNumber("MOBILIZE");
		Map<String, SystemParameter> mobilizeMap = mobilizeList.stream().collect(Collectors.toMap(SystemParameter::getParamCode, x->x));
		model.addAttribute("mobilizeMap", mobilizeMap);
		
		
		model.addAttribute("role", Role.values());
		
		model.addAttribute("context", httpServletRequest.getContextPath());
		
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
	
	@RequestMapping(value="/out", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/index";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

}
