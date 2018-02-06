/**
 * All rights Reserved
 * @Title: 	PollOptions.java 
 * @Package eastrobot.robotdev.ticketsystem.model 
 * @Description: 	TODO
 * @author:	 wwei4  
 * @date:	Oct 9, 2017 10:38:34 AM 
 * @version	V1.0   
 */
package eastrobot.robotdev.ticketsystem.model;

import java.io.Serializable;

/** 
 * @ClassName:	PollOptions 
 * @Description: 问卷调查问题选项 
 * @author:	wei wei
 * @date:	Oct 9, 2017 10:38:34 AM 
 *  
 */
public class PollOptions implements Serializable{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -711452674424279298L;
	
	//问题选项主键ID
	private Integer id;
	
	//问题ID
	private Integer topicId;
	
	//选项内容
	private String content;
	
	//选项顺序
	private Integer seq;
	
	//是否正确答案   1：是    0：否
	private Integer isAnswer;
	
	//选项类型  0：单选  1：多选  2：填空
	private Integer optType;

	public PollOptions() {
		super();
	}

	public PollOptions(Integer topicId, String content, Integer seq,
			Integer isAnswer, Integer optType) {
		super();
		this.topicId = topicId;
		this.content = content;
		this.seq = seq;
		this.isAnswer = isAnswer;
		this.optType = optType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getIsAnswer() {
		return isAnswer;
	}

	public void setIsAnswer(Integer isAnswer) {
		this.isAnswer = isAnswer;
	}

	public Integer getOptType() {
		return optType;
	}

	public void setOptType(Integer optType) {
		this.optType = optType;
	}
	
}
