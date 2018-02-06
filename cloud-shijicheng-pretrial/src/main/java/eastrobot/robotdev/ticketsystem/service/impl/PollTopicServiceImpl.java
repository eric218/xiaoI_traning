/**
 * All rights Reserved
 * @Title: 	PollTopicServiceImpl.java 
 * @Package eastrobot.robotdev.ticketsystem.service.impl 
 * @Description: 	TODO
 * @author:	 wwei4  
 * @date:	Oct 9, 2017 4:04:37 PM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.stereotype.Service;

import eastrobot.robotdev.ticketsystem.dao.PollTopicDao;
import eastrobot.robotdev.ticketsystem.model.PollTopic;
import eastrobot.robotdev.ticketsystem.service.PollTopicService;

/** 
 * @ClassName:	PollTopicServiceImpl 
 * @Description: TODO 
 * @author:	wei wei
 * @date:	Oct 9, 2017 4:04:37 PM 
 *  
 */
@Service("pollTopicService")
public class PollTopicServiceImpl implements PollTopicService {
	
	@Autowired
	private PollTopicDao pollTopicDao;

	@Override
	//新增问卷调查题目
	public Integer addPollTopic(PollTopic pollTopic) {
		pollTopicDao.addPollTopic(pollTopic);
		return pollTopic.getId();
	}

	@Override
	//通过surveyId查询当前问卷调查下所有的题目
	public List<PollTopic> findTopicsBySurveyId(Integer surveyId) {
		return pollTopicDao.findTopicsBySurveyId(surveyId);
	}

	@Override
	//通过surveyId删除问卷调查题目
	public void deleteTopicsBySurveyId(Integer surveyId) {
		pollTopicDao.deleteTopicsBySurveyId(surveyId);
	}

}
