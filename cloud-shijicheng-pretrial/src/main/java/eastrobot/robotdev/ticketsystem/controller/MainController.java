package eastrobot.robotdev.ticketsystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import eastrobot.robotdev.ticketsystem.constant.SessionConstant;
import eastrobot.robotdev.ticketsystem.constant.UriConstant;
import eastrobot.robotdev.ticketsystem.service.LoginService;
import eastrobot.robotdev.ticketsystem.service.MainService;
import eastrobot.robotdev.ticketsystem.utils.StringUtils;

@Controller
public class MainController {
	private static Logger logger = LogManager.getLogger(MainController.class);
	@ResponseBody
	@RequestMapping(value = UriConstant.MAIN_PAGE)
	public ModelAndView mainPage(HttpSession session,HttpServletResponse response) throws IOException{
	    response.setDateHeader("Expires", 0);      
	    response.setHeader("Cache-Control", "no-cache");      
	    response.setHeader("Pragma", "no-cache");      
	    response.setDateHeader("Expires", 0);    
	    response.setHeader("Cache-Control", "no-cache");    
	    response.setHeader("Pragma", "no-cache"); 
		if(session.getAttribute(SessionConstant.SESSION_IS_LOGIN) == null ||
				session.getAttribute(SessionConstant.SESSION_IS_LOGIN).equals(false)){
			response.sendRedirect("login");
			return new ModelAndView("Login");
		}else{
			logger.info("-----main page visit-----"); 
			return new ModelAndView("Main");
		}
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_MAIN_PAGE_API)
	public ModelAndView getMainPageAPI(){
		logger.info("-----get main page visit-----");
		return new ModelAndView("function/main");				
	}
	
