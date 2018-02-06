/**
 * All rights Reserved
 * @Title: 	PollOptionsService.java 
 * @Package eastrobot.robotdev.ticketsystem.service 
 * @Description: 	TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 4:12:54 PM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.service;

import java.util.List;

import eastrobot.robotdev.ticketsystem.model.PollOptions;


/** 
 * @ClassName:	PollOptionsService 
 * @Description:TODO 
 * @author:	wei wei
 * @date:	Oct 9, 2017 4:12:54 PM 
 *  
 */
public interface PollOptionsService {
	
	//新增题目选项
	Integer addPollOptions(PollOptions pollOptions);
		
	//通过topicId查询当前题目下的所有选项
	List<PollOptions> findAllPollOptions(Integer topicId);
		
	//通过topicId删除当前题目下的所有选项
	void deletePollOptionsByTopicId(Integer topicId);
}
