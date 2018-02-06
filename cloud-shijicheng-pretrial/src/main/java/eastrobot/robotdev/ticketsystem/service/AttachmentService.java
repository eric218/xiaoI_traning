package eastrobot.robotdev.ticketsystem.service;

import java.util.List;
import java.util.Map;

import eastrobot.robotdev.ticketsystem.model.Attachment;

public interface AttachmentService {

	List<Map<String, Object>> getFileByFormNo(Map<String, Object> selectParam);

	//新增附件
	Integer saveAttachment(Attachment attachment);
	
	//查询modeCode和modeId下的所有附件
	List<Attachment> findAttachmentsByModeIdAndModeCode(String modeCode,Integer modeId);
	
	//删除modeCode和modeId下的所有附件
	void deleteAttachmentsByModeIdAndModeCode(String modeCode,Integer modeId);

	List<Map<String, Object>> getFileByCodeAndId(Map<String, Object> fileSelectParam);

	//根据文件主键删除文件
	void deleteAttachmentById(int fileId);

	List<Map<String, Object>> getRecruitFiles(Map<String, Object> fileSelectParam);
	
}
