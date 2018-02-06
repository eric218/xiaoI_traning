package eastrobot.robotdev.ticketsystem.model;

public class Login_Log {
	private String login_id;
	private String login_userid;
	private java.sql.Time login_time;
	private java.sql.Date login_date;
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getLogin_userid() {
		return login_userid;
	}
	public void setLogin_userid(String login_userid) {
		this.login_userid = login_userid;
	}
	public java.sql.Time getLogin_time() {
		return login_time;
	}
	public void setLogin_time(java.sql.Time login_time) {
		this.login_time = login_time;
	}
	public java.sql.Date getLogin_date() {
		return login_date;
	}
	public void setLogin_date(java.sql.Date login_date) {
		this.login_date = login_date;
	}
}
