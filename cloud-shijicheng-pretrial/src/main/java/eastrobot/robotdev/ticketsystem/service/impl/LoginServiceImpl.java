package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import eastrobot.robotdev.ticketsystem.constant.SessionConstant;
import eastrobot.robotdev.ticketsystem.dao.Bk_RoleDao;
import eastrobot.robotdev.ticketsystem.dao.Bk_UserDao;
import eastrobot.robotdev.ticketsystem.dao.Bk_User_RoleDao;
import eastrobot.robotdev.ticketsystem.model.Bk_Role;
import eastrobot.robotdev.ticketsystem.model.Bk_User;
import eastrobot.robotdev.ticketsystem.model.Bk_User_Role;
import eastrobot.robotdev.ticketsystem.service.LoginService;
import eastrobot.robotdev.ticketsystem.utils.Md5Utils;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private Bk_UserDao userDao;

	public boolean login(String username, String password,HttpSession session) {
		Bk_User bk_User = userDao.selectByUserName(username);
		if(bk_User == null || bk_User.getStatus() == 0)
			return false;
		
		String salt = bk_User.getSalt();
		String correctPassword = bk_User.getPassword();
		String verifyPassword = Md5Utils.GetMD5Code(password+salt);
		if(correctPassword.equals(verifyPassword)){
			session.setAttribute(SessionConstant.SESSION_IS_LOGIN, true);
			session.setAttribute(SessionConstant.SESSION_USER_ID, bk_User.getId());
			session.setAttribute(SessionConstant.SESSION_USER_NAME,bk_User.getUsername());
			return true;
		}else{
			return false;
		}		
	}
	@Transactional
	public boolean changePassword(String old_password, String new_password, HttpSession session) throws Exception {
		//session中如果未登录或者没有user_id则直接放回修改失败
		if(session.getAttribute(SessionConstant.SESSION_IS_LOGIN) == null 
				|| session.getAttribute(SessionConstant.SESSION_USER_ID) == null
				|| session.getAttribute(SessionConstant.SESSION_IS_LOGIN).equals(false)){
			return false;
		}
		Bk_User bk_User = userDao.select(session.getAttribute(SessionConstant.SESSION_USER_ID).toString());
		if(bk_User == null || bk_User.getStatus() == 0 ){
			return false;
		}else{
			String salt = bk_User.getSalt();
			String password = bk_User.getPassword();
			if(password.equals(Md5Utils.GetMD5Code(old_password + salt))){
				//输入的密码和数据库中的一致，可以进行下一步的修改
				bk_User.setPassword(Md5Utils.GetMD5Code(new_password + salt));
				userDao.update(bk_User);
				return true;
			}else{
				return false;
			}
		}
	}
	
	@Autowired
	private Bk_User_RoleDao bk_User_RoleDao;
	@Autowired
	private Bk_RoleDao bk_RoleDao;
	
	public String getAllUsers(HttpSession session){
		//TODO session需要用来判断权限是否足够，要有添加删除用户的功能
		JSONArray array = new JSONArray();
		List<Bk_User> users = null;
		users = userDao.selectAll();
		for(Bk_User user : users){
			JSONObject json = new JSONObject();
			json.put("username", user.getUsername());
			String id = user.getId();
			if(bk_User_RoleDao.select(id) != null){
				String roleId = bk_User_RoleDao.select(id).getRole_id();
				String role = bk_RoleDao.select(roleId).getName();
				json.put("role", role);
			}else{
				json.put("role", "无");
			}
			json.put("status", String.valueOf(user.getStatus()));
			array.add(json);
		}
		String jsonStr = array.toJSONString();
		return jsonStr;
	}
	
	@Transactional
	@Override
	public boolean addNewUser(String username, String password) throws Exception {
		//验证是否有同名
		Bk_User user = userDao.selectByUserName(username);
		if(user != null)
			//有同名
			return false;
		//插user表
		Bk_User newUser = new Bk_User();
		newUser.setUsername(username);
		String id = UUID.randomUUID().toString().replace("-","");
		newUser.setId(id);
		String salt = UUID.randomUUID().toString().replace("-","");
		newUser.setSalt(salt);
		newUser.setStatus(1);
		newUser.setPassword(Md5Utils.GetMD5Code(password+salt));
		int i = userDao.insert(newUser);
		
		//获取空权限roleid
		Bk_Role role = bk_RoleDao.selectByName("无");
		String roleId = role.getId();
		
		//绑定权限
		Bk_User_Role userRole = new Bk_User_Role();
		userRole.setUser_id(id);
		userRole.setRole_id(roleId);
		bk_User_RoleDao.insert(userRole);
		
		if(i == 1)
			return true;
		else
			return false;
	}
	
	@Transactional
	@Override
	public boolean deleteUser(String username) throws Exception {
		//先查询数据库是否有对应的user
		Bk_User user = userDao.selectByUserName(username);
		if(user == null)
			return false;
		//有对应的user 
		String id = user.getId();		
		//删除user
		userDao.delete(id);
		//删除userRole
		bk_User_RoleDao.delete(id);
		return true;
	}
	
	@Transactional
	@Override
	public boolean changeUserPasswordWithoutOriginPassword(String username,
			String new_password, String verify_password) {
		if(!new_password.endsWith(verify_password))
			return false;
		//先查询数据库是否有对应的user
		Bk_User user = userDao.selectByUserName(username);
		if(user == null)
			return false;
		//有对应的user拿出salt
		String salt = user.getSalt();
		//用新密码和salt生成数据库新密码
		String password = Md5Utils.GetMD5Code(new_password + salt);
		user.setPassword(password);
		//更新
		userDao.update(user);
		return true;
	}
	@Override
	public boolean changeUserStatus(String username) {
		if(username.length() < 5)
			return false;
		//先查询数据库是否有对应的user
		Bk_User user = userDao.selectByUserName(username);
		if(user == null)
			return false;
		//有对应的user则修改对应的status
		if(user.getStatus() == 0){
			user.setStatus(1);
		}else{
			user.setStatus(0);
		}
		userDao.update(user);
		return true;
	}
	

}
