package eastrobot.robotdev.ticketsystem.dao;

import eastrobot.robotdev.ticketsystem.model.Bk_Operate;

public interface Bk_OperateDao {

	public int insert(Bk_Operate operate);
	
	public int delete(int operate_id);
	
	public int update(Bk_Operate operate);
	
	public Bk_Operate select(int operate_id);
}
