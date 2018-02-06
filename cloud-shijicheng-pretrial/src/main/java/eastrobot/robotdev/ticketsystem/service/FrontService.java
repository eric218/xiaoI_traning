package eastrobot.robotdev.ticketsystem.service;

public interface FrontService {
	public String getInitPageJson(String jsonFileName);
	
	public boolean submitApply(String formDatas);
}
