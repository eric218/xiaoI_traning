package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import eastrobot.robotdev.ticketsystem.dao.NotifyMapper;
import eastrobot.robotdev.ticketsystem.service.NotifyService;
import eastrobot.robotdev.ticketsystem.utils.DateUtils;

@Service
public class NotifyServiceImpl implements NotifyService {
	
	@Autowired
	private NotifyMapper notifyMapper;

	@Override
	public List<Map<String, Object>> getAllNotify(Map<String, Object> map) {
		PageHelper.startPage((Integer)map.get("currentPage"), (Integer)map.get("pageNumber"));
		List<Map<String, Object>> list=notifyMapper.getAllNotify(map);
/*		List<Map<String, Object>> result=new LinkedList<>();
		for (Map<String, Object> map2 : list) {
			Date createTimeStr=(Date) map2.get("createTime");
			String createTime = null;
			createTime=DateUtils.formatFullTime(createTimeStr);
			map2.put("createTime", createTime);
			result.add(map2);
		}*/
		return list;
	}

	@Override
	public Map<String, Object> getNotifyByFormNo(Integer formNo) {
		return notifyMapper.getNotifyByFormNo(formNo);
	}

	@Override
	public void updateByFormNo(Map<String, Object> map) {
		notifyMapper.updateByFormNo(map);
	}

	@Override
	public int getNotifyDetailCount(Map<String, Object> map) {
		return notifyMapper.getNotifyDetailCount(map);
	}

	@Override
	public int selectDistinctTel() {
		return notifyMapper.selectDistinctTel();
	}


}
