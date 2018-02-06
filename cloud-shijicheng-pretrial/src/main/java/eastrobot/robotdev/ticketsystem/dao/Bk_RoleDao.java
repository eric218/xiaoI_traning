package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;

import eastrobot.robotdev.ticketsystem.model.Bk_Role;

public interface Bk_RoleDao {
	
	public int insert(Bk_Role role);
	
	public int delete(String id);
	
	public int update(Bk_Role role);
	
	public Bk_Role select(String id);
	
	public Bk_Role selectByName(String name);
	
	public List<Bk_Role> selectAll();
}
