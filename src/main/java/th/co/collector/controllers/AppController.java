package th.co.collector.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
