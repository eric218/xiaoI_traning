package eastrobot.robotdev.ticketsystem.service;

import java.util.List;

import eastrobot.robotdev.ticketsystem.model.vo.Record;

/**
 * Project:
 * Author: HONGMIN GAO
 * Date: 27/9/17
 * Descripthion: ...
 */
public interface PollReplyService {

    List<Record> findSurveyLists(Integer surveyId) throws Exception;
    
    //删除问卷ID下的所有回复
    void deletePollReplyBySurveyId(Integer surveyId);

}
