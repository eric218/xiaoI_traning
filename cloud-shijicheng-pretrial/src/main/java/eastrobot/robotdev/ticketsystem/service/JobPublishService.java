package eastrobot.robotdev.ticketsystem.service;

import java.util.List;
import java.util.Map;

import eastrobot.robotdev.ticketsystem.model.JobPublish;

public interface JobPublishService {

	List<Map<String, Object>> getAllPublishJob(Map<String, Object> selectParam);

	Map<String, Object> getPublishDetailById(int jobId);

	void insert(JobPublish jobPublish);

	void updateById(JobPublish jobPublish);
	
	void deletePublishDetailById(int jobId);

}
