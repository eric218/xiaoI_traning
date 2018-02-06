/**
 * All rights Reserved
 * @Title: 	PollOptionsDao.java 
 * @Package eastrobot.robotdev.ticketsystem.dao 
 * @Description: 	TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 11:15:56 AM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import eastrobot.robotdev.ticketsystem.model.PollOptions;

/** 
 * @ClassName:	PollOptionsDao 
 * @Description:TODO 
 * @author:	wei wei
 * @date:	Oct 9, 2017 11:15:56 AM 
 *  
 */
public interface PollOptionsDao {

	//新增题目选项
	Integer addPollOptions(PollOptions pollOptions);
	
	//通过topicId查询当前题目下的所有选项
	List<PollOptions> findAllPollOptions(@Param("topicId")Integer topicId);
	
	//通过topicId删除当前题目下的所有选项
	void deletePollOptionsByTopicId(@Param("topicId")Integer topicId);
	
}
