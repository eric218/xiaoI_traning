/**
 * All rights Reserved
 * @Title: 	PollSurveyServiceImpl.java 
 * @Package com.ibot.gridtraining.service.impl 
 * @Description: 	TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 3:53:45 PM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eastrobot.robotdev.ticketsystem.dao.PollSurveyDao;
import eastrobot.robotdev.ticketsystem.model.PollSurvey;
import eastrobot.robotdev.ticketsystem.service.PollSurveyService;

/** 
 * @ClassName:	PollSurveyServiceImpl 
 * @Description:TODO 
 * @author:	wei wei
 * @date:	Oct 9, 2017 3:53:45 PM 
 *  
 */
@Service("pollSurveyService")
public class PollSurveyServiceImpl implements PollSurveyService {
	
	@Autowired
	private PollSurveyDao pollSurveyDao;

	@Override
	//分页查询问卷调查
	public List<PollSurvey> queryForPage(Integer pageSize, Integer pageNumber) {
		PollSurvey pollSurvey = pollSurveyDao.findPollSurveyByStatus(1);
		
		if(pollSurvey != null){
			if(pageNumber ==1){
				List<PollSurvey> listA = pollSurveyDao.queryForPage(false,(pageNumber-1)*pageSize, pageSize-1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				pollSurvey.setSurveyTime(sdf.format(pollSurvey.getStartTime())+" - "+sdf.format(pollSurvey.getEndTime()));
				listA.add(0, pollSurvey);
				return listA;
			}else{
				Integer firstNum = (pageNumber-1)*pageSize;
				if(pageNumber != 1){
					firstNum = ((pageNumber-1)*pageSize) - 1;
				}
				List<PollSurvey> listA = pollSurveyDao.queryForPage(false,firstNum, pageSize);
				return listA;
			}
		}else{
			
			List<PollSurvey> listB = pollSurveyDao.queryForPage(true,(pageNumber-1)*pageSize, pageSize);
			return listB;
		}
		
	}

	@Override
	//查询总记录条数
	public int getSurveyCount() {
		return pollSurveyDao.getSurveyCount();
	}

	@Override
	//新增问卷调查
	public Integer addPollSurvey(PollSurvey pollSurvey) {
		pollSurveyDao.addPollSurvey(pollSurvey);
		return pollSurvey.getId();
	}

	@Override
	//通过主键ID查询问卷调查
	public PollSurvey findPollSurveyById(Integer surveyId) {
		return pollSurveyDao.findPollSurveyById(surveyId);
	}

	@Override
	//删除问卷调查
	public void deleteSurvey(Integer surveyId) {
		pollSurveyDao.deleteSurvey(surveyId);
	}

	@Override
	public void CancelSurvey() {
		pollSurveyDao.CancelSurvey();
	}

	@Override
	public void updatePollSurveyById(Integer surveyId) {
		pollSurveyDao.updatePollSurveyById(surveyId);
	}

}
