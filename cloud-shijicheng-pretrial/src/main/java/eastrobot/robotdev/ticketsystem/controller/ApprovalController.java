package eastrobot.robotdev.ticketsystem.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import eastrobot.robotdev.ticketsystem.constant.CommonConstant;
import eastrobot.robotdev.ticketsystem.constant.SessionConstant;
import eastrobot.robotdev.ticketsystem.constant.UriConstant;
import eastrobot.robotdev.ticketsystem.model.Notify;
import eastrobot.robotdev.ticketsystem.service.AttachmentService;
import eastrobot.robotdev.ticketsystem.service.MigrantMarriedService;
import eastrobot.robotdev.ticketsystem.service.MigrantUnmarriedService;
import eastrobot.robotdev.ticketsystem.service.NotifyService;
import eastrobot.robotdev.ticketsystem.service.ReproductiveCivicsService;
import eastrobot.robotdev.ticketsystem.utils.SendMessageUtil;

/**
 * @Description: 
 *
 * @version: v1.1.0
 * @author: xiangdong.she
 * @date: Sep 20, 2017
 */
@Controller
public class ApprovalController {
	
	@Autowired
	private MigrantUnmarriedService unMarriedService;
	
	@Autowired
	private MigrantMarriedService marriedService;
	
	@Autowired
	private ReproductiveCivicsService reproductiveCivicsService;
	
	@Autowired
	private NotifyService notifyService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private SendMessageUtil messageUtil;
	
	@ResponseBody
	@RequestMapping(value = UriConstant.GET_ALL_APPLY)
	public ModelAndView getAllApply(){
		return new ModelAndView("function/allApply");
	}
	
	/**
	 * @Description: 待审申请/审核记录列表内容请求
	 * @param formType 可选，如果为空，那就是从父级菜单点击进入，需要所有类型的记录
	 * 4：流动人口婚育证明 5：生殖保健服务证明
	 * @param currentPage  当前页
	 * @param pageNumber  页面记录数
	 * @param approvalStatus 必选， 用于区分待审申请和审核记录
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Sep 22, 2017 10:37:04 AM 
	 *
	 */
	@ResponseBody
	@RequestMapping(value = UriConstant.APPROVAL_LIST)
	public Map<String, Object> list(
			@RequestParam(value="formType",required=false)String formType,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage,
			@RequestParam(value="pageNumber",defaultValue="10")int pageNumber,
			@RequestParam(value="approvalStatus")int approvalStatus){
		Map<String, Object> map=new HashMap<>();
		if (formType!=null&&!formType.equals("")) {
			map.put("formType", formType);
		}
		map.put("currentPage", currentPage);
		map.put("pageNumber", pageNumber);
		map.put("approvalStatus", approvalStatus);
		List<Map<String, Object>> list=notifyService.getAllNotify(map);
		Map<String, Object> result=new HashMap<>();
		PageInfo<Map<String, Object>> info=new PageInfo<>(list);
		result.put("total", info.getTotal());
		result.put("rows", list);
		return result;
	}
	
	/**
	 * @Description: 详情页面跳转
	 * @param formNo  表单号 根据通知表单号，跳转不同的页面
	 * @param maritalStatus
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Sep 22, 2017 12:23:13 PM 
	 */
	@ResponseBody
	@RequestMapping(value = UriConstant.APPROVAL_DETAIL_PAGE)
	public ModelAndView migrantDetailPage(
			@RequestParam(value="formNo")Integer formNo){
		Map<String, Object> notifyMap=notifyService.getNotifyByFormNo(formNo);
		int formType=(int) notifyMap.get("formType");
		Map<String, Integer> param=new HashMap<>();
		param.put("formNo", formNo);
		if (formType==CommonConstant.FORM_TYPE_NOTIFY_SZBJ) {
			//生殖保健，只需要一个页面，不管已婚未婚
			//==========页面需要自定义===========
			return new ModelAndView("szbjListDetail",param);
		} else {
			int maritalStatus=(int) notifyMap.get("maritalStatus");
			if (maritalStatus==CommonConstant.MARITAL_STATUS_MARRIED) {
				return new ModelAndView("marriedDetail",param);
			} else {
				return new ModelAndView("notMarriedDetail",param);
			}
		}
	}
	
	/**
	 * @Description: 根据表单号获取不同的详情信息
	 * @param formNo  表单ID
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Sep 21, 2017 10:19:29 AM 
	 *
	 */
	@RequestMapping(value=UriConstant.APPROVAL_DETAIL)
	@ResponseBody
	public Map<String, Object> getDetail(@RequestParam(value="formNo")Integer formNo){
		Map<String, Object> selectParam=new HashMap<>();
		selectParam.put("notifyFormId", formNo);
		//保存基本信息和审核信息(审核信息可能为空))
		List<Map<String, Object>> baseInfo=null;
		Map<String, Object> result=new HashMap<>();
		Map<String, Object> baseInfoMap=new HashMap<>();
		List<Map<String, Object>> filesInfo=null;
		try {
			Map<String, Object> notifyMap=notifyService.getNotifyByFormNo(formNo);
			if (notifyMap!=null) {
				int formType=(int) notifyMap.get("formType");

				if (formType==CommonConstant.FORM_TYPE_NOTIFY_SZBJ) {
					//生殖保健，所有用户都是一眼的信息，基本信息+审核信息+附件信息
					baseInfo=reproductiveCivicsService.getMaritalMarriedDetail(selectParam);
				} else {
					int maritalStatus=(int) notifyMap.get("maritalStatus");
					if (maritalStatus==CommonConstant.MARITAL_STATUS_MARRIED) {
						baseInfo=marriedService.getMaritalMarriedDetail(selectParam);
					} else {
						baseInfo=unMarriedService.getMaritalUnmarriedDetail(selectParam);
					}
				}
				if (baseInfo!=null&&baseInfo.size()!=0) {
					baseInfoMap=baseInfo.get(0);
				}
				//查询formId对应的附件信息
				filesInfo=attachmentService.getFileByFormNo(selectParam);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		result.put("files", filesInfo);
		result.put("baseInfo", baseInfoMap);
		return result;
	}
	
	/**
	 * @Function: ApprovalController::approval
	 * @Description: 针对于每一个申请，进行审核
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Sep 27, 2017 10:06:20 AM 
	 *
	 */
	@RequestMapping(value=UriConstant.APPROVAL_APPLRY, method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> approval(
			@RequestParam(value="formNo")Integer formNo,
			@RequestParam(value="approvalStatus")Integer approvalStatus,
			@RequestParam(value="approvalResult")String approvalResul,HttpSession httpSession){
		Map<String, Object> map=new HashMap<>();
		Map<String, Object> result=new HashMap<>();
		map.put("formNo", formNo);
		map.put("approvalStatus", approvalStatus);
		map.put("approvalResul", approvalResul);
		String approvalPerson=httpSession.getAttribute(SessionConstant.SESSION_USER_NAME).toString();
		if(approvalPerson==null || "".equals(approvalPerson)){
			result.put("code", 0);
			result.put("message", "提交失败！");
			return result;
		}
		map.put("approvalPerson", approvalPerson);
		map.put("approvalTime", new Date());
		//更新审核状态
		notifyService.updateByFormNo(map);
		
		result.put("code", 1);
		result.put("message", "提交成功");
		
		//发送短信
		Map<String, Object> notify=notifyService.getNotifyByFormNo(formNo);
		//1. 不通过  2. 通过
		if (approvalStatus==1) {
			messageUtil.sendMessage((String)notify.get("phone"), (String)notify.get("proposerName"), String.valueOf(formNo), (int) notify.get("formType"), "默认拒绝短信模板");
			
		} else if (approvalStatus==2) {
			messageUtil.sendMessage((String)notify.get("phone"), (String)notify.get("proposerName"), String.valueOf(formNo), (int) notify.get("formType"), "默认通过短信模板");
		}
		
		return result;
	}
	
	/**
	 * @Function: ApprovalController::approval
	 * @Description: 得到主页工单的详细情况
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Sep 27, 2017 10:06:20 AM 
	 *
	 */
	@RequestMapping(value=UriConstant.MAIN_PAGE_OVERVIEW)
	@ResponseBody
	public Map<String, Object> getOverviewDataCount() {
		Map<String, Object> result=new HashMap<>();
		Map<String, Object> map=new HashMap<>();
		//获取未审核工单数
		map.put("approvalStatus", 0);
		map.put("approvalBeginDate", null);
		map.put("approvalEndDate", null);
		int apllyCount = notifyService.getNotifyDetailCount(map);
		result.put("apllyCount", apllyCount);
		//获取今日新增
		SimpleDateFormat dfBdate = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String bDate = dfBdate.format(new Date(System.currentTimeMillis()));
		SimpleDateFormat dfEdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String eDate = dfEdate.format(new Date(System.currentTimeMillis()));
		map.put("approvalStatus", null);
		map.put("approvalBeginDate", bDate);
		map.put("approvalEndDate", eDate);
		int todayNew = notifyService.getNotifyDetailCount(map);
		result.put("todayNew", todayNew);
		//获取总用户数
		int userCount = notifyService.selectDistinctTel();
		result.put("userCount", userCount);
		//获取工单总数
		map.put("approvalStatus", null);
		map.put("approvalBeginDate", null);
		map.put("approvalEndDate", null);
		int allApplyCount = notifyService.getNotifyDetailCount(map);
		result.put("allApplyCount", allApplyCount);
		
		return result;
	}

}
