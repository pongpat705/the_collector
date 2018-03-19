package th.co.collector.entities.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import th.co.collector.constants.Role;

@Entity
@Table(name = "USER_ROLE")
@TableGenerator(name="GEN_USER_ROLE", initialValue=0, allocationSize=1)
public class UserRole {

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_USER_ROLE")
    private Long roleId;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String enabled;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;


	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
