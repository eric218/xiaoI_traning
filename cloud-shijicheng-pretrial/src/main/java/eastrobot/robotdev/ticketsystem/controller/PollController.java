package eastrobot.robotdev.ticketsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import eastrobot.robotdev.ticketsystem.constant.SessionConstant;
import eastrobot.robotdev.ticketsystem.constant.UriConstant;
import eastrobot.robotdev.ticketsystem.model.Attachment;
import eastrobot.robotdev.ticketsystem.model.PollOptions;
import eastrobot.robotdev.ticketsystem.model.PollSurvey;
import eastrobot.robotdev.ticketsystem.model.PollTopic;
import eastrobot.robotdev.ticketsystem.model.vo.PollCreateVo;
import eastrobot.robotdev.ticketsystem.model.vo.PollOptVo;
import eastrobot.robotdev.ticketsystem.model.vo.PollTopicVo;
import eastrobot.robotdev.ticketsystem.service.AttachmentService;
import eastrobot.robotdev.ticketsystem.service.PollOptionsService;
import eastrobot.robotdev.ticketsystem.service.PollReplyService;
import eastrobot.robotdev.ticketsystem.service.PollSurveyService;
import eastrobot.robotdev.ticketsystem.service.PollTopicService;
import eastrobot.robotdev.ticketsystem.utils.FileUtils;


@Controller
public class PollController {

	private static final Logger LOGGER = Logger.getLogger(PollController.class);
	
	@Autowired
	private PollSurveyService pollSurveyService;
	
	@Autowired
	private PollTopicService pollTopicService;
	
	@Autowired
	private PollOptionsService pollOptionsService;
	
	@Autowired
	private PollReplyService pollReplyService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping(value = UriConstant.POLL_MANAGE, method=RequestMethod.GET)
	public ModelAndView index(){
		Map<String, Integer> param=new HashMap<>();
		return new ModelAndView("viewPoll", param);
	}
	
