package eastrobot.robotdev.ticketsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eastrobot.robotdev.ticketsystem.dao.Bk_FnDao;
import eastrobot.robotdev.ticketsystem.dao.Bk_OperateDao;
import eastrobot.robotdev.ticketsystem.dao.Bk_RoleDao;
import eastrobot.robotdev.ticketsystem.dao.Bk_UserDao;
import eastrobot.robotdev.ticketsystem.dao.Bk_User_RoleDao;
import eastrobot.robotdev.ticketsystem.dao.Log_User_OperateDao;
import eastrobot.robotdev.ticketsystem.dao.Tk_InfoDao;
import eastrobot.robotdev.ticketsystem.dao.Tk_Pic_UrlDao;
import eastrobot.robotdev.ticketsystem.model.Bk_Fn;
import eastrobot.robotdev.ticketsystem.model.Bk_Operate;
import eastrobot.robotdev.ticketsystem.model.Bk_Role;
import eastrobot.robotdev.ticketsystem.model.Bk_User;
import eastrobot.robotdev.ticketsystem.model.Bk_User_Role;
import eastrobot.robotdev.ticketsystem.model.Log_User_Operate;
import eastrobot.robotdev.ticketsystem.model.Tk_Info;
import eastrobot.robotdev.ticketsystem.model.Tk_Pic_Url;
import eastrobot.robotdev.ticketsystem.service.TestDaoService;
@Service
public class TestDaoServiceImpl implements TestDaoService {
	@Autowired
	private Bk_FnDao bk_FnDao;
	
	@Autowired
	private Bk_OperateDao bk_OperateDao;
	
	@Autowired
	private Bk_RoleDao bk_RoleDao;
	
	@Autowired
	private Bk_User_RoleDao bk_User_RoleDao;
	
	@Autowired
	private Bk_UserDao bk_UserDao;
	
	@Autowired
	private Log_User_OperateDao log_User_OperateDao;
	
	@Autowired
	private Tk_InfoDao tk_InfoDao;
	
