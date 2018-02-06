/**
 * All rights Reserved
 * @Title: 	PollSurveyService.java 
 * @Package eastrobot.robotdev.ticketsystem.service 
 * @Description: 	TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 3:50:28 PM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.service;

import java.util.List;

import eastrobot.robotdev.ticketsystem.model.PollSurvey;

/** 
 * @ClassName:	PollSurveyService 
 * @Description:TODO 
 * @author:	wei wei
 * @date:	Oct 9, 2017 3:50:28 PM 
 *  
 */
public interface PollSurveyService {

	//分页查询问卷调查
	List<PollSurvey> queryForPage(Integer pageSize, Integer pageNumber);
		
	//查询总记录条数
	int getSurveyCount();

	//新增问卷调查
	Integer addPollSurvey(PollSurvey pollSurvey);
		
	//通过主键ID查询问卷调查
	PollSurvey findPollSurveyById(Integer surveyId);
		
	//删除问卷调查
	void deleteSurvey(Integer surveyId);
	
	//发布 取消
	void CancelSurvey();
	
	//发布
	void updatePollSurveyById(Integer surveyId);
	
}
