package eastrobot.robotdev.ticketsystem.service;

import java.util.List;
import java.util.Map;

public interface NotifyService {

	List<Map<String, Object>> getAllNotify(Map<String, Object> map);

	Map<String, Object> getNotifyByFormNo(Integer formNo);

	void updateByFormNo(Map<String, Object> map);

	int getNotifyDetailCount(Map<String, Object> map);
	
	int selectDistinctTel();
}
