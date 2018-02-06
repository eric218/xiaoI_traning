package eastrobot.robotdev.ticketsystem.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eastrobot.robotdev.ticketsystem.constant.CommonConstant;
import eastrobot.robotdev.ticketsystem.dao.AttachmentMapper;
import eastrobot.robotdev.ticketsystem.model.Attachment;
import eastrobot.robotdev.ticketsystem.service.AttachmentService;
import eastrobot.robotdev.ticketsystem.utils.FileUtils;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public List<Map<String, Object>> getFileByFormNo(Map<String, Object> selectParam) {
		List<Map<String, Object>> list=attachmentMapper.getFileByFormNo(selectParam);
		
		List<Map<String, Object>> result=new LinkedList<>();
		
		for (Map<String, Object> map : list) {
			String fileAddr=(String)map.get("fileAddr");
			String[] fileAddrArray=fileAddr.split(";");
			for (int i = 0; i < fileAddrArray.length; i++) {
				fileAddrArray[i]=FileUtils.getRealPath(fileAddrArray[i],CommonConstant.FILE_PATH,request);
			}
			map.put("fileAddr", fileAddrArray);
			result.add(map);
		}
		
		return result;
	}

	@Override
	//新增附件
	public Integer saveAttachment(Attachment attachment) {
		attachmentMapper.saveAttachment(attachment);
		return attachment.getId();
	}

	@Override
	//查询modeCode和modeId下的所有附件
	public List<Attachment> findAttachmentsByModeIdAndModeCode(String modeCode, Integer modeId) {
		return attachmentMapper.findAttachmentsByModeIdAndModeCode(modeCode, modeId);
	}

	@Override
	//删除modeCode和modeId下的所有附件
	public void deleteAttachmentsByModeIdAndModeCode(String modeCode, Integer modeId) {
		attachmentMapper.deleteAttachmentsByModeIdAndModeCode(modeCode, modeId);
	}

	@Override
	public List<Map<String, Object>> getFileByCodeAndId(Map<String, Object> fileSelectParam) {
		List<Map<String, Object>> list=attachmentMapper.getFileByCodeAndId(fileSelectParam);
		
		List<Map<String, Object>> result=new LinkedList<>();
		
		for (Map<String, Object> map : list) {
			String fileAddr=(String)map.get("fileAddr");
			String[] fileAddrArray=fileAddr.split(";");
			for (int i = 0; i < fileAddrArray.length; i++) {
				fileAddrArray[i]=FileUtils.getRealPath(fileAddrArray[i],null,request);
			}
			map.put("fileAddr", fileAddrArray);
			result.add(map);
		}
		return result;
	}

	@Override
	public void deleteAttachmentById(int fileId) {
		attachmentMapper.deleteAttachmentById(fileId);
	}

	@Override
	public List<Map<String, Object>> getRecruitFiles(Map<String, Object> fileSelectParam) {
		List<Map<String, Object>> list=attachmentMapper.getFileByCodeAndId(fileSelectParam);
		List<Map<String, Object>> result=new LinkedList<>();
		
		for (Map<String, Object> map : list) {
			String fileAddr=(String)map.get("fileAddr");
			String[] fileAddrArray=fileAddr.split(";");
			for (int i = 0; i < fileAddrArray.length; i++) {
				fileAddrArray[i]=FileUtils.getRealPath(fileAddrArray[i],CommonConstant.FILE_PATH,request);
			}
			map.put("fileAddr", fileAddrArray);
			result.add(map);
		}
		return result;
	}

}
