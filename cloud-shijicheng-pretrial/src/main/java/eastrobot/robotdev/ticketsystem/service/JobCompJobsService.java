package eastrobot.robotdev.ticketsystem.service;

import java.util.List;
import java.util.Map;

public interface JobCompJobsService {

	List<Map<String, Object>> getCompJobsByJobNo(String jobNo);

}