	/** 
	 * @Title: addPollSurvey
	 * @Description: 新增问卷调查
	 * @param: pollCreateVo
	 * @throws TODO
	 */
	public void addPollSurvey(PollCreateVo pollCreateVo){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//插入问卷调查对象
		PollSurvey pollSurvey = new PollSurvey();
		pollSurvey.setTitle(pollCreateVo.getTitle());
		pollSurvey.setDescription(pollCreateVo.getDes());
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = sdf.parse(pollCreateVo.getDatetimeStart());
			endTime = sdf.parse(pollCreateVo.getDatetimeEnd());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		pollSurvey.setStartTime(startTime);
		pollSurvey.setEndTime(endTime);
		pollSurvey.setCreator(session.getAttribute(SessionConstant.SESSION_USER_NAME).toString());
		pollSurvey.setCreateTime(Timestamp.valueOf(sdf.format(new Date())));
		pollSurvey.setStatus(0);
		Integer surveyId = pollSurveyService.addPollSurvey(pollSurvey);
		
		//插入问卷问题对象和选项对象
		List<PollTopicVo> surveyTopics = pollCreateVo.getSurveyTopicVos();
		if(surveyTopics.size() > 0 && surveyTopics != null){
			for(int i=0; i<surveyTopics.size(); i++){
				if(surveyTopics.get(i)!=null && surveyTopics.get(i).getTopicTypeNum()!=null){
					//插入问题对象
					PollTopicVo surveyTopicVo = surveyTopics.get(i);
					PollTopic pollTopic = new PollTopic();
					pollTopic.setSurveyId(surveyId);
					pollTopic.setTitle(surveyTopicVo.getOpts_title());
					pollTopic.setSeq(i+1);
					pollTopic.setType(Integer.parseInt(surveyTopicVo.getTopicTypeNum()));
					pollTopic.setCreator(session.getAttribute(SessionConstant.SESSION_USER_NAME).toString());
					pollTopic.setCreateTime(Timestamp.valueOf(sdf.format(new Date())));
					Integer topicId = pollTopicService.addPollTopic(pollTopic);
					
					List<PollOptVo> surveyOptions = surveyTopicVo.getSurveyOptVos();
					if(surveyOptions != null && surveyOptions.size()>0){
						//插入选项对象
						for(int j=0; j<surveyOptions.size(); j++){
							PollOptions pollOptions = new PollOptions();
							pollOptions.setTopicId(topicId);
							pollOptions.setContent(surveyOptions.get(j).getOptValue());
							pollOptions.setSeq(j+1);
							pollOptions.setIsAnswer(Integer.parseInt(surveyOptions.get(j).getAns()));
							Integer optionId = pollOptionsService.addPollOptions(pollOptions);
							//如果选项有图片附件插入附件
							if((surveyOptions.get(j).getMyfile() != null && surveyOptions.get(j).getMyfile().getSize()>0)
									|| (surveyOptions.get(j).getPicSrc() != null
									&& !surveyOptions.get(j).getPicSrc().equals("")
									&& surveyOptions.get(j).getMyfile().getSize()<=0)){
								try {
									MultipartFile multipartFile = null;
									if(surveyOptions.get(j).getMyfile() != null && surveyOptions.get(j).getMyfile().getSize()>0){
										multipartFile = surveyOptions.get(j).getMyfile();
									}else{
										String realPath = request.getSession().getServletContext().getRealPath(File.separator);
										realPath = realPath.substring(0, realPath.length() - 1);
										int aString = realPath.lastIndexOf(File.separator);
										realPath = realPath.substring(0, aString);
										String filePath = realPath+File.separator+surveyOptions.get(j).getPicSrc();
										File file = new File(filePath);
										FileInputStream input = new FileInputStream(file);
										multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", input);
									}
									Attachment optionImg = FileUtils.upload(request, multipartFile, "images", "POLL_SURVEY");
									Attachment attachment = new Attachment();
									attachment.setFileName(optionImg.getFileName());
									attachment.setFileAddr(optionImg.getFileAddr());
									attachment.setSeq(0);
									attachment.setModeCode("POLL_SURVEY");
									attachment.setModeId(optionId);
									attachmentService.saveAttachment(attachment);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}
	
	/** 
	 * @Title: deletePollSurvey
	 * @Description: 删除问卷调查
	 * @param: surveyId
	 * @throws TODO
	 */
	public List<String> deletePollSurvey(Integer surveyId){
		List<PollTopic> pollTopics = pollTopicService.findTopicsBySurveyId(surveyId);
		List<String> filePathList = new ArrayList<String>();
		if(pollTopics != null && pollTopics.size()>0){
			for(int i=0; i<pollTopics.size(); i++){
				Integer topicId = pollTopics.get(i).getId();
				List<PollOptions> pollOptions = pollOptionsService.findAllPollOptions(topicId);
				if(pollOptions != null && pollOptions.size()>0){
					for(int j=0; j<pollOptions.size(); j++){
						Integer optionId = pollOptions.get(j).getId();
						try {
							List<Attachment> images = attachmentService.findAttachmentsByModeIdAndModeCode("POLL_SURVEY", optionId);
							if(images != null && images.size()>0){
								for(int k=0; k<images.size(); k++){
									filePathList.add(images.get(k).getFileAddr());
								}
							}
							attachmentService.deleteAttachmentsByModeIdAndModeCode("POLL_SURVEY", optionId);
							// 删除磁盘上的文件
							//FileUtil.deleteFile(request, filePathList);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				pollOptionsService.deletePollOptionsByTopicId(topicId);
			}
		}
		pollTopicService.deleteTopicsBySurveyId(surveyId);
		pollSurveyService.deleteSurvey(surveyId);
		pollReplyService.deletePollReplyBySurveyId(surveyId);
		return filePathList;
	}
	
	/** 
	 * @Title: getPollByPage 
	 * @Description: 分页查询问卷调查
	 * @param: pageSize 分页大小
	 * @param: pageNumber 页码
	 * @throws TODO
	 */
	@RequestMapping(value = UriConstant.POLL_LIST, method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getPollByPage(Integer pageSize, Integer pageNumber){
		LOGGER.info("pageSize:"+pageSize+"----"+"pageNumber:"+pageNumber);
		Map<String, Object> result = new HashMap<String, Object>();
		List<PollSurvey> pollSurveyList = pollSurveyService.queryForPage(pageSize, pageNumber);
		Integer pollSurveyCount = pollSurveyService.getSurveyCount();
		result.put("total", pollSurveyCount);
		result.put("rows", pollSurveyList);
		return result;
	}
	
	@RequestMapping(value = UriConstant.POLL_ADD, method=RequestMethod.GET)
	public ModelAndView add(){
		Map<String, Integer> param=new HashMap<>();
		return new ModelAndView("addPoll", param);
	}
	
	/**
	 * 进入  民调修改 页面
	 * @param surveyId 民调ID
	 * @return
	 */
	@RequestMapping(value = UriConstant.POLL_EDIT, method=RequestMethod.GET)
	public ModelAndView edit(Integer surveyId){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PollCreateVo pollCreateVo = new PollCreateVo();
		//封装问卷调查对象
		PollSurvey pollSurvey = pollSurveyService.findPollSurveyById(surveyId);
		pollCreateVo.setId(pollSurvey.getId());
		pollCreateVo.setTitle(pollSurvey.getTitle());
		pollCreateVo.setDes(pollSurvey.getDescription());
		pollCreateVo.setDatetimeStart(sdf.format(pollSurvey.getStartTime()));
		pollCreateVo.setDatetimeEnd(sdf.format(pollSurvey.getEndTime()));
		//封装问卷问题对象
		List<PollTopic> pollTopics = pollTopicService.findTopicsBySurveyId(surveyId);
		List<PollTopicVo> surveyTopicVos = new ArrayList<PollTopicVo>();
		if(pollTopics != null && pollTopics.size()>0){
			for(int i=0; i<pollTopics.size(); i++){
				PollTopicVo surveyTopicVo = new PollTopicVo();
				Integer topicId = pollTopics.get(i).getId();
				LOGGER.info("topicId:"+topicId);
				surveyTopicVo.setId(topicId);
				surveyTopicVo.setOpts_title(pollTopics.get(i).getTitle());
				surveyTopicVo.setTopicTypeNum(pollTopics.get(i).getType().toString());
				//封装选项对象
				List<PollOptions> pollOptions = pollOptionsService.findAllPollOptions(topicId);
				List<PollOptVo> surveyOptVos = new ArrayList<PollOptVo>();
				if(pollOptions != null && pollOptions.size()>0){
					for(int j=0; j<pollOptions.size(); j++){
						PollOptVo surveyOptVo = new PollOptVo();
						Integer optionId = pollOptions.get(j).getId();
						LOGGER.info("optionId:"+optionId);
						surveyOptVo.setId(optionId);
						surveyOptVo.setOptValue(pollOptions.get(j).getContent());
						surveyOptVo.setAns(pollOptions.get(j).getIsAnswer().toString());
						//获取选项图片
						List<Attachment> images;
						try {
							images = attachmentService.findAttachmentsByModeIdAndModeCode("POLL_SURVEY", optionId);
							if(images != null && images.size()>0){
								Attachment optionImage = images.get(0);
								String imagePath = optionImage.getFileAddr();
								LOGGER.info("imagePath:"+imagePath);
								surveyOptVo.setPicSrc(imagePath);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						surveyOptVos.add(surveyOptVo);
					}
				}
				surveyTopicVo.setSurveyOptVos(surveyOptVos);
				surveyTopicVos.add(surveyTopicVo);
			}
		}
		pollCreateVo.setSurveyTopicVos(surveyTopicVos);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pollCreateVo", pollCreateVo);
		return new ModelAndView("editPoll", resultMap);
	}
	
	@RequestMapping(value = UriConstant.POLL_COUNT, method=RequestMethod.GET)
	public ModelAndView count(String title, int id){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("title",title);
		resultMap.put("id",id);
		return new ModelAndView("countPoll", resultMap);
	}
	
	/** 
	 * @Title: save 
	 * @Description: 保存问卷调查
	 * @param: pollCreateVo 民调对象VO
	 * @throws TODO
	 */
	@RequestMapping(value = UriConstant.POLL_SAVE, method=RequestMethod.POST)
	@ResponseBody
	public String save(PollCreateVo pollCreateVo){
		addPollSurvey(pollCreateVo);
		return "1";
	}
	
	/**
	 * 修改民调(先删除后新增)
	 * @param pollCreateVo
	 * @return
	 */
	@RequestMapping(value = UriConstant.POLL_UPDATE, method=RequestMethod.POST)
	@ResponseBody
	public String update(PollCreateVo pollCreateVo){
		//此SurveyCreateVo 对象中会返回  surveyCreateVo.id 属性
		//建议修改的时候删除掉此对象中对应的所有题目和选项，重新关联
		//删除问卷调查对象及关联对象
		Integer surveyVoId = pollCreateVo.getId();
		List<String> filePathList = deletePollSurvey(surveyVoId);
		//新增问卷调查对象及关联对象
		addPollSurvey(pollCreateVo);
		FileUtils.deleteFiles(request, filePathList);
		return "1";
	}
	
	/**
	 * 删除民调
	 * @param surveyId
	 * @return
	 */
	@RequestMapping(value = UriConstant.POLL_DELETE, method=RequestMethod.GET)
	@ResponseBody
	public String delete(Integer surveyId){
		deletePollSurvey(surveyId);
		return "success";
	}
	
	//发布操作
	@RequestMapping(value = UriConstant.POLL_PUBLISH, method=RequestMethod.GET)
	@ResponseBody
	public String publishSurvey(Integer surveyId){		
		try {
			pollSurveyService.CancelSurvey(); //上一个发布，status 1->2
			pollSurveyService.updatePollSurveyById(surveyId); //本条记录，status -> 1
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "1";
	}
	
	//取消发布 操作
	@RequestMapping(value = UriConstant.POLL_UNPUBLISH, method=RequestMethod.GET)
	@ResponseBody
	public String unpublishSurvey(){
		try {
			pollSurveyService.CancelSurvey(); //上一个发布，status 1->2
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "1";
	}

	//报表接口
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = UriConstant.POLL_REPORT, method=RequestMethod.GET)
	@ResponseBody
	public List getSurveyReport(int id){
		List list = new ArrayList();
		try {
			list = pollReplyService.findSurveyLists(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
