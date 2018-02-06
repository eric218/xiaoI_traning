package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eastrobot.robotdev.ticketsystem.dao.MigrantMarriedInfoMapper;
import eastrobot.robotdev.ticketsystem.service.MigrantMarriedService;

@Service
public class MigrantMarriedServiceImpl implements MigrantMarriedService{
	
	@Autowired
	private MigrantMarriedInfoMapper  marriedInfoMapper;

	@Override
	public List<Map<String, Object>> getMaritalMarriedDetail(Map<String, Object> selectParam) {
		List<Map<String, Object>> list=marriedInfoMapper.getMaritalMarriedDetail(selectParam);
		return list;
	}

}
