package eastrobot.robotdev.ticketsystem.dao;


import java.util.List;
import java.util.Map;


public interface NotifyMapper {

    List<Map<String, Object>> getAllNotify(Map<String, Object> map);

	Map<String, Object> getNotifyByFormNo(Integer formNo);

	void updateByFormNo(Map<String, Object> map);

	int getNotifyDetailCount(Map<String, Object> map);
	
	int selectDistinctTel();
}