/**
 * All rights Reserved
 * @Title: 	PollReply.java 
 * @Package eastrobot.robotdev.ticketsystem.model 
 * @Description: TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 10:47:24 AM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.model;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName:	PollReply 
 * @Description: 问卷调查回复 
 * @author:	wei wei
 * @date:	Oct 9, 2017 10:47:24 AM 
 *  
 */
public class PollReply implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -4654276057586808333L;

	//回复主键ID
	private Integer id;
	
	//问卷调查ID
	private Integer surveyId;
	
	//问卷调查标题
	private String surveyTitle;
	
	//问题ID
	private Integer topicId;
	
	//问题名称
	private String topicTitle;
	
	//问题顺序
	private Integer topicSeq;
	
	//问题类型  0：单选  1：多选  2：填空
	private Integer topicType;
	
	//选项ID
	private Integer optionId;
	
	//选项内容
	private String optionContent;
	
	//选项顺序
	private Integer optionSeq;
	
	//用户ID(指的是手机用户ID)
	private String userId;
	
	//回复内容
	private String content;
	
	//回复时间
	private Date createTime;

	public PollReply() {
		super();
	}

	public PollReply(Integer surveyId, String surveyTitle, Integer topicId,
			String topicTitle, Integer topicSeq, Integer topicType,
			Integer optionId, String optionContent, Integer optionSeq,
			String userId, String content, Date createTime) {
		super();
		this.surveyId = surveyId;
		this.surveyTitle = surveyTitle;
		this.topicId = topicId;
		this.topicTitle = topicTitle;
		this.topicSeq = topicSeq;
		this.topicType = topicType;
		this.optionId = optionId;
		this.optionContent = optionContent;
		this.optionSeq = optionSeq;
		this.userId = userId;
		this.content = content;
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public String getSurveyTitle() {
		return surveyTitle;
	}

	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getTopicTitle() {
		return topicTitle;
	}

	public void setTopicTitle(String topicTitle) {
		this.topicTitle = topicTitle;
	}

	public Integer getTopicSeq() {
		return topicSeq;
	}

	public void setTopicSeq(Integer topicSeq) {
		this.topicSeq = topicSeq;
	}

	public Integer getTopicType() {
		return topicType;
	}

	public void setTopicType(Integer topicType) {
		this.topicType = topicType;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public Integer getOptionSeq() {
		return optionSeq;
	}

	public void setOptionSeq(Integer optionSeq) {
		this.optionSeq = optionSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
