package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import eastrobot.robotdev.ticketsystem.dao.JobCompanyMapper;
import eastrobot.robotdev.ticketsystem.model.JobCompany;
import eastrobot.robotdev.ticketsystem.service.JobCompanyService;

@Service
public class JobCompanyServiceImpl implements JobCompanyService{
	
	@Autowired
	private JobCompanyMapper jobCompanyMapper;

	@Override
	public List<Map<String, Object>> getAllCompJob(Map<String, Object> map) {
		PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageNumber"));
		List<Map<String, Object>> list=jobCompanyMapper.getAllCompJob(map);
		return list;
	}

	@Override
	public Map<String, Object> getCompDetailById(int jobId) {
		Map<String, Object> jobCompany=jobCompanyMapper.getCompDetailById(jobId);
		return jobCompany;
	}

	@Override
	public JobCompany getJobCompanyById(int jobId) {
		return jobCompanyMapper.selectByPrimaryKey(jobId);
	}

}
