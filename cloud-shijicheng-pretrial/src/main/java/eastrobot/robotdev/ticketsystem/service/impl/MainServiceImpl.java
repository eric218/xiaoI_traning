package eastrobot.robotdev.ticketsystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import eastrobot.robotdev.ticketsystem.constant.SessionConstant;
import eastrobot.robotdev.ticketsystem.dao.Bk_FnDao;
import eastrobot.robotdev.ticketsystem.dao.Bk_RoleDao;
import eastrobot.robotdev.ticketsystem.dao.Bk_UserDao;
import eastrobot.robotdev.ticketsystem.dao.Bk_User_RoleDao;
import eastrobot.robotdev.ticketsystem.dao.Tk_InfoDao;
import eastrobot.robotdev.ticketsystem.model.Bk_Fn;
import eastrobot.robotdev.ticketsystem.model.Bk_Role;
import eastrobot.robotdev.ticketsystem.model.Bk_User;
import eastrobot.robotdev.ticketsystem.model.Bk_User_Role;
import eastrobot.robotdev.ticketsystem.model.Tk_Info;
import eastrobot.robotdev.ticketsystem.service.MainService;
import eastrobot.robotdev.ticketsystem.utils.SendMessageUtil;

@Service
public class MainServiceImpl implements MainService ,InitializingBean{
	@Autowired
	private Bk_FnDao bk_FnDao;
	@Autowired
	private Bk_RoleDao bk_RoleDao;
	@Autowired
	private Bk_User_RoleDao bk_User_RoleDao;
	@Autowired
	private Bk_UserDao bk_UserDao;
	//所有的功能列表
	private static List<Bk_Fn> fn_List = null;

	@Override
	public String getInitJsonByPerm(HttpSession session) {		
		String userId = null;
		if(session.getAttribute(SessionConstant.SESSION_USER_ID) == null)
			return "";	
		//获取userid，roleid和role bean
		userId = session.getAttribute(SessionConstant.SESSION_USER_ID).toString();
		Bk_User_Role userRole = bk_User_RoleDao.select(userId);
		String roleId = userRole.getRole_id();
		Bk_Role role = bk_RoleDao.select(roleId);
		
		//将权限字符串切成单个，每个1/0代表有或无权限
		List<String> perms = new ArrayList<String>();
		for(String s : role.getPermission().split("")){
			if(!StringUtils.isBlank(s))
				perms.add(s);
		}
		
		//生成用户的功能列表
		List<Bk_Fn> userFnPerms = new ArrayList<Bk_Fn>();
		for(int i = 0 ; i < perms.size() ; i++){
			if(perms.get(i).equals("1")){
				Bk_Fn tempfn = new Bk_Fn(); 
				tempfn.setChild(fn_List.get(i).getChild());
				tempfn.setDescription(fn_List.get(i).getDescription());
				tempfn.setIcon(fn_List.get(i).getIcon());
				tempfn.setId(fn_List.get(i).getId());
				tempfn.setName(fn_List.get(i).getName());
				tempfn.setParent_id(fn_List.get(i).getParent_id());
				tempfn.setUrl(fn_List.get(i).getUrl());
				userFnPerms.add(tempfn);
			}
		}
		if(userFnPerms.size() == 0)
			return "[]";
		Bk_Fn fnTree = generateFnTree(userFnPerms, 0);
		
		String initJson = JSONObject.toJSON(fnTree.getChild()).toString();
		//生成功能树，初始化参数是完整的功能列表和父节点id

		return initJson;
	}
	
