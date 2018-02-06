package eastrobot.robotdev.ticketsystem.service;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface MainService {
	/**
	 * 根据权限获取初始化页面的json
	 */
	public String getInitJsonByPerm(HttpSession session);
	
	/**
	 * 获取所有角色的方法
	 * @return
	 */
	public String getAllRoles();
	
	/**
	 * 获取整个功能树结构，用于权限管理中勾选功能
	 * @return
	 */
	public String getAllFnTree(String userId);
	
	/**
	 * 将前端提交的权限修改存到数据库中
	 * @param perms
	 * @param roleId
	 * @return
	 */
	public boolean submitChangedPrems(String fnIds, String roleId);
	
	/**
	 * 修改角色描述
	 * @param roleId
	 * @param new_description
	 * @return
	 */
	public boolean changeRoleDescription(String roleId, String new_description);
	
	/**
	 * 添加新角色
	 * @param fnIds
	 * @param new_roleName
	 * @param new_description
	 * @return
	 */
	public boolean addNewRole(String fnIds, String new_roleName, String new_description);
	
	/**
	 * 删除角色
	 * @param roleId
	 * @return
	 */
	public boolean deleteRole(String roleId);
	
	/**
	 * 获取所有的用户信息和角色信息表
	 * @return
	 */
	public String getAllUserAndRole();
	
	/**
	 * 接收前端提交的用户id和权限id，然后做绑定
	 * @param checkedRoleId
	 * @param userId
	 * @return
	 */
	public boolean changeUserRole(String checkedRoleId, String userId);
	
	/**
	 * 根据功能id获取对应的申请数据
	 * @param fnId
	 * @return
	 */
	public String getApplydata(List<Integer> fnId);
	/**
	 * 根据userId获取对应的权限
	 * @param userId
	 * @return
	 */
	public List<Integer> getapplyIds(String userId);
	
	/**
	 * 拒绝请求的服务层处理方法
	 * @param tk_id
	 * @param reason
	 * @return
	 */
	public String refuseApply(String tk_id,String reason);
	
	/**
	 * 请求通过的服务层处理方法
	 * @param tk_id
	 * @return
	 */
	public String throughApply(String tk_id);
	
	/**
	 * 根据功能id列表获取已经过审或拒绝的申请
	 * @param fnId
	 * @return
	 */
	public String getAppliedData(List<Integer> fnId);

	/**
	 * 获取总揽数据
	 * @return
	 */
	public String getOverviewData();
}
