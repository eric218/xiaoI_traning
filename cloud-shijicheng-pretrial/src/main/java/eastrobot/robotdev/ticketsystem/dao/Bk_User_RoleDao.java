package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;

import eastrobot.robotdev.ticketsystem.model.Bk_User_Role;

public interface Bk_User_RoleDao {
	
	public int insert(Bk_User_Role user_role);
	
	public int delete(String user_id);
	
	public int update(Bk_User_Role user_role);
	
	public Bk_User_Role select(String user_id);
	
	public List<Bk_User_Role> selectByRoleId(String role_id);
}
