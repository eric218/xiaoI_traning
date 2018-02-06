package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;
import java.util.Map;


public interface JobPersonMapper {

	List<Map<String, Object>> getAllPersonJob(Map<String, Object> map);

	Map<String, Object> getPersonDetailById(int jobId);
}