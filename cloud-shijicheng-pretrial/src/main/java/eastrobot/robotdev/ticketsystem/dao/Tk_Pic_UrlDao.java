package eastrobot.robotdev.ticketsystem.dao;

import eastrobot.robotdev.ticketsystem.model.Tk_Pic_Url;

public interface Tk_Pic_UrlDao {
	
	public int insert(Tk_Pic_Url picUrl);

	public int delete(String pic_id);
	
	public int update(Tk_Pic_Url picUrl);
	
	public Tk_Pic_Url select(String pic_id);
}
