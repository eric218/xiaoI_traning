package eastrobot.robotdev.ticketsystem.constant;
/**
 * 这个接口定义在项目中用到的session的名称
 * @author zerlzerl
 *
 */
public interface SessionConstant {
	//登录后加上该名称的session属性表示登录状态
	public static final String SESSION_IS_LOGIN = "is_login";
	//登录后加上该名称的session用来存放用户id
	public static final String SESSION_USER_ID = "user_id";
	//登录后加上该名称的session用来存放用户name
	public static final String SESSION_USER_NAME = "user_name";
	//页面请求jsonFileName
	public static final String JSON_FILE_NAME = "json_file_name";
	//同一用户(已session区分)在提交窗口提交数据的缓存记录，存在一个session生命周期的时长
	public static final String APPLY_CACHE = "apply_cache";

}
