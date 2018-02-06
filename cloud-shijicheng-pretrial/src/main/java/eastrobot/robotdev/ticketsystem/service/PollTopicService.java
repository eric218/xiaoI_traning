/**
 * All rights Reserved
 * @Title: 	PollTopicService.java 
 * @Package eastrobot.robotdev.ticketsystem.service 
 * @Description: TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 4:02:27 PM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.service;

import java.util.List;

import eastrobot.robotdev.ticketsystem.model.PollTopic;

/** 
 * @ClassName:	PollTopicService 
 * @Description:TODO 
 * @author:	wei wei
 * @date:	Oct 9, 2017 4:02:27 PM 
 *  
 */
public interface PollTopicService {

	//新增问卷调查题目
	Integer addPollTopic(PollTopic pollTopic);
		
	//通过surveyId查询当前问卷调查下所有的题目
	List<PollTopic> findTopicsBySurveyId(Integer surveyId);
		
	//通过surveyId删除问卷调查题目
	void deleteTopicsBySurveyId(Integer surveyId);
	
}
