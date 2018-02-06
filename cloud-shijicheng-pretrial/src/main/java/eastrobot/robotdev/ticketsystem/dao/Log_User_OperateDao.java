package eastrobot.robotdev.ticketsystem.dao;

import eastrobot.robotdev.ticketsystem.model.Log_User_Operate;

public interface Log_User_OperateDao {
	
	public int insert(Log_User_Operate user_Operate);
	
	public int delete(String id);
	
	public int update(Log_User_Operate user_Operate);
	
	public Log_User_Operate select(String id);
}
