package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;
import java.util.Map;


public interface MigrantMarriedInfoMapper {

	List<Map<String, Object>> getMaritalMarriedDetail(Map<String, Object> selectParam);
}