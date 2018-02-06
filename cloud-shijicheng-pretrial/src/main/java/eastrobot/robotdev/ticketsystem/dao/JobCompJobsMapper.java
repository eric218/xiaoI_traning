package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


public interface JobCompJobsMapper {

	List<Map<String, Object>> getCompJobsByJobNo(@Param("jobNo")String jobNo);
}