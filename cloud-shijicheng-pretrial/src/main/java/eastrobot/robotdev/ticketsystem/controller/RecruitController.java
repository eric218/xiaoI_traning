package eastrobot.robotdev.ticketsystem.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import eastrobot.robotdev.ticketsystem.constant.CommonConstant;
import eastrobot.robotdev.ticketsystem.constant.SessionConstant;
import eastrobot.robotdev.ticketsystem.constant.UriConstant;
import eastrobot.robotdev.ticketsystem.model.Attachment;
import eastrobot.robotdev.ticketsystem.model.JobPublish;
import eastrobot.robotdev.ticketsystem.service.AttachmentService;
import eastrobot.robotdev.ticketsystem.service.JobCompJobsService;
import eastrobot.robotdev.ticketsystem.service.JobCompanyService;
import eastrobot.robotdev.ticketsystem.service.JobPersonService;
import eastrobot.robotdev.ticketsystem.service.JobPublishService;
import eastrobot.robotdev.ticketsystem.utils.FileUtils;

@Controller
public class RecruitController {

	@Autowired
	private JobCompanyService jobCompanyService;
	
	@Autowired
	private JobCompJobsService jobCompJobsService;

	@Autowired
	private JobPersonService jobPersonService;
	
	@Autowired
	private JobPublishService jobPublishService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private HttpServletRequest request;

	/**
	 * @Description: 招聘相关的列表
	 * @param currentPage  当前页号
	 * @param pageNumber	每页数据量
	 * @param type	区分公司/个人/发布信息
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Oct 18, 2017 12:42:51 PM 
	 *
	 */
	@RequestMapping(value = UriConstant.RECRUIT_LIST,method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value = "pageNumber", defaultValue = "10") int pageNumber,
			@RequestParam(value = "type") int type) {
		Map<String, Object> paraResutl = new HashMap<>();
		Map<String, Object> selectParam = new HashMap<>();
		selectParam.put("currentPage", currentPage);
		selectParam.put("pageNumber", pageNumber);
		List<Map<String, Object>> result = null;
		if (type == 0) {
			// 公司招聘信息列表
			result = jobCompanyService.getAllCompJob(selectParam);
		} else if (type == 1) {
			// 个人求职列表
			result = jobPersonService.getAllPersonJob(selectParam);
		}else if (type==2) {
			//就业信息发布列表
			result=jobPublishService.getAllPublishJob(selectParam);
		}
		PageInfo<Map<String, Object>> info=new PageInfo<>(result);
		paraResutl.put("total", info.getTotal());
		paraResutl.put("rows", result);
		return paraResutl;
	}
	
	/**
	 * @Description: 查询详情页面
	 * @param jobId
	 * @param type
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Oct 18, 2017 12:42:32 PM 
	 *
	 */
	@RequestMapping(value=UriConstant.RECRUIT_DETAIL)
	@ResponseBody
	public Map<String, Object> getDetailInfo(
			@RequestParam(value="jobId")int jobId,
			@RequestParam(value="type")int type){
		Map<String, Object> result=new HashMap<>();
		
		Map<String, Object> fileSelectParam=new HashMap<>();
		fileSelectParam.put("jobId", jobId);
		if (type==0) {
			//公司
			result=jobCompanyService.getCompDetailById(jobId);
			fileSelectParam.put("modelCode", CommonConstant.JOB_COMPANY);
			if (result.containsKey("jobNo")) {
				List<Map<String, Object>> jobCompJobs=jobCompJobsService.getCompJobsByJobNo(String.valueOf(jobId));
				result.put("jobCompJobs", jobCompJobs);
			}
		}else if (type==1) {
			//个人
			result=jobPersonService.getPersonDetailById(jobId);
			fileSelectParam.put("modelCode", CommonConstant.JOB_PERSON);
		}else if (type==2) {
			//招聘信息发布
			result=jobPublishService.getPublishDetailById(jobId);
			fileSelectParam.put("modelCode", CommonConstant.MODE_CODE_RECRUIT);
		}
		//查询对应的附件
		if (result!=null) {
			List<Map<String, Object>> files=attachmentService.getRecruitFiles(fileSelectParam);
			result.put("files", files);
		}
		return result;
	}
	
	/**
	 * @Description: 修改或新增就业信息的发布
	 * @param jobTitle 
	 * @param content
	 * @param jobId
	 * @param httpSession
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @throws Exception 
	 * @date: Oct 18, 2017 12:43:42 PM 
	 *
	 */
	@RequestMapping(value=UriConstant.UPDATEORADD,method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateOrAddJobPublish(
			@RequestParam(value="jobTitle")String jobTitle,
			@RequestParam(value="content")String content,
			@RequestParam(value="jobId")int jobId,
			@RequestParam(value="files",required=false)MultipartFile[] files,
			@RequestParam(value="deleteFileIds",required=false)String deleteFileIds) throws Exception{
		String publisher=httpSession.getAttribute(SessionConstant.SESSION_USER_NAME).toString();
		Map<String, Object> result=new HashMap<>();
		JobPublish jobPublish=new JobPublish();
		jobPublish.setContent(content);
		jobPublish.setJobTitle(jobTitle);
		jobPublish.setPublisher(publisher);
		jobPublish.setCreateTime(new Date());
		
		if (jobId==0) {
			//新增
			jobPublishService.insert(jobPublish);
			result.put("code", 1);
			System.out.println("jobPublishId ======"+jobPublish.getId());
			if (jobPublish.getId()!=null) {
				//上传文件，并在数据库中添加记录
				filesUpload(files, jobPublish.getId());
			}
			result.put("message", "插入成功");
		}else {//修改
			jobPublish.setId(jobId);
			/*更新发布内容*/
			jobPublishService.updateById(jobPublish);
			/*删除文件*/
			if (deleteFileIds!=null&&!"".equals(deleteFileIds)) {
				String[] ids=deleteFileIds.split("#");
				for (int i = 0; i < ids.length; i++) {
					int fileId=Integer.valueOf(ids[i]);
					attachmentService.deleteAttachmentById(fileId);
				}
			}
			/*添加新增的文件*/
			filesUpload(files, jobId);
			result.put("code", 1);
			result.put("message", "修改成功");
		}
		return result;
	}

	/**
	 * @Description: 文件上传，并在数据库中添加记录
	 * @param files  0或多个文件
	 * @param jobPublish   招聘信息发布
	 * @throws Exception
	 * @author: xiangdong.she
	 * @date: Oct 19, 2017 1:45:24 PM 
	 *
	 */
	private void filesUpload(MultipartFile[] files, int jobPublishId) throws Exception {
		if (files!=null&&files.length!=0) {
			int seq=0;
			for (MultipartFile multipartFile : files) {
				if (!multipartFile.isEmpty()) {
					Attachment attachment=FileUtils.upload(request, multipartFile, "recruit", CommonConstant.MODE_CODE_RECRUIT);
					attachment.setModeId(jobPublishId);
					attachment.setSeq(seq++);
					attachment.setModeCode(CommonConstant.MODE_CODE_RECRUIT);
					attachmentService.saveAttachment(attachment);
				}
			}
		}
	}
	
	/**
	 * @Description: 公司/个人/就业信息详情页面的跳转
	 * @param jobId
	 * @param type
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Oct 18, 2017 12:44:42 PM 
	 *
	 */
	@RequestMapping(value=UriConstant.RECRUIT_DETAIL_PAGE)
	@ResponseBody
	public ModelAndView getDetailInfoPage(
			@RequestParam(value="jobId")int jobId,
			@RequestParam(value="type")int type){
		Map<String, Integer> param=new HashMap<>();
		param.put("jobId", jobId);
		param.put("type", type);
		if (type==0) {
			//公司
			return new ModelAndView("companyDetail",param);
		}else if (type==1) {
			//个人
			return new ModelAndView("persionDetail",param);
		}else{
			//招聘信息发布
			return new ModelAndView("zpxxfuDetail",param);
		}
	}
	
	@RequestMapping(value=UriConstant.RECRUIT_ADDRECRUIT)
	@ResponseBody
	public ModelAndView addRecruitlInfo(){
		return new ModelAndView("createRecruit");
	}
	
	@RequestMapping(value=UriConstant.RECRUIT_DIDTECRUIT)
	@ResponseBody
	public ModelAndView editRecruitlInfo(
			@RequestParam(value="jobId")int jobId){
		Map<String, Integer> param=new HashMap<>();
		param.put("jobId", jobId);
		return new ModelAndView("editRecruit", param);
	}
	
	@RequestMapping(value=UriConstant.DELETEPUBLISH)
	@ResponseBody
	public Map<String, Object> deleteJobPublish(
			@RequestParam(value="jobId")int jobId,HttpSession httpSession){
		String publisher=httpSession.getAttribute(SessionConstant.SESSION_USER_NAME).toString();
		Map<String, Object> result=new HashMap<>();
		if(publisher==null || "".equals(publisher)){
			result.put("code", 0);
			result.put("message", "删除失败");
			return result;
		}
		try{
			jobPublishService.deletePublishDetailById(jobId);
			attachmentService.deleteAttachmentsByModeIdAndModeCode(CommonConstant.MODE_CODE_RECRUIT, jobId);
		}catch(Exception e){
			e.printStackTrace();
			result.put("code", 0);
			result.put("message", "删除失败");
			return result;
		}
		result.put("code", 1);
		result.put("message", "删除成功");
		return result;
	}

}
