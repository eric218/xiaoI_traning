package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;
import java.util.Map;

import eastrobot.robotdev.ticketsystem.model.JobPublish;

public interface JobPublishMapper {

    int insert(JobPublish record);

	List<Map<String, Object>> getAllPublishJob(Map<String, Object> map);

	Map<String, Object> getPublishDetailById(int jobId);
	
	int updateByPrimaryKeySelective(JobPublish record);
	
	void deletePublishDetailById(int jobId);
}