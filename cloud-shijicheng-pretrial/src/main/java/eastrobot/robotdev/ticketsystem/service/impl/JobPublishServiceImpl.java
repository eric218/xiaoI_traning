package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import eastrobot.robotdev.ticketsystem.dao.JobPublishMapper;
import eastrobot.robotdev.ticketsystem.model.JobPublish;
import eastrobot.robotdev.ticketsystem.service.JobPublishService;

@Service
public class JobPublishServiceImpl implements JobPublishService{
	
	@Autowired
	private JobPublishMapper jobPublishMapper;

	@Override
	public List<Map<String, Object>> getAllPublishJob(Map<String, Object> map) {
		PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageNumber"));
		List<Map<String, Object>> list=jobPublishMapper.getAllPublishJob(map);
		return list;
	}

	@Override
	public Map<String, Object> getPublishDetailById(int jobId) {
		Map<String, Object> jobPublist=jobPublishMapper.getPublishDetailById(jobId);
		return jobPublist;
	}

	@Override
	public void insert(JobPublish jobPublish) {
		jobPublishMapper.insert(jobPublish);
	}

	@Override
	public void updateById(JobPublish jobPublish) {
		jobPublishMapper.updateByPrimaryKeySelective(jobPublish);
	}

	@Override
	public void deletePublishDetailById(int jobId) {
		jobPublishMapper.deletePublishDetailById(jobId);
	}

}
