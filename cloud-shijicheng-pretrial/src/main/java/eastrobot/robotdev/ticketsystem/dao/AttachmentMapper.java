package eastrobot.robotdev.ticketsystem.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import eastrobot.robotdev.ticketsystem.model.Attachment;


public interface AttachmentMapper {

	//根据formNo查询附件列表
	List<Map<String, Object>> getFileByFormNo(Map<String, Object> selectParam);
	
	//新增附件
	Integer saveAttachment(Attachment attachment);
	
	//查询modeCode和modeId下的所有附件
	List<Attachment> findAttachmentsByModeIdAndModeCode(@Param("modeCode")String modeCode,@Param("modeId")Integer modeId);
	
	//删除modeCode和modeId下的所有附件
	void deleteAttachmentsByModeIdAndModeCode(@Param("modeCode")String modeCode,@Param("modeId")Integer modeId);

	//根据mode_code和mode_id查询附件列表
	List<Map<String, Object>> getFileByCodeAndId(Map<String, Object> fileSelectParam);

	//根据文件主键删除文件
	void deleteAttachmentById(@Param("fileId")int fileId);
}