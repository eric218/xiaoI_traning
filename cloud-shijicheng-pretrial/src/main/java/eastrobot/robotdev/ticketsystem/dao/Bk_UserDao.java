package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;

import eastrobot.robotdev.ticketsystem.model.Bk_User;

public interface Bk_UserDao {
	
	public int insert(Bk_User user);
	
	public int delete(String id);
	
	public int update(Bk_User user);
	
	public Bk_User select(String id);
	
	public Bk_User selectByUserName(String username);
	
	public List<Bk_User> selectAll();
}
