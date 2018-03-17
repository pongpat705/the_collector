package th.co.collector.components;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import th.co.collector.constants.Role;
import th.co.collector.entities.user.User;
import th.co.collector.entities.user.UserRole;
import th.co.collector.repositories.UserRepository;
import th.co.collector.repositories.UserRoleRepository;

@Component
@Order(2)
public class Setup {

	
private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@PostConstruct
	 public void settingData(){
//		initialRole();
	 }
	 
	 public void initialRole(){
		 log.info("inserting admin");
			User adminUser = new User();
			adminUser.setEnabled("1");
			adminUser.setUserName("supadmin");
			adminUser.setPassword("supadmin");
			
			userRepository.save(adminUser);
			
			UserRole adminRole = new UserRole();
			adminRole.setEnabled("1");
			adminRole.setRole(Role.ROLE_SUPER_ADMIN);
			adminRole.setUser(adminUser);
			
			userRoleRepository.save(adminRole);
			
	 }
	
}
