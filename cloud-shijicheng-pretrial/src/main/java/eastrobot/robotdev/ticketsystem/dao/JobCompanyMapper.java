package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;
import java.util.Map;

import eastrobot.robotdev.ticketsystem.model.JobCompany;


public interface JobCompanyMapper {

	List<Map<String, Object>> getAllCompJob(Map<String, Object> map);

	Map<String, Object> getCompDetailById(int jobId);
	
	JobCompany selectByPrimaryKey(Integer id);
}