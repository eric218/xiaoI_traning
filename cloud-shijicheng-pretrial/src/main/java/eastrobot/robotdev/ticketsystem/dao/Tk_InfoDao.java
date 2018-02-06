package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import eastrobot.robotdev.ticketsystem.model.Tk_Info;

public interface Tk_InfoDao {
	
	public int insert(Tk_Info info);
	
	public int delete(String tk_id);
	
	public int update(Tk_Info info);
	
	public Tk_Info select(String tk_id);
	
	public List<Tk_Info> selectApplyByFnId(List<Integer> list);
	
	public int selectCountByStatus(@Param("tk_status")Integer tk_status,@Param("tk_date")String date);

	public int selectDistinctTel();
}
