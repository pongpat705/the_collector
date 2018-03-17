package th.co.collector.constants;

public enum Role {
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN"),
	ROLE_MAKER("ROLE_MAKER"),
	ROLE_CHECKER("ROLE_CHECKER"),
	ROLE_SUPERVISOR("ROLE_SUPERVISOR");
	
	private String roleName;

	Role(String roleName){
		this.roleName = roleName;
	}
	
	private String roleName(){
		return roleName;
	}
}
	