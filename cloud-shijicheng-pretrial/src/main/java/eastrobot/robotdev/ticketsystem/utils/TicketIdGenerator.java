package eastrobot.robotdev.ticketsystem.utils;

import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class TicketIdGenerator {
	private static int subId;
	/**
	 * @param args
	 */
	public TicketIdGenerator() {
		subId = 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketIdGenerator tg = new TicketIdGenerator();
		while(true){
			tg.generateId();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String generateId(){	
		Calendar calendar = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		//获取id前12位
		sb.append(String.valueOf(calendar.get(1)).substring(2))
		.append(String.format("%02d", calendar.get(2) + 1))
		.append(String.format("%02d", calendar.get(5)))
		.append(String.format("%02d", calendar.get(11)))
		.append(String.format("%02d", calendar.get(12)))
		.append(String.format("%02d", calendar.get(13)));
		//获取id后4位
		if(subId != 9999){
			sb.append(String.format("%04d", subId));
			subId++;
		}else{
			sb.append(String.format("%04d", subId));
			subId = 0;
		}
		
		return sb.toString();
	}
}
