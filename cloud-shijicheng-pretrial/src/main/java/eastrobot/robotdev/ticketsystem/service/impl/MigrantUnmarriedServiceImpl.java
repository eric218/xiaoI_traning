package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import eastrobot.robotdev.ticketsystem.dao.MigrantUnmarriedInfoMapper;
import eastrobot.robotdev.ticketsystem.service.MigrantUnmarriedService;

@Service
public class MigrantUnmarriedServiceImpl implements MigrantUnmarriedService {

	@Autowired
	private MigrantUnmarriedInfoMapper UnmarriedInfoMapper;

	@Override
	public List<Map<String, Object>> getMaritalUnmarriedDetail(Map<String, Object> selectParam) {
		List<Map<String, Object>> list=UnmarriedInfoMapper.getMaritalUnmarriedDetail(selectParam);
		return list;
	}
	
}
