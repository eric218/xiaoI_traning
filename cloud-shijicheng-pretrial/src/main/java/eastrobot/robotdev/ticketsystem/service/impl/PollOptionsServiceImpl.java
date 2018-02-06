/**
 * All rights Reserved
 * @Title: 	PollOptionsServiceImpl.java 
 * @Package eastrobot.robotdev.ticketsystem.service.impl 
 * @Description: 	TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 4:14:09 PM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eastrobot.robotdev.ticketsystem.dao.PollOptionsDao;
import eastrobot.robotdev.ticketsystem.model.PollOptions;
import eastrobot.robotdev.ticketsystem.service.PollOptionsService;

/** 
 * @ClassName:	PollOptionsServiceImpl 
 * @Description:TODO 
 * @author:	wei wei
 * @date:	Oct 9, 2017 4:14:09 PM 
 *  
 */
@Service("pollOptionsService")
public class PollOptionsServiceImpl implements PollOptionsService {

	@Autowired
	private PollOptionsDao pollOptionsDao;

	@Override
	//新增题目选项
	public Integer addPollOptions(PollOptions pollOptions) {
		pollOptionsDao.addPollOptions(pollOptions);
		return pollOptions.getId();
	}

	@Override
	//通过topicId查询当前题目下的所有选项
	public List<PollOptions> findAllPollOptions(Integer topicId) {
		return pollOptionsDao.findAllPollOptions(topicId);
	}

	@Override
	//通过topicId删除当前题目下的所有选项
	public void deletePollOptionsByTopicId(Integer topicId) {
		pollOptionsDao.deletePollOptionsByTopicId(topicId);
	}

}
