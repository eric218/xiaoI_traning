package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eastrobot.robotdev.ticketsystem.dao.ReproductiveCivicsInfoMapper;
import eastrobot.robotdev.ticketsystem.service.ReproductiveCivicsService;

@Service
public class ReproductiveCivicsServiceImpl implements ReproductiveCivicsService{
	
	@Autowired
	private ReproductiveCivicsInfoMapper infoMapper;

	@Override
	public List<Map<String, Object>> getMaritalMarriedDetail(Map<String, Object> selectParam) {
		List<Map<String, Object>> list=infoMapper.getMaritalMarriedDetail(selectParam);
		return list;
	}

}
