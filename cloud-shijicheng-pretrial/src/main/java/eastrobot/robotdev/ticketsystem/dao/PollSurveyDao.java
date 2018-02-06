/**
 * All rights Reserved
 * @Title: 	PollSurveyDao.java 
 * @Package eastrobot.robotdev.ticketsystem.dao 
 * @Description: 	TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 11:11:37 AM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import eastrobot.robotdev.ticketsystem.model.PollSurvey;


/** 
 * @ClassName:	PollSurveyDao 
 * @Description:TODO 
 * @author:	wei wei
 * @date:	Oct 9, 2017 11:11:37 AM 
 *  
 */
public interface PollSurveyDao {

	//分页查询问卷调查
	List<PollSurvey> queryForPage(@Param("sign")boolean sign,@Param("pageNumber")Integer pageNumber,@Param("pageSize")Integer pageSize);
	
	Integer getSurveyCount();
	
	//新增问卷调查
	Integer addPollSurvey(PollSurvey pollSurvey);
	
	//通过主键ID查询问卷调查
	PollSurvey findPollSurveyById(@Param("surveyId")Integer surveyId);
	
	//删除问卷调查
	void deleteSurvey(@Param("surveyId")Integer surveyId);
	
	//取消发布  status 1->2
	void CancelSurvey();
	
	//发布 0->1, 2->1
	void updatePollSurveyById(Integer surveyId);
	
	//通过status 查询  正在发布
	PollSurvey findPollSurveyByStatus(@Param("status")Integer status);
}
