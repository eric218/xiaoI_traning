package eastrobot.robotdev.ticketsystem.model;

public class Log_User_Operate {
	private String id;
	private String user_id;
	private java.sql.Date operate_time;
	private int operate_id;
	private String tk_id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public java.sql.Date getDatetime() {
		return operate_time;
	}
	
	public void setDatetime(java.sql.Date operate_time) {
		this.operate_time = operate_time;
	}
	
	public int getOperate_id() {
		return operate_id;
	}
	
	public void setOperate_id(int operate_id) {
		this.operate_id = operate_id;
	}
	
	public String getTk_id() {
		return tk_id;
	}
	
	public void setTk_id(String tk_id) {
		this.tk_id = tk_id;
	}
}
