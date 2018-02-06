package eastrobot.robotdev.ticketsystem.model;


public class Bk_User {
	private String id;
	private String username;
	private String password;
	private String salt;
	private int status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
