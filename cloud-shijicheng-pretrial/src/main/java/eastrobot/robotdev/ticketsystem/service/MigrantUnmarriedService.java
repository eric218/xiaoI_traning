package eastrobot.robotdev.ticketsystem.service;

import java.util.List;
import java.util.Map;

public interface MigrantUnmarriedService {

	List<Map<String, Object>> getMaritalUnmarriedDetail(Map<String, Object> selectParam);

}
