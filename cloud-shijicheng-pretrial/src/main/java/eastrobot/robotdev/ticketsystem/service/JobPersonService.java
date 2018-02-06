package eastrobot.robotdev.ticketsystem.service;

import java.util.List;
import java.util.Map;

public interface JobPersonService {

	List<Map<String, Object>> getAllPersonJob(Map<String, Object> selectParam);

	Map<String, Object> getPersonDetailById(int jobId);

}
