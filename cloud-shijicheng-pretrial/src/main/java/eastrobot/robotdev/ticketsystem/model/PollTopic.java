/**
 * All rights Reserved
 * @Title: 	PollTopic.java 
 * @Package eastrobot.robotdev.ticketsystem.model 
 * @Description: TODO
 * @author:	 wei wei  
 * @date:	Oct 9, 2017 10:35:12 AM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.model;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName:	PollTopic 
 * @Description: 问卷调查问题 
 * @author:	wei wei
 * @date:	Oct 9, 2017 10:35:12 AM 
 *  
 */
public class PollTopic implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -6080192716651433008L;
	
	//问题主键ID
	private Integer id;
	
	//问卷调查ID
	private Integer surveyId;
	
	//题目名称
	private String title;
	
	//题目顺序
	private Integer seq;
	
	//题目类型
	private Integer type;
	
	//创建者
	private String creator;
	
	//创建时间
	private Date createTime;
	
	//修改人
	private String modifier;
	
	//修改时间
	private Date updateTime;

	public PollTopic() {
		super();
	}

	public PollTopic(Integer surveyId, String title, Integer seq, Integer type,
			String creator, Date createTime, String modifier, Date updateTime) {
		super();
		this.surveyId = surveyId;
		this.title = title;
		this.seq = seq;
		this.type = type;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.updateTime = updateTime;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
