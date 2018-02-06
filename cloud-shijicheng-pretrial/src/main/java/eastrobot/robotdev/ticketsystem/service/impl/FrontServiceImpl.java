package eastrobot.robotdev.ticketsystem.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import eastrobot.robotdev.ticketsystem.dao.Tk_InfoDao;
import eastrobot.robotdev.ticketsystem.model.Tk_Info;
import eastrobot.robotdev.ticketsystem.service.FrontService;
import eastrobot.robotdev.ticketsystem.utils.TicketIdGenerator;

@Service
public class FrontServiceImpl implements FrontService {
	private static Logger logger = LogManager.getLogger(FrontServiceImpl.class);
	private static Map<String,String> frontPageInitJsonMap = new HashMap<String,String>();
	private static String classpath;
	
	public FrontServiceImpl(){
		classpath = this.getClass().getClassLoader().getResource("/frontJson").getPath();
		logger.info("classpath:"+classpath);
		List<File> jsonFiles = new ArrayList<File>();
		try {
			this.readfile(classpath,jsonFiles);
		} catch (Exception e) {
			logger.error("读取json文件失败!");
			logger.equals(e.getMessage());
		}
		for(File file : jsonFiles){
			if(file.getName().endsWith(".json")){
				String key = file.getName().substring(0,file.getName().indexOf(".json"));
				String content = null;
				try {
					content = FileUtils.readFileToString(file,"UTF-8");
				} catch (IOException e) {
					logger.error("读取"+file.getAbsolutePath()+"时出现异常");
					logger.error(e.getMessage());
				}
				if(content.length() > 0)
					frontPageInitJsonMap.put(key, content);
			}
		}
		
	}
	public String getInitPageJson(String jsonFileName) {
		return frontPageInitJsonMap.get(jsonFileName);
	}

	public boolean readfile(String filepath,List<File> jsonFiles) 
			throws FileNotFoundException, IOException {
        try {
	        File file = new File(filepath);
	        if (!file.isDirectory()) {
	        	jsonFiles.add(file);
	        } else if (file.isDirectory()) {
	            String[] filelist = file.list();
	            for (int i = 0; i < filelist.length; i++) {
	                File readfile = new File(filepath + File.separator + filelist[i]);
	                if (!readfile.isDirectory()) {
	                    jsonFiles.add(readfile);
	                } else if (readfile.isDirectory()) {
	                    readfile(filepath + File.separator + filelist[i],jsonFiles);
	                }
	            }
	        }
        } catch (FileNotFoundException e) {
            logger.error("readfile()   Exception:" + e.getMessage());
        }
        return true;
	}
	@Autowired
	private TicketIdGenerator generator;
	@Autowired
	private Tk_InfoDao tk_InfoDao;
	@Override
	public boolean submitApply(String formDatas) {
		//格式化数据为JSONObject
		JSONObject jsonData = JSONObject.parseObject(formDatas);
		//建立map放前端上传的数据
		Map<String,Object> nameValueMap = new HashMap<String, Object>();
		//获取功能ID
		String fnId = jsonData.getString("type");
		//获取姓名，联系电话和上传资料等数据
		for(Object nameValuePair : jsonData.getJSONArray("array")){
			String name = ((JSONObject)nameValuePair).getString("name");
			String value = ((JSONObject)nameValuePair).getString("value");
			String label = ((JSONObject)nameValuePair).getString("label");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", name);
			jsonObject.put("value", value);
			jsonObject.put("label", label);
			nameValueMap.put(name, jsonObject);
		}
		//构造存放到数据库的对象
		Tk_Info tk_Info = new Tk_Info();
		tk_Info.setTk_id(generator.generateId());
		tk_Info.setTk_name(((JSONObject)nameValueMap.get("realName")).getString("value"));
		nameValueMap.remove("realName");
		tk_Info.setTk_tel(((JSONObject)nameValueMap.get("telNum")).getString("value"));
		nameValueMap.remove("telNum");
		tk_Info.setTk_status(0);
		tk_Info.setTk_fn_id(Integer.parseInt(fnId));
		tk_Info.setTk_time(new java.sql.Time(System.currentTimeMillis()));
		tk_Info.setTk_date(new java.sql.Date(System.currentTimeMillis()));
		
		JSONArray array = new JSONArray();
		for(String key : nameValueMap.keySet()){
			array.add(nameValueMap.get(key));
		}
		tk_Info.setTk_content(JSONObject.toJSONString(array));
		//存放到数据库
		tk_InfoDao.insert(tk_Info);
		return true;
	}
}