	/**
	 * 从list转成tree
	 * 
	 * @param fns
	 * @param parent_id
	 * @return
	 */
	private Bk_Fn generateFnTree(List<Bk_Fn> fns, int parent_id){
		//以功能列表为初始化数据，parent_id为根，生成Bk_Fn树
		Bk_Fn root = null;
		//新建一个List<Bk_Fn>用来存储childs
		List<Bk_Fn> childs = new ArrayList<Bk_Fn>();
		for(Bk_Fn fn : fns){
			if(fn.getId() == parent_id){
				//当前节点，添加到root中
				root = fn;
			}
			if(fn.getParent_id() == parent_id){
				//直属子节点，添加到childs集合中
				childs.add(fn);
			}
		}
		//遍历完后判定childs子集
		if(childs.size() == 0){
			//说明这个已经是最后的一层，没有childs了，因此应该返回parent_id的对象
			return root;
		}else{
			//childs子集不为空的话说明还不是最后一层，还有child，应该继续递归
			//先遍历所有子集递归查找
			for(Bk_Fn child : childs){
				child = generateFnTree(fns,child.getId());
			}
			//再把当前层的直系子集放入子集属性中
			root.setChild(childs);
			return root;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
			fn_List = bk_FnDao.selectAll();
	}

	@Override
	public String getAllRoles() {
		//进入方法前要对权限进行验证
		List<Bk_Role> roles = bk_RoleDao.selectAll();
		if(roles.size() == 0)
			return "[]";
		//返回json
		JSONArray array = new JSONArray();
		for(Bk_Role role : roles){
			JSONObject json = new JSONObject();
			json.put("name", role.getName());
			json.put("perm", role.getPermission());
			json.put("desc", role.getDescription());
			json.put("id", role.getId());
			array.add(json);
		}
		String jsonStr = array.toJSONString();
		
		return jsonStr;
	}

	@Override
	public String getAllFnTree(String roleId) {
		if(StringUtils.isBlank(roleId)){
			//roleId为空
			JSONArray array = new JSONArray();
			for(Bk_Fn fn : fn_List){
				JSONObject obj = new JSONObject();
				obj.put("id", fn.getId());
				obj.put("pid", fn.getParent_id());
				obj.put("name", fn.getName());
				obj.put("checked", false);				
				array.add(obj);
			}
			return array.toJSONString();
		}else{
			//获取userid，roleid和role bean
			Bk_Role role = bk_RoleDao.select(roleId);
		
			//将权限字符串切成单个，每个1/0代表有或无权限
			List<String> perms = new ArrayList<String>();
			for(String s : role.getPermission().split("")){
				if(!StringUtils.isBlank(s))
					perms.add(s);
				}
		
			JSONArray array = new JSONArray();
		
			for(Bk_Fn fn : fn_List){
				JSONObject obj = new JSONObject();
				obj.put("id", fn.getId());
				obj.put("pid", fn.getParent_id());
				obj.put("name", fn.getName());
				if(perms.get(fn.getId()).equals("1")){
					//有权限
					obj.put("checked", true);
				}else{
					//无权限
					obj.put("checked", false);
				}
				array.add(obj);
			}
			return array.toJSONString();
		}
		
	}
	
	@Transactional
	@Override
	public boolean submitChangedPrems(String fnIds, String roleId) {
		if(StringUtils.isBlank(fnIds))
			fnIds = "0";
		String[] fnsId = fnIds.split(",");
		System.out.println("fnIds:"+fnIds);
		List<Integer> ids = new ArrayList<Integer>();
		for(String fnId : fnsId){
			ids.add(Integer.parseInt(fnId));
		}
		StringBuffer sb = new StringBuffer();
		//先获取function的数量
		int fnSize = fn_List.size();
		System.out.println("fn_List.size():"+fnSize);
		for(int i = 0 ; i < fnSize ; i++){
			int flag = 0;
			for(Integer id : ids){
				if(id == i){
					flag = 1;
					sb.append("1");
					break;
				}
			}
			if(flag == 0)
				sb.append("0");
		}
		
		Bk_Role role = bk_RoleDao.select(roleId);
		if(role == null)
			return false;
		
		role.setPermission(sb.toString());
		bk_RoleDao.update(role);
		return true;
	}

	@Override
	public boolean changeRoleDescription(String roleId, String new_description) {
		//之前需要做权限验证
		Bk_Role role = bk_RoleDao.select(roleId);
		if(role == null)
			return false;
		
		role.setDescription(new_description);
		bk_RoleDao.update(role);
		return true;
	}

	@Transactional
	@Override
	public boolean addNewRole(String fnIds, String new_roleName,
			String new_description) {
		//之前需要做权限验证
		//把fnIds整理成数据库中存储的形式
		System.out.println("fnIds:"+fnIds+"|new_roleName:"+new_roleName+"|new_description:"+new_description);
		
		//验证重名
		Bk_Role role = bk_RoleDao.selectByName(new_roleName);
		if(role != null)
			return false;
		
		//如果什么权限都没选
		if(StringUtils.isBlank(fnIds))
			fnIds = "0";
		
		//把fnId整理成数据库中的结构
		String[] fnsId = fnIds.split(",");
		List<Integer> ids = new ArrayList<Integer>();
		for(String fnId : fnsId){
			ids.add(Integer.parseInt(fnId));
		}
		StringBuffer sb = new StringBuffer();
		//先获取function的数量
		int fnSize = fn_List.size();
		System.out.println("fn_List.size():"+fnSize);
		for(int i = 0 ; i < fnSize ; i++){
			int flag = 0;
			for(Integer id : ids){
				if(id == i){
					flag = 1;
					sb.append("1");
					break;
				}
			}
			if(flag == 0)
				sb.append("0");
		}
		//创建一个新的Bk_Role对象
		Bk_Role new_role = new Bk_Role();
		new_role.setId(UUID.randomUUID().toString().replace("-", ""));
		new_role.setName(new_roleName);
		new_role.setDescription(new_description);
		new_role.setPermission(sb.toString());
		//数据库更新
		bk_RoleDao.insert(new_role);
		return true;
	}

	@Override
	public boolean deleteRole(String roleId) {
		//需要做权限验证
		//验证roleID有效
		Bk_Role role = bk_RoleDao.select(roleId);
		if(role == null)
			return false;
		//验证是否有用户绑定
		List<Bk_User_Role> userRoles = bk_User_RoleDao.selectByRoleId(roleId);
		if(userRoles != null && userRoles.size() > 0)
			return false;
		//删除角色
		bk_RoleDao.delete(roleId);
		return true;
	}

	@Override
	public String getAllUserAndRole() {
		//进入之前需要进行权限验证
		JSONArray array = new JSONArray();
		List<Bk_User> allUsers = bk_UserDao.selectAll();
		for(Bk_User user : allUsers){
			JSONObject json = new JSONObject();
			json.put("username", user.getUsername());
			json.put("userId", user.getId());
			String roleId = bk_User_RoleDao.select(user.getId()).getRole_id();
			Bk_Role role = bk_RoleDao.select(roleId);
			json.put("roleName", role.getName());
			json.put("roleDesc", role.getDescription());
			array.add(json);			
		}
		
		return array.toJSONString();
	}

	@Override
	public boolean changeUserRole(String checkedRoleId, String userId) {
		Bk_User_Role userRole = bk_User_RoleDao.select(userId);
		if(userRole == null)
			return false;
		Bk_Role role = bk_RoleDao.select(checkedRoleId);
		if(role == null)
			return false;
		
		userRole.setRole_id(checkedRoleId);
		bk_User_RoleDao.update(userRole);
		return true;
	}

	@Autowired
	private Tk_InfoDao tk_InfoDao;
	@Override
	public String getApplydata(List<Integer> fnId) {
		List<Tk_Info> tk_infos = tk_InfoDao.selectApplyByFnId(fnId);
		//组织返回json
		JSONArray array = new JSONArray();
		if(tk_infos == null || tk_infos.size() == 0){
			return array.toJSONString();
		}
		for(Tk_Info tk_info : tk_infos){
			if(tk_info.getTk_status() == 0){
				JSONObject jsonObj = new JSONObject();
				//工单id，提交人姓名，联系电话
				jsonObj.put("tk_id", tk_info.getTk_id());
				jsonObj.put("tk_name", tk_info.getTk_name());
				jsonObj.put("tk_telNum", tk_info.getTk_tel());
				//加入一个分类
				jsonObj.put("tk_classify", bk_FnDao.select(tk_info.getTk_fn_id()).getName());
				//提交时间
				String datetime = tk_info.getTk_date().toString()+" "+tk_info.getTk_time().toString();
				jsonObj.put("tk_datetime", datetime);
				jsonObj.put("status",getStatus(tk_info.getTk_status()));
				jsonObj.put("tk_data", JSONArray.parse(tk_info.getTk_content()));
				array.add(jsonObj);
			}
		}
		return array.toJSONString();
	}

	private String getStatus(int i){
		if(i == 0){
			return "待审";
		}else if(i == 1){
			return "在审";
		}else if(i == 2){
			return "审核通过";
		}else if(i == 3){
			return "审核未通过";
		}else{
			return "illegal status";
		}
	}

	@Override
	public List<Integer> getapplyIds(String userId) {
		String roleId = bk_User_RoleDao.select(userId).getRole_id();
		String perms = bk_RoleDao.select(roleId).getPermission();
		//权限列表
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < perms.length() ; i++){
			if(perms.substring(i,i+1).equals("1") && String.valueOf(fn_List.get(i).getParent_id()).equals("2"))
				list.add(i);
		}		
		return list;
	}

