package eastrobot.robotdev.ticketsystem.service;

import java.util.List;
import java.util.Map;

import eastrobot.robotdev.ticketsystem.model.JobCompany;

public interface JobCompanyService {

	List<Map<String, Object>> getAllCompJob(Map<String, Object> selectParam);

	Map<String, Object> getCompDetailById(int jobId);
	
	JobCompany getJobCompanyById(int jobId);

}
