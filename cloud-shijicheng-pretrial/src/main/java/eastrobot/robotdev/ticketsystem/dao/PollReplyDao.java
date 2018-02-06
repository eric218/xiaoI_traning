/**
 * All rights Reserved
 * @Title: PollReplyDao.java
 * @Package eastrobot.robotdev.ticketsystem.dao
 * @Description: TODO
 * @author: wei wei  
 * @date:	Oct 9, 2017 11:17:45 AM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;

import eastrobot.robotdev.ticketsystem.model.PollReply;
import eastrobot.robotdev.ticketsystem.model.vo.DbReturnOption;
import eastrobot.robotdev.ticketsystem.model.vo.DbReturnTopic;

/** 
 * @ClassName: PollReplyDao
 * @Description:TODO
 * @author: wei wei
 * @date:	Oct 9, 2017 11:17:45 AM 
 *  
 */
public interface PollReplyDao {
	int deleteByPrimaryKey(Integer id);

    int insert(PollReply record);

    int insertSelective(PollReply record);

    PollReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PollReply record);

    int updateByPrimaryKey(PollReply record);
	
    List<DbReturnOption> findDbReturnOptionByTopicId(Integer topicId) throws Exception;

    List<DbReturnTopic> findDbReturnTopicByTopicId(Integer topicId) throws Exception;

    List<DbReturnTopic> findDbReturnTopicAllByTopicId(Integer topicId) throws Exception;

    List<DbReturnOption> findDbReturnOptionAllByTopicId(Integer topicId) throws Exception;
    
    //删除问卷ID下的所有回复
    void deletePollReplyBySurveyId(Integer surveyId);
}