	@Autowired
	private SendMessageUtil messageUtil;
	@Override
	public String refuseApply(String tk_id, String reason) {
		JSONObject resp = new JSONObject();
		Tk_Info tk = tk_InfoDao.select(tk_id);
		if(tk == null){
			resp.put("type", "failure");
			resp.put("info", "查无此单号！");
			return resp.toJSONString();
		}
		//判断表单状态
		if(tk.getTk_status() == 3){
			resp.put("type", "failure");
			resp.put("info", "该申请已被设置为拒绝！");
			return resp.toJSONString();
		}
		//改变状态
		tk.setTk_status(3);//3表示拒绝
		tk_InfoDao.update(tk);
		//发短信
		messageUtil.sendMessage(tk.getTk_tel(), tk.getTk_name(),tk.getTk_id(),tk.getTk_fn_id(),reason);
		resp.put("type", "success");
		resp.put("info", "操作成功，已拒绝此申请，原因已通过短信发送给申请人！");
		return resp.toJSONString();
	}

	@Override
	public String throughApply(String tk_id) {
		JSONObject resp = new JSONObject();
		Tk_Info tk = tk_InfoDao.select(tk_id);
		if(tk == null){
			resp.put("type", "failure");
			resp.put("info", "查无此单号！");
			return resp.toJSONString();
		}
		if(tk.getTk_status() == 2){
			resp.put("type", "failure");
			resp.put("info", "该申请已通过审核！");
			return resp.toJSONString();
		}
		//改变状态
		tk.setTk_status(2);//2表示通过
		tk_InfoDao.update(tk);
		//发短信
		messageUtil.sendMessage(tk.getTk_tel(),tk.getTk_name(),tk.getTk_id(),tk.getTk_fn_id(),"默认通过短信模板");
		resp.put("type", "success");
		resp.put("info", "操作成功，此申请已通过，过审短信已发送给申请人！");
		return resp.toJSONString();
	}

