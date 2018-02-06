package eastrobot.robotdev.ticketsystem.service;

import javax.servlet.http.HttpSession;

public interface LoginService {
	/**
	 * 登录方法
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	public boolean login(String username,String password,HttpSession session); 
	
	/**
	 * 修改密码方法
	 * @param old_password
	 * @param new_password
	 * @return
	 * @throws Exception 
	 */
	public boolean changePassword(String old_password,String new_password,HttpSession session) throws Exception;

	/**
	 * 获取所有的user列表，需要做根据session的权限判断，判断是否是管理员，有没有添加删除用户的功能
	 * @param session
	 * @return
	 */
	public String getAllUsers(HttpSession session);
	
	/**
	 * 添加新用户的功能，需要先判断是否有权限
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean addNewUser(String username,String password) throws Exception;
	
	/**
	 * 删除用户的功能
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean deleteUser(String username) throws Exception;
	
	/**
	 * 管理员权限修改用户密码，不需要原密码
	 * @param username
	 * @param new_password
	 * @param verify_password
	 * @return
	 */
	public boolean changeUserPasswordWithoutOriginPassword(String username, String new_password, String verify_password);
	
	/**
	 * 管理员修改用户状态，正常为1，锁定为0
	 * @param username
	 * @return
	 */
	public boolean changeUserStatus(String username);

}
