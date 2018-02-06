package eastrobot.robotdev.ticketsystem.model.vo;

import java.util.List;

/**
 * 民调对象-jsp
 * @author mli8
 *
 */
public class PollCreateVo {
	
	//id
	private Integer id;
	
	//民调开始时间
	private String datetimeStart;
	
	//民调结束时间
	private String datetimeEnd;
	
	//民调标题
	private String title;
	
	//民调描述
	private String des;
	
	//民调题目--集合
	private List<PollTopicVo> surveyTopicVos;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<PollTopicVo> getSurveyTopicVos() {
		return surveyTopicVos;
	}

	public void setSurveyTopicVos(List<PollTopicVo> surveyTopicVos) {
		this.surveyTopicVos = surveyTopicVos;
	}

	public String getDatetimeStart() {
		return datetimeStart;
	}

	public void setDatetimeStart(String datetimeStart) {
		this.datetimeStart = datetimeStart;
	}

	public String getDatetimeEnd() {
		return datetimeEnd;
	}

	public void setDatetimeEnd(String datetimeEnd) {
		this.datetimeEnd = datetimeEnd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
}
