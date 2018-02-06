package eastrobot.robotdev.ticketsystem.dao;

import eastrobot.robotdev.ticketsystem.model.Login_Log;

public interface Login_LogDao {
	public int intsert(Login_Log log);
	
	public int delete(String login_id);
	
	public int update(Login_Log log);
	
	public Login_Log select(String login_id);
	
	public Login_Log selectAll();
}
