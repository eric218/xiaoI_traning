package eastrobot.robotdev.ticketsystem.model.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * 民调题目选项
 * @author mli8
 *
 */
public class PollOptVo {
	//id
	private Integer id;
	
	//正确答案  1：是  0:否
	private String ans;
	
	//选项内容
	private String optValue;
	
	//选项文件
	private MultipartFile myfile;
	
	//图片路径----修改jsp用，返回给前端显示图片
	private String picSrc;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPicSrc() {
		return picSrc;
	}

	public void setPicSrc(String picSrc) {
		this.picSrc = picSrc;
	}

	public MultipartFile getMyfile() {
		return myfile;
	}

	public void setMyfile(MultipartFile myfile) {
		this.myfile = myfile;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getOptValue() {
		return optValue;
	}

	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}
}
