package th.co.collector.services;

import java.io.IOException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import th.co.collector.components.UserBean;
import th.co.collector.constants.Role;
import th.co.collector.entities.user.User;
import th.co.collector.entities.user.UserRole;
import th.co.collector.repositories.user.UserRepository;
import th.co.collector.repositories.user.UserRoleRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired UserRepository usersRepository;
	
	@Autowired UserRoleRepository userRoleRepository;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	public String userToString(String userName){
    	User user = usersRepository.findByUserName(userName);
    	ObjectMapper mapper = new ObjectMapper();
    	String tramformed = "";
		try {
			tramformed = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
    	
		return tramformed;
    }
	
	public User getUser(String userName){
		log.info("get userBean of "+userName);
		User user = usersRepository.findByUserName(userName);
		return user;
	}
    
    public User stringToUsers(String usersString) {
    	ObjectMapper mapper = new ObjectMapper();
    	User users = null;
		try {
			users = mapper.readValue(usersString, User.class);
		} catch (JsonParseException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (JsonMappingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return users;
    }

    @Transactional
	@Override
	public void addUser(UserBean userBean) {
		// TODO Auto-generated method stub
		log.info("inserting user");
		User user = new User();
		user.setEnabled("1");
		user.setName(userBean.getName());
		user.setUserName(userBean.getUserName());
		user.setPassword(userBean.getPassword());
		usersRepository.save(user);
		
		for (String role : userBean.getRole()) {
			UserRole userRole = new UserRole();
			userRole.setEnabled("1");
			userRole.setRole(Role.valueOf(role));
			userRole.setUser(user);
			
			userRoleRepository.save(userRole);
		}
		
	}

    @Transactional
    @Override
	public void patchUser(UserBean userBean, Long userId) {
		// TODO Auto-generated method stub
		User user = usersRepository.findById(userId).get();
		user.setName(userBean.getName());
		user.setPassword(userBean.getPassword());
		for (UserRole xRole : user.getUserRole()) {
			userRoleRepository.delete(xRole);
		}
		
		for (String xRole : userBean.getRole()) {
			UserRole userRole = new UserRole();
			userRole.setEnabled("1");
			userRole.setRole(Role.valueOf(xRole));
			userRole.setUser(user);
			
			userRoleRepository.save(userRole);
		}
		usersRepository.save(user);
	}

}