	@Override
	public String getAppliedData(List<Integer> fnId) {
		List<Tk_Info> tk_infos = tk_InfoDao.selectApplyByFnId(fnId);
		//组织返回json
		JSONArray array = new JSONArray();
		if(tk_infos == null || tk_infos.size() == 0){
			return array.toJSONString();
		}
		for(Tk_Info tk_info : tk_infos){
			if(tk_info.getTk_status() != 0){
				JSONObject jsonObj = new JSONObject();
				//工单id，提交人姓名，联系电话
				jsonObj.put("tk_id", tk_info.getTk_id());
				jsonObj.put("tk_name", tk_info.getTk_name());
				jsonObj.put("tk_telNum", tk_info.getTk_tel());
				//加入一个分类
				jsonObj.put("tk_classify", bk_FnDao.select(tk_info.getTk_fn_id()).getName());
				//提交时间
				String datetime = tk_info.getTk_date().toString()+" "+tk_info.getTk_time().toString();
				jsonObj.put("tk_datetime", datetime);
				jsonObj.put("status",getStatus(tk_info.getTk_status()));
				jsonObj.put("tk_data", JSONArray.parse(tk_info.getTk_content()));
				array.add(jsonObj);
			}
		}
		return array.toJSONString();
	}

	@Override
	public String getOverviewData() {
		JSONObject obj = new JSONObject();
		//获取未审核工单数
		int apllyCount = tk_InfoDao.selectCountByStatus(0,null);
		obj.put("apllyCount", apllyCount);
		//获取今日新增
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date = df.format(new Date(System.currentTimeMillis()));
		int todayNew = tk_InfoDao.selectCountByStatus(0,date);
		obj.put("todayNew", todayNew);
		//获取总用户数
		int userCount = tk_InfoDao.selectDistinctTel();
		obj.put("userCount", userCount);
		//获取工单总数
		int allApplyCount = tk_InfoDao.selectCountByStatus(null,null);
		obj.put("allApplyCount", allApplyCount);
		
		return obj.toJSONString();
	}

}