	/*@ResponseBody
	@RequestMapping(value = UriConstant.GET_ALL_APPLY)
	public ModelAndView getAllApply(){
		return new ModelAndView("function/allApply");
	}*/
	@ResponseBody
	@RequestMapping(value = UriConstant.REVIEW_LOG_PAGE_API)
	public ModelAndView getReviewLogPageAPI(){
		logger.info("-----get main page visit-----");
		return new ModelAndView("function/review_log");				
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_USER_SETTING_PAGE_API)
	public ModelAndView getUserSettingPageAPI(){
		logger.info("-----get user setting page visit-----");
		return new ModelAndView("function/usersettings/u_setting");				
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.DELETE_USER_PAGE_API)
	public ModelAndView getDeleteUserPageAPI(){
		logger.info("-----get user setting page visit-----");
		return new ModelAndView("function/usersettings/u_deleteuser");				
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.USER_LOGIN_LOG_PAGE_API)
	public ModelAndView getLoginLogPageAPI(){
		logger.info("-----get user setting page visit-----");
		return new ModelAndView("function/usersettings/u_loginlog");				
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.USER_SETTING_ADD_DELETE_USER)
	public ModelAndView getAddDeleteUserPage(){
		return new ModelAndView("function/usersettings/u_adddeleteuser");
	}
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_NAVBAR_API)
	public ModelAndView getNavBarAPI(){
		return new ModelAndView("function/navbar");	
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_SIDERBAR_PAGE_API)
	public ModelAndView getSiderBarAPI(){
		return new ModelAndView("function/siderbar");	
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.USER_SETTING_PAGE )
	public ModelAndView userSettingPage(){
		logger.info("-----userSetting page visit-----");
		return new ModelAndView("function/usersettings/u_setting");				
	}	
	
	@ResponseBody
	@RequestMapping(value = UriConstant.USER_SETTING_CHANGE_PASSWORD )
	public ModelAndView changePassword(){
		logger.info("-----userSetting page visit-----");
		return new ModelAndView("function/usersettings/u_setting");				
	}
	@ResponseBody
	@RequestMapping(value="/main/widgets")
	public ModelAndView widgetsPage(){
		logger.info("-----widget page visit-----");
		return new ModelAndView("function/widgets");				
	}
	
	@ResponseBody
	@RequestMapping(value="/main/getUserName.api")
	public String getUserNameAPI(HttpSession session){
		if(session.getAttribute(SessionConstant.SESSION_USER_NAME) != null)
			return session.getAttribute(SessionConstant.SESSION_USER_NAME).toString();
		else
			return "";
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.PERMISSION_SETTING_API)
	public ModelAndView getPermPageAPI(){
		return new ModelAndView("function/permission/perm_setting");
	}
	@Autowired
	private MainService mainService;
	@ResponseBody
	@RequestMapping(value="/main/initSilderBar.api")
	public String getInitSiderBarJsonAPI(HttpSession session){
		String jsonString = mainService.getInitJsonByPerm(session);
		System.out.println(jsonString);
		return jsonString;
		
	}
	
	@Autowired
	private LoginService loginService;
	
	@ResponseBody
	@RequestMapping(value = UriConstant.ADD_NEW_USER_API)
	public String addNewUserAPI(String username,String new_password,String verify_password,HttpSession session){
		//TODO 需要session判断是否有添加新用户的权限
		JSONObject json = new JSONObject();
		//验证		
		if(StringUtils.isBlank(username) || StringUtils.isBlank(new_password) || StringUtils.isBlank(verify_password)
				|| username.length() < 5 || new_password.length() < 5 || verify_password.length() < 5
				|| !new_password.equals(verify_password)){
			json.put("type", "failure");
			return json.toJSONString();
		}
		//验证通过
		try{
			loginService.addNewUser(username, new_password);
			json.put("type", "success");
		}catch(Exception e){
			json.put("type", "failure");
			return json.toJSONString();
		}
		
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.DELET_USER_API)
	public String deleteUserAPI(String username){
		//权限判断
		//执行业务逻辑
		JSONObject json = new JSONObject();
		try{
			if(loginService.deleteUser(username)){
				json.put("type", "success");
			}else{
				json.put("type", "failure");				
			}			
		}catch(Exception e){
			json.put("type", "failure");
		}
		return json.toJSONString();			
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.CHANGE_PASSWORD_ADMIN)
	public String adminChangePassword(String username, String new_password, String verify_password){
		//之前先做session验证
		JSONObject json = new JSONObject();
		if(StringUtils.isBlank(username) || StringUtils.isBlank(new_password) || StringUtils.isBlank(verify_password)
				|| username.length() < 5 || new_password.length() < 5 || verify_password.length() < 5
				|| !new_password.equals(verify_password)){
			json.put("type", "failure");
			return json.toJSONString();
		}
		//通过验证
		try{
			if(loginService.changeUserPasswordWithoutOriginPassword(username, new_password, verify_password)){
				json.put("type", "success");
			}else{
				json.put("type", "failure");
			}
		}catch(Exception e){
			json.put("type", "failure");
		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.CHANGE_USER_STATUS)
	public String changeUserStatus(String username){
		JSONObject json = new JSONObject();
		//还是拦截器先进行权限判断
		//然后验证表单有效性
		if(StringUtils.isBlank(username) || username.length() < 5){
			json.put("type", "failure");
			return json.toJSONString();
		}
		//业务逻辑
		try{
			if(loginService.changeUserStatus(username)){
				json.put("type", "success");
			}else{
				json.put("type", "failure");
			}
		}catch(Exception e){
			json.put("type", "failure");
		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_ALL_ROLES)
	public String getAllRoles(){
		//权限验证
		String json = null;
		try{
			json = mainService.getAllRoles();
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return "[]";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.SUBMIT_CHANGED_PERMS_API)
	public String submitChangedPermsAPI(String fnIds, String roleId){
		//权限验证在拦截器
		JSONObject json = new JSONObject();
		fnIds = fnIds.substring(1, fnIds.length()-1);
		try{
			if(mainService.submitChangedPrems(fnIds, roleId)){
				json.put("type", "success");
			}else{
				json.put("type", "failure");
			}
		}catch(Exception e){
			e.printStackTrace();
			json.put("type", "failure");
		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.CHANGE_ROLE_DESCRIPTION)
	public String changeRoleDescriptionAPI(String roleId, String new_description){
		JSONObject json = new JSONObject();
		try{
			if(mainService.changeRoleDescription(roleId, new_description)){
				json.put("type", "success");
			}else{
				json.put("type", "failure");
			}
		}catch(Exception e){
			e.printStackTrace();
			json.put("type", "failure");
		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.ADD_NEW_ROLE_API)
	public String addNewRoleAPI(String list, String new_roleName, String new_roleDescription){
		JSONObject json = new JSONObject();
		try{
			if(mainService.addNewRole(list.substring(1,list.length()-1), new_roleName, new_roleDescription)){
				json.put("type", "success");
			}else{
				json.put("type", "failure");
			}
		}catch(Exception e){
			e.printStackTrace();
			json.put("type", "failure");
		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.DELETE_ROLE_API)
	public String deleteRoleAPI(String roleId){
		JSONObject json = new JSONObject();
		if(StringUtils.isBlank(roleId)){
			json.put("type", "failure");
			return json.toJSONString();
		}
		try{
			if(mainService.deleteRole(roleId)){
				json.put("type", "success");
			}else{
				json.put("type", "failure");
			}
		}catch(Exception e){
			json.put("type", "failure");
			e.printStackTrace();
		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.ROLE_USER_BIND)
	public ModelAndView roleUserBindPage(){
		return new ModelAndView("function/permission/perm_userrolebind");
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_ALL_USER_AND_ROLE)
	public String getAllUserAndRoleAPI(){
		return mainService.getAllUserAndRole();
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.RECIEVE_USER_ROLE_BIND_API)
	public String userRoleBindAPI(String checkedRoleId, String userId){
		//之前在拦截器做权限判断
		JSONObject json = new JSONObject();
		if(StringUtils.isBlank(checkedRoleId) || StringUtils.isBlank(userId)){
			json.put("type", "failure");
			return json.toJSONString();
		}
		try{
			if(mainService.changeUserRole(checkedRoleId, userId)){
				json.put("type", "success");
			}else{
				json.put("type", "failure");
			}
		}catch(Exception e){
			e.printStackTrace();
			json.put("type", "failure");
		}
		return json.toJSONString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/main/apply_{classify}.api")
	public ModelAndView applyJsfwSzbjfwz(@PathVariable String classify,HttpServletResponse response){
		String[] classifys = classify.split("_");
		//uri:apply_jsfw_szbjfw.api classify = ["jsfw","szbjfwz"]

		//设置不使用缓存
	    response.setDateHeader("Expires", 0);      
	    response.setHeader("Cache-Control", "no-cache");      
	    response.setHeader("Pragma", "no-cache");      
	    response.setDateHeader("Expires", 0);    
	    response.setHeader("Cache-Control", "no-cache");    
	    response.setHeader("Pragma", "no-cache");
	    //判断分类
		if(classifys.length == 1){
			//只有一级分类，也就是大分类
			return new ModelAndView("function/apply/"+classifys[0]);
		}else{
			//两级分类
			return new ModelAndView("function/apply/"+classifys[0]+"/"+classifys[1]);
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/main/job_{classify}.api")
	public ModelAndView jopApplyAndSearch(@PathVariable String classify,HttpServletResponse response){
		String[] classifys = classify.split("_");
		//设置不使用缓存
	    response.setDateHeader("Expires", 0);      
	    response.setHeader("Cache-Control", "no-cache");      
	    response.setHeader("Pragma", "no-cache");      
	    response.setDateHeader("Expires", 0);    
	    response.setHeader("Cache-Control", "no-cache");    
	    response.setHeader("Pragma", "no-cache");
	    //判断分类
	    return new ModelAndView("function/job/"+classifys[0]+"/"+classifys[1]);
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_APPLY_DATA)
	public String getApplyData(String fnId){
		//权限验证在拦截器进行
		List<Integer> list = new ArrayList<Integer>();
		String[] fns = fnId.split(",");
		for(int i = 0 ; i < fns.length ; i++){
			if(fns[i] != null && fns[i].trim().length() != 0)
				list.add(Integer.parseInt(fns[i]));
		}
		String responseJson = null;
		try{
			responseJson = mainService.getApplydata(list);
		}catch(Exception e){
			return new JSONArray().toJSONString();
		}
		return responseJson;
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.RECIEVE_TRIAL_RESULT)
	public String recieveTrialResult(String tk_id, String trial_result){
		//接收审核结果，并处理，处理结果返回给前端
		
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_ALL_APPLY_DATA)
	public String getAllApplyDataApi(HttpSession session){
		//获取该用户的userId
		String userId = (String)session.getAttribute(SessionConstant.SESSION_USER_ID);
		//获取该用户的审核权限信息
		List<Integer> applyIds = mainService.getapplyIds(userId);
		String responseJson = null;
		try{
			responseJson = mainService.getApplydata(applyIds);
		}catch(Exception e){
			return new JSONArray().toJSONString();
		}
		return responseJson;

	}
	@ResponseBody
	@RequestMapping(value = "/main/recieveRefuseReason.api")
	public String recieveRefuseReason(String text, String tk_id){
		if(StringUtils.isBlank(text))
			text = "默认拒绝短信模板";
		JSONObject resp = new JSONObject();
		//非空判断
		if(StringUtils.isBlank(tk_id)){
			resp.put("type", "failure");
			resp.put("info", "提交失败，缺少必填参数！");
			return resp.toJSONString();
		}
		if(!(tk_id.length()==16))
		{
			resp.put("type", "failure");
			resp.put("info", "提交失败，非法的单号");
			return resp.toJSONString();
		}
		
		String str = mainService.refuseApply(tk_id, text);		
		
		return str;
	}
	
	@ResponseBody
	@RequestMapping(value = "/main/throughPretrial.api")
	public String throughPretrial(String tk_id){
		JSONObject resp = new JSONObject();
		//非空判断
		if(StringUtils.isBlank(tk_id)){
			resp.put("type", "failure");
			resp.put("info", "提交失败，缺少必填参数！");
			return resp.toJSONString();
		}
		if(!(tk_id.length()==16))
		{
			resp.put("type", "failure");
			resp.put("info", "提交失败，非法的单号");
			return resp.toJSONString();
		}
		
		String str = mainService.throughApply(tk_id);
		
		return str;
	}

	@ResponseBody
	@RequestMapping(value = "main/getAppliedData.api")
	public String getAppliedDataApi(HttpSession session){
		//获取该用户的userId
		String userId = (String)session.getAttribute(SessionConstant.SESSION_USER_ID);
		//获取该用户的审核权限信息
		List<Integer> applyIds = mainService.getapplyIds(userId);
		String responseJson = null;
		try{
			responseJson = mainService.getAppliedData(applyIds);
		}catch(Exception e){
			return new JSONArray().toJSONString();
		}
		return responseJson;

	}
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_OVERVIEW_DATA)
	public String getOverviewDataApi(){
		String str = mainService.getOverviewData();
		return str;
	}
}
