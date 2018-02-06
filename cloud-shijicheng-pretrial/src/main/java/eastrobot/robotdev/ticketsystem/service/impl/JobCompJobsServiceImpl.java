package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eastrobot.robotdev.ticketsystem.dao.JobCompJobsMapper;
import eastrobot.robotdev.ticketsystem.service.JobCompJobsService;

@Service
public class JobCompJobsServiceImpl implements JobCompJobsService {
	
	@Autowired
	private JobCompJobsMapper jobCompJobsMapper;

	@Override
	public List<Map<String, Object>> getCompJobsByJobNo(String jobNo) {
		return jobCompJobsMapper.getCompJobsByJobNo(jobNo);
	}

}
