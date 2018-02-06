package eastrobot.robotdev.ticketsystem.model.vo;

import java.util.List;

/**
 * 民调题目-jsp
 * @author mli8
 *
 */
public class PollTopicVo {
	//id
	private Integer id;
	
	//题目标题
	private String opts_title;
	
	//题目类型   0:单选  1:多选  2:填空
	private String topicTypeNum;
	
	//题目选项--集合
	private List<PollOptVo> surveyOptVos;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopicTypeNum() {
		return topicTypeNum;
	}

	public void setTopicTypeNum(String topicTypeNum) {
		this.topicTypeNum = topicTypeNum;
	}

	public String getOpts_title() {
		return opts_title;
	}

	public void setOpts_title(String opts_title) {
		this.opts_title = opts_title;
	}

	public List<PollOptVo> getSurveyOptVos() {
		return surveyOptVos;
	}

	public void setSurveyOptVos(List<PollOptVo> surveyOptVos) {
		this.surveyOptVos = surveyOptVos;
	}
	
}
