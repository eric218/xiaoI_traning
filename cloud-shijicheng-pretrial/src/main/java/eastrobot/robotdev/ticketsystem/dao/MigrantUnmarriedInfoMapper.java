package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;
import java.util.Map;


public interface MigrantUnmarriedInfoMapper {

	List<Map<String, Object>> getMaritalUnmarriedDetail(Map<String, Object> selectParam);
}