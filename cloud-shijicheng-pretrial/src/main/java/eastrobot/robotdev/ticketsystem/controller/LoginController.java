package eastrobot.robotdev.ticketsystem.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import eastrobot.robotdev.ticketsystem.constant.SessionConstant;
import eastrobot.robotdev.ticketsystem.constant.UriConstant;
import eastrobot.robotdev.ticketsystem.service.LoginService;
import eastrobot.robotdev.ticketsystem.service.MainService;
import eastrobot.robotdev.ticketsystem.utils.StringUtils;

@Controller
public class LoginController {
	private static Logger logger = LogManager.getLogger(LoginController.class);
	/**
	 * 返回登录页面视图
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = UriConstant.LOGIN_PAGE)
	public ModelAndView login(HttpSession session,HttpServletResponse response) throws IOException{
	    response.setDateHeader("Expires", 0);      
	    response.setHeader("Cache-Control", "no-cache");      
	    response.setHeader("Pragma", "no-cache");      
	    response.setDateHeader("Expires", 0);    
	    response.setHeader("Cache-Control", "no-cache");    
	    response.setHeader("Pragma", "no-cache"); 
		if(session.getAttribute(SessionConstant.SESSION_IS_LOGIN) != null &&
				session.getAttribute(SessionConstant.SESSION_IS_LOGIN).equals(true)){
			response.sendRedirect("main");
			return new ModelAndView("Main");
		}else{
			logger.info("-----login page visit-----"); 
			return new ModelAndView("Login");
		}			
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.ROOT_URI)
	public void root(HttpServletResponse response ,HttpSession session){
		try{
		if(session.getAttribute(SessionConstant.SESSION_IS_LOGIN) == null
				|| session.getAttribute(SessionConstant.SESSION_IS_LOGIN).equals(false))
			response.sendRedirect("login");
		else
			response.sendRedirect("main");
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
	}
	
	@Autowired
	private LoginService loginService;
	/**
	 * login接口
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = UriConstant.LOGIN_API)
	public String checkLogin(String username,String password,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		logger.info("-----user login-----");
		String path = request.getContextPath();		
	    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		JSONObject json = new JSONObject();
		//需要验证，避免被绕过前端直接请求接口
		if(username.length() <= 4 || password.length() <= 4){
			json.put("type", "failure");
			json.put("reason", "登录失败：无效的用户名或密码");
			return json.toJSONString();
		}
		
		if(loginService.login(username, password, session)){
			//登录成功
			json.put("type", "success");
			json.put("url", basePath+UriConstant.MAIN_PAGE);
			//Cookie cookie = new Cookie("JSESSION",session.getId());
			//response.addCookie(cookie);
		}else{
			json.put("type", "failure");
			json.put("reason", "登录失败：错误的用户名或密码");
		}
		System.out.println(json.toJSONString());
		return json.toJSONString();
	}
	/**
	 * 登出API
	 * @param session
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping(value = UriConstant.LOGOUT_API)
	public void logout(HttpSession session,HttpServletRequest request,HttpServletResponse response){
		String path = request.getContextPath();		
	    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		if(session.getAttribute(SessionConstant.SESSION_IS_LOGIN) != null &&
				session.getAttribute(SessionConstant.SESSION_IS_LOGIN).equals(true)){
			//登录状态下
			session.setAttribute(SessionConstant.SESSION_IS_LOGIN, false);
			session.removeAttribute(SessionConstant.SESSION_IS_LOGIN);
		}
		//重定向
		try {
			response.sendRedirect(basePath + UriConstant.LOGIN_PAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.CHANGE_USER_PASSWORD_API)
	public String changePasswordAPI(String old_password, String new_password, String verify_password, HttpSession session){
		logger.info("-----user change password-----");
		JSONObject json = new JSONObject();
		//未登录不能访问接口由拦截器完成，所以只用判断数据有效性,这里判断非空，长度不小于5以及两次输入密码相等
		if(StringUtils.isBlank(old_password) || StringUtils.isBlank(new_password) || StringUtils.isBlank(verify_password)
				|| old_password.length() <= 4 || new_password.length() <= 4 || verify_password.length() <= 4
				|| !new_password.equals(verify_password)){
			json.put("type", "failure");
			json.put("info", "Illegal Parameters");
			//直接返回，不执行数据库操作
			return json.toJSONString();
		}
		
		//验证数据有效后执行数据库操作
		try {
			if(loginService.changePassword(old_password, new_password, session)){
				//修改成功
				json.put("type", "success");
				json.put("info", "修改成功");
			}else{
				//修改失败
				json.put("type", "failure");
				json.put("info", "修改失败，请核实输入信息!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_ALL_USERS_API)
	public String getAllUsers(HttpSession session){
		String usersJson = loginService.getAllUsers(session);
		return usersJson;
	}
	@Autowired
	private MainService mainService;
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_ALL_FN_TREE)
	public String getAllFnTree(String roleId){
		//需要验证是否有权限
		String userJson = mainService.getAllFnTree(roleId);
		return userJson;
	}
}
