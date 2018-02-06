package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;

import eastrobot.robotdev.ticketsystem.model.Bk_Fn;

public interface Bk_FnDao {
	
	public int insert(Bk_Fn fn);
	
	public int delete(int id);
	
	public int update(Bk_Fn fn);
	
	public Bk_Fn select(int id);
	
	public List<Bk_Fn> selectAll();
}
