import java.util.ArrayList;
import java.util.List;

public class RoleList
{
	static private List<Role>roles = new ArrayList<Role>();
	static RoleList roleListRef;
	
	
	private RoleList()
	{
		addRole(new Role("admin", new Permissions()));
		addRole(new Role("user", new Permissions()));
		addRole(new Role("trainer", new Permissions()));
	}
	
	
	public Role giveRole(String role)
	{
		for(int i = 0; i < roles.size(); i++)
		{
			if(roles.get(i).getName().equals(role))
			{
				return roles.get(i);
			}
		}
		
		throw new NullPointerException("No such role exists"); 
	}
	
	public void addRole(Role role)
	{
		roles.add(role);
	}
	
	static public RoleList getRoleList()
	{
		if(roleListRef != null)
		{
			return roleListRef;
		}
		else
		{
			roleListRef = new RoleList();
			return roleListRef;
		}
	}
	
}