	@Autowired
	private Tk_Pic_UrlDao tk_Pic_UrlDao;
	
	
	public void testDao() {
		// TODO Auto-generated method stub
		//init
		Bk_Fn bk_Fn = new Bk_Fn();
		bk_Fn.setId(1);
		bk_Fn.setName("办证");
		bk_Fn.setParent_id(0);
		bk_Fn.setDescription("这个功能是特么办证");
		
		Bk_Operate bk_Operate = new Bk_Operate();
		bk_Operate.setOperate_id(1);
		bk_Operate.setName("登录");
		bk_Operate.setDescription("这个操作是登录");
		
		Bk_Role bk_Role = new Bk_Role();
		bk_Role.setId("werhtjyyhrq");
		bk_Role.setName("管理猿");
		bk_Role.setPermission("11111111111111111111");
		bk_Role.setDescription("这个权限是管理猿哦");
		
		Bk_User_Role user_role = new Bk_User_Role();
		user_role.setRole_id("asdfwqertyre");
		user_role.setUser_id("wqerrthtaAFSGDHFFJHRSA");
		
		Bk_User user = new Bk_User();
		user.setId("aaaabbbbccccddddeeeeffffgggghhhh");
		user.setUsername("micheal");
		user.setPassword("m3b216");
		user.setSalt("9277561221");
		
		Log_User_Operate log_User_Operate = new Log_User_Operate();
		log_User_Operate.setTk_id("wertykjhgfdsweryu");
		log_User_Operate.setId("ytrwewweeuisfdaddh");
		log_User_Operate.setDatetime(new java.sql.Date(new java.util.Date().getTime()));
		log_User_Operate.setOperate_id(1);
		log_User_Operate.setUser_id("luytrewqdsfvbewwrt");
		
		Tk_Info tk_Info = new Tk_Info();
		tk_Info.setTk_id("ktrewq");
		tk_Info.setTk_name("Li");
		tk_Info.setTk_status(1);
		tk_Info.setTk_content("提交的内容");
		tk_Info.setTk_tel("122345121");
		
		Tk_Pic_Url tk_Pic_Url = new Tk_Pic_Url();
		tk_Pic_Url.setPic_id("weqwrtyjytrsasd");
		tk_Pic_Url.setPic_url("qwertyferrewrewr");
		tk_Pic_Url.setPic_url_zip("qewrtyuwqertyy");
		tk_Pic_Url.setTk_id("qwertuyerwqwwert");
		
		bk_FnDao.insert(bk_Fn);
		bk_OperateDao.insert(bk_Operate);
		bk_RoleDao.insert(bk_Role);
		bk_User_RoleDao.insert(user_role);
		bk_UserDao.insert(user);
		log_User_OperateDao.insert(log_User_Operate);
		tk_InfoDao.insert(tk_Info);
		tk_Pic_UrlDao.insert(tk_Pic_Url);
		
		System.out.println(bk_FnDao.select(bk_Fn.getId()).getName());
		System.out.println(bk_OperateDao.select(bk_Operate.getOperate_id()).getDescription());
		System.out.println(bk_RoleDao.select(bk_Role.getId()).getDescription());
		System.out.println(bk_User_RoleDao.select(user_role.getUser_id()).getRole_id());
		System.out.println(bk_UserDao.select(user.getId()).getPassword());
		System.out.println(log_User_OperateDao.select(log_User_Operate.getId()).getDatetime());
		System.out.println(tk_InfoDao.select(tk_Info.getTk_id()).getTk_content());
		System.out.println(tk_Pic_UrlDao.select(tk_Pic_Url.getPic_id()).getPic_url_zip());
		
		System.out.println("-----------------------------------");
		
		bk_Fn.setName("newName");
		bk_FnDao.update(bk_Fn);
		bk_Operate.setDescription("newsetDescription");
		bk_OperateDao.update(bk_Operate);
		bk_Role.setDescription("newsetDescription");
		bk_RoleDao.update(bk_Role);
		user_role.setRole_id("newRoleId");
		bk_User_RoleDao.update(user_role);
		user.setPassword("newPassword");
		bk_UserDao.update(user);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log_User_Operate.setDatetime(new java.sql.Date(new java.util.Date().getTime()));
		log_User_OperateDao.update(log_User_Operate);
		tk_Info.setTk_content("newContent");
		tk_InfoDao.update(tk_Info);
		tk_Pic_Url.setPic_url_zip("new PicUrlZip");
		tk_Pic_UrlDao.update(tk_Pic_Url);
		
		System.out.println(bk_FnDao.select(bk_Fn.getId()).getName());
		System.out.println(bk_OperateDao.select(bk_Operate.getOperate_id()).getDescription());
		System.out.println(bk_RoleDao.select(bk_Role.getId()).getDescription());
		System.out.println(bk_User_RoleDao.select(user_role.getUser_id()).getRole_id());
		System.out.println(bk_UserDao.select(user.getId()).getPassword());
		System.out.println(log_User_OperateDao.select(log_User_Operate.getId()).getDatetime());
		System.out.println(tk_InfoDao.select(tk_Info.getTk_id()).getTk_content());
		System.out.println(tk_Pic_UrlDao.select(tk_Pic_Url.getPic_id()).getPic_url_zip());

		bk_FnDao.delete(bk_Fn.getId());
		bk_OperateDao.delete(bk_Operate.getOperate_id());
		bk_RoleDao.delete(bk_Role.getId());
		bk_User_RoleDao.delete(user_role.getUser_id());
		bk_UserDao.delete(user.getId());
		log_User_OperateDao.delete(log_User_Operate.getId());
		tk_InfoDao.delete(tk_Info.getTk_id());
		tk_Pic_UrlDao.delete(tk_Pic_Url.getPic_id());
	}

}
