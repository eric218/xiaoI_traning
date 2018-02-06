package eastrobot.robotdev.ticketsystem.constant;

/**
 * 这个接口中定义的常量是项目中所有的URI
 * @author zerlzerl
 *
 */
public interface UriConstant {
	//根URI
	public static final String ROOT_URI = "/";
	//登录页面URI
	public static final String LOGIN_PAGE = "/login";
	//登出功能URI
	public static final String LOGOUT_API = "/logout.api";
	//登录验证功能URI
	public static final String LOGIN_API = "/login.api";
	//主页面URI
	public static final String MAIN_PAGE = "/main";
	//查看审核记录URI
	public static final String REVIEW_LOG_PAGE_API = "/main/getReviewLog.api";
	//用户设置URI
	public static final String USER_SETTING_PAGE = "/main/getUserSetting.api";
	//修改密码URI
	public static final String USER_SETTING_CHANGE_PASSWORD = "/main/u_changePassword";
	//添加删除用户URI
	public static final String USER_SETTING_ADD_DELETE_USER = "/main/u_addDeleteUser.api";
	//添加新用户API
	public static final String ADD_NEW_USER_API = "/main/addNewUser.api";
	//删除用户API
	public static final String DELET_USER_API = "/main/deleteUser.api";
	//管理员修改密码API
	public static final String CHANGE_PASSWORD_ADMIN = "/main/changePasswordWithoutOriginPassword.api";
	//管理员修改用户状态的API
	public static final String CHANGE_USER_STATUS = "/main/changeUserStatus.api";
	//添加新用户页面URI
	public static final String ADD_USER_PAGE_API = "/getAddUserHtml.api";
	//删除用户页面URI
	public static final String DELETE_USER_PAGE_API = "/getDeleteUserHtml.api";
	//权限管理页面API
	public static final String PERMISSION_SETTING_API = "/main/getPermissionSetting.api";
	//获取所有的角色
	public static final String GET_ALL_ROLES = "/main/perm_getAllRoles.api";
	//获取所有权限树，用来显示在修改权限的模态窗
	public static final String GET_ALL_FN_TREE = "/main/getAllFnTree.api";
	//接收前端修改好的权限树
	public static final String SUBMIT_CHANGED_PERMS_API = "/main/submitChangedPermission.api";
	//接收前端修改的角色描述接口
	public static final String CHANGE_ROLE_DESCRIPTION = "/main/changeRoleDescription.api";
	//接收前端新建角色接口
	public static final String ADD_NEW_ROLE_API = "/main/addNewRole.api";
	//接收前端删除角色接口
	public static final String DELETE_ROLE_API = "/main/deleteRole.api";
	//获取角色和用户绑定的页面的接口
	public static final String ROLE_USER_BIND = "/main/perm_roleUserBind.api";
	//接收选择的角色和用户绑定信息
	public static final String RECIEVE_USER_ROLE_BIND_API= "/main/submitUserRoleBind.api";
	
	//获取待审申请页面
	public static final String GET_ALL_APPLY = "/main/getAllApply.api";
	//获取所有申请数据
	public static final String GET_ALL_APPLY_DATA = "/main/getAllApplyData.api";
	//获取初始化页面表数据的接口
	public static final String GET_APPLY_DATA = "/main/getApplyData.api";
	//前端审核结果提交到后台的接口
	public static final String RECIEVE_TRIAL_RESULT = "/main/recieveTrialResult";
	//获取用户和角色信息表
	public static final String GET_ALL_USER_AND_ROLE = "/main/getAllUserAndRole.api";
	//查看登录记录URI
	public static final String USER_LOGIN_LOG_PAGE_API = "/main/u_loginLog.api";
	
	//重设密码URI
	public static final String CHANGE_USER_PASSWORD_API = "/changePassword.api";
	
	//获取主页面html的API
	public static final String GET_MAIN_PAGE_API = "/main/getMainPage.api";
	//获取主页的总揽数据的api
	public static final String GET_OVERVIEW_DATA = "main/getOverviewData.api";
	//获取用户设置页面的API
	public static final String GET_USER_SETTING_PAGE_API = "/getUserSettingHtml.api";
	//获取所有用户列表的API
	public static final String GET_ALL_USERS_API = "/main/getAllUsers.api";
	//获取siderbar导航条的API
	public static final String GET_SIDERBAR_PAGE_API = "/getSiderBar.api";
	//获取navbar的API
	public static final String GET_NAVBAR_API = "/getNavBar.api";
	
	
	//前端上传图片和信息的表单URI
	public static final String INPUT_DEMO = "/inputdemo";
	//前端上传图片的API接口
	public static final String UPLOAD_API = "/upload.api";
	//文件上传目录URI
	public static final String UPLOAD_IMG_PATH = "/upload/images";
	
	
	//404页面
	public static final String PAGE_NOT_FOUND = "/404";
	
	//待审申请/审核记录API
	public static final String APPROVAL_LIST = "/approval/list";
	//详情页面跳转
	public static final String APPROVAL_DETAIL_PAGE = "/approval/detail.api";
	//流动人口待审申请详情
	public static final String APPROVAL_DETAIL = "/approval/detail";
	//审核申请
	public static final String APPROVAL_APPLRY = "/approval/apply";
	public static final String RECRUIT_LIST = "/recruit/list";
	public static final String RECRUIT_DETAIL = "/recruit/getDetail";
	public static final String RECRUIT_DETAIL_PAGE = "/recruit/getDetail.api";
	public static final String UPDATEORADD = "/recruit/updateOrAdd";
	public static final String RECRUIT_ADDRECRUIT = "/recruit/addRecruit";
	public static final String RECRUIT_DIDTECRUIT = "/recruit/editRecruit.api";
	public static final String DELETEPUBLISH = "/recruit/deletePublish";
	
	//民调管理
	public static final String POLL_MANAGE = "/main/pollManage.api";
	public static final String POLL_LIST = "/poll/list";
	public static final String POLL_ADD = "/poll/add";
	public static final String POLL_EDIT = "/poll/edit";
	public static final String POLL_COUNT = "/poll/count";	
	public static final String POLL_SAVE = "/poll/save";
	public static final String POLL_UPDATE = "/poll/update";
	public static final String POLL_DELETE = "/poll/delete";
	public static final String POLL_PUBLISH = "/poll/publish";
	public static final String POLL_UNPUBLISH = "/poll/unpublish";
	public static final String POLL_REPORT = "/poll/report";
	
	//得到主页的总共审核工单情况
	public static final String MAIN_PAGE_OVERVIEW = "/main/getMainOverviewInfo.api";
	
	
}
