package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import eastrobot.robotdev.ticketsystem.dao.JobPersonMapper;
import eastrobot.robotdev.ticketsystem.service.JobPersonService;

@Service
public class JobPersonServiceImpl implements JobPersonService {
	
	@Autowired
	private JobPersonMapper jobPersonMapper;

	@Override
	public List<Map<String, Object>> getAllPersonJob(Map<String, Object> map) {
		PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageNumber"));
		List<Map<String, Object>> list=jobPersonMapper.getAllPersonJob(map);
		return list;
	}

	@Override
	public Map<String, Object> getPersonDetailById(int jobId) {
		Map<String, Object> jobPerson=jobPersonMapper.getPersonDetailById(jobId);
		return jobPerson;
	}

}
