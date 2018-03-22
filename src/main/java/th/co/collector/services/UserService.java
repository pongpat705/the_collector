package th.co.collector.services;

import th.co.collector.components.UserBean;
import th.co.collector.entities.user.User;

public interface UserService {

	public String userToString(String userName);
	
	public User stringToUsers(String usersString);
	
	public User getUser(String userName);
	
	public void addUser(UserBean userBean);
	public void patchUser(UserBean userBean, Long userId);
}
