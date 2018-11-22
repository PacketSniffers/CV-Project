
public class User 
{
	private String userName;
	private String password;
	private Role userRole;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = RoleList.getRoleList().giveRole(userRole);
	}
}
