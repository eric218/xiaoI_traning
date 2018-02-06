package eastrobot.robotdev.ticketsystem.model;

public class Tk_Info {
	private String tk_id;
	private String tk_name;
	private String tk_tel;
	private String tk_content;
	private java.sql.Date tk_date;
	private java.sql.Time tk_time;
	private int tk_status;
	private int tk_fn_id;
	
	public String getTk_id() {
		return tk_id;
	}
	
	public void setTk_id(String tk_id) {
		this.tk_id = tk_id;
	}
	
	public String getTk_name() {
		return tk_name;
	}
	
	public void setTk_name(String tk_name) {
		this.tk_name = tk_name;
	}
	
	public String getTk_tel() {
		return tk_tel;
	}
	
	public void setTk_tel(String tk_tel) {
		this.tk_tel = tk_tel;
	}
	
	public String getTk_content() {
		return tk_content;
	}
	public void setTk_content(String tk_content) {
		this.tk_content = tk_content;
	}
	
	public int getTk_status() {
		return tk_status;
	}
	
	public void setTk_status(int tk_status) {
		this.tk_status = tk_status;
	}

	public java.sql.Date getTk_date() {
		return tk_date;
	}

	public void setTk_date(java.sql.Date tk_date) {
		this.tk_date = tk_date;
	}

	public java.sql.Time getTk_time() {
		return tk_time;
	}

	public void setTk_time(java.sql.Time tk_time) {
		this.tk_time = tk_time;
	}

	public int getTk_fn_id() {
		return tk_fn_id;
	}

	public void setTk_fn_id(int tk_fn_id) {
		this.tk_fn_id = tk_fn_id;
	}
}
