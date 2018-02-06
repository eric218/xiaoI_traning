/**
 * All rights Reserved
 * @Title: 	PollTopicDao.java 
 * @Package eastrobot.robotdev.ticketsystem.dao 
 * @Description: 	TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 11:14:10 AM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import eastrobot.robotdev.ticketsystem.model.PollTopic;


/** 
 * @ClassName:	PollTopicDao 
 * @Description:TODO 
 * @author:	wei wei
 * @date:	Oct 9, 2017 11:14:10 AM 
 *  
 */
public interface PollTopicDao {
	
	//新增问卷调查题目
	Integer addPollTopic(PollTopic pollTopic);
	
	//通过surveyId查询当前问卷调查下所有的题目
	List<PollTopic> findTopicsBySurveyId(@Param("surveyId")Integer surveyId);
	
	//通过surveyId删除问卷调查题目
	void deleteTopicsBySurveyId(@Param("surveyId")Integer surveyId);

}
