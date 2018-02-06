/**
 * All rights Reserved
 * @Title: 	PollSurvey.java 
 * @Package eastrobot.robotdev.ticketsystem.model 
 * @Description: TODO
 * @author:	 wei wei  
 * @date: Oct 9, 2017 10:22:36 AM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.model;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName:	PollSurvey 
 * @Description: 问卷调查 
 * @author:	wei wei
 * @date: Oct 9, 2017 10:22:36 AM 
 *  
 */
public class PollSurvey implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -5493056007291773507L;
	
	//问卷调查主键ID
	private Integer id;
		
	//问卷调查标题
	private String title;
		
	//问卷调查描述
	private String description;
		
	//问卷调查开始时间
	private Date startTime;
		
	//问卷调查结束时间
	private Date endTime;
		
	//问卷调查创建人
	private String creator;
		
	//问卷调查创建时间
	private Date createTime;
		
	//问卷调查修改人
	private String modifier;
		
	//问卷调查修改时间
	private Date modifyTime;
		
	//问卷调查状态  0：未发布  1：发布  2：取消发布
	private Integer status;
		
	//调查时间
	private String surveyTime;
	
	public PollSurvey() {
		super();
	}

	public PollSurvey(String title, String description, Date startTime,
			Date endTime, String creator, Date createTime, String modifier,
			Date modifyTime, Integer status) {
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.creator = creator;
		this.createTime = createTime;
		this.modifier = modifier;
		this.modifyTime = modifyTime;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSurveyTime() {
		return surveyTime;
	}

	public void setSurveyTime(String surveyTime) {
		this.surveyTime = surveyTime;
	}
	
}
