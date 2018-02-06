package eastrobot.robotdev.ticketsystem.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import eastrobot.robotdev.ticketsystem.constant.SessionConstant;
import eastrobot.robotdev.ticketsystem.constant.UriConstant;

public class LoginInterceptor implements HandlerInterceptor {
	
	/**
	 * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用
	 */	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//拦截器变量
		String uri = request.getRequestURI();
		String path = request.getContextPath();		
	    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	    HttpSession session = request.getSession();
	    /*if(session.getAttribute(SessionConstant.SESSION_IS_LOGIN) != null)
	    	System.out.println("-----is_login-----:"+session.getAttribute(SessionConstant.SESSION_IS_LOGIN).toString());
	    //对login页面的请求，如果已登录跳转主页面，如果没登录则不拦截
	    if(uri.indexOf(UriConstant.LOGIN_PAGE) != -1){
			//System.out.println(session.getAttribute("is_login"));
			if(session.getAttribute(SessionConstant.SESSION_IS_LOGIN) == null
					||session.getAttribute(SessionConstant.SESSION_IS_LOGIN).equals("false")){
				//session中is_login为空说明还未登录过
				return true;//不拦截
			}else{
				//不为空则说明已经登录且session未过期，这时跳转到登陆后的主页面
				//TODO 这里可以针对权限做设计，先不写
				response.sendRedirect(basePath + UriConstant.MAIN_PAGE);
				return false;
			}
		}
	    
	  //对main页面的请求，如果已登录跳转主页面，如果没登录则不拦截
	    if(uri.indexOf(UriConstant.MAIN_PAGE) != -1){
			//System.out.println(session.getAttribute("is_login"));
			if(session.getAttribute(SessionConstant.SESSION_IS_LOGIN) != null
					&& session.getAttribute(SessionConstant.SESSION_IS_LOGIN).equals("true")){
				//session中is_login为不为空且值为true说明已登录
				return true;//不拦截
			}else{
				//不为空则说明已经登录且session未过期，这时跳转到登陆后的主页面
				//TODO 这里可以针对权限做设计，先不写
				response.sendRedirect(basePath + UriConstant.LOGIN_PAGE);
				return false;
			}
		}*/
	    
	    //对修改密码API进行拦截
	    if(uri.indexOf(UriConstant.CHANGE_USER_PASSWORD_API) != -1){
	    	if(session.getAttribute(SessionConstant.SESSION_IS_LOGIN) == null){
	    		//session为空则没有登录，直接拦截返回一个警告json串
	    		JSONObject json = new JSONObject();
				json.put("type", "failure");
				json.put("info", "您已退出登录，请登录后再修改密码!");
	    		response.getWriter().write(json.toJSONString());
	    		return false;
	    	}else{
	    		//不为空则说明已经登录且session未过期，修改密码API可以通行
	    		return true;
	    	}
	    }
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) 
			throws Exception {
	}

	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
