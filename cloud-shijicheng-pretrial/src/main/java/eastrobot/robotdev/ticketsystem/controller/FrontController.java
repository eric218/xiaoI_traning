package eastrobot.robotdev.ticketsystem.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import eastrobot.robotdev.ticketsystem.constant.SessionConstant;
import eastrobot.robotdev.ticketsystem.constant.UriConstant;
import eastrobot.robotdev.ticketsystem.service.FrontService;
import eastrobot.robotdev.ticketsystem.utils.StringUtils;

@Controller
public class FrontController {
	private static Logger logger = Logger.getLogger(FrontController.class);
	@Autowired
	private FrontService frontService;
	
	@ResponseBody
	@RequestMapping( value = UriConstant.INPUT_DEMO)
	public ModelAndView inputDemo(){
		return new ModelAndView("front/inputDemo");
	}
	@ResponseBody
	@RequestMapping( value = "/test")
	public ModelAndView test(){
		return new ModelAndView("front/testupload");
	}
	
	@Value("#{setting.serverHost}")
	private String serverHost;
	@ResponseBody
    @RequestMapping(value=UriConstant.UPLOAD_API)
	public String handleFileUpload( @RequestParam(value = "UploadPic", required = false)MultipartFile file , String picClassify, String fileLength, HttpServletRequest request){
        
		//服务器绝对路径
    	String realPath = request.getSession().getServletContext().getRealPath(UriConstant.UPLOAD_IMG_PATH + File.separator + picClassify);
    	//计算URL
    	String path = request.getContextPath();		
	    //String relativePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
	    //新文件名
	    String newFileName = UUID.randomUUID().toString().replace("-", "")+".jpg";
	    
	    //原文件名
	    //String fileName = file.getOriginalFilename();
	    
	    //返回json对象
	    JSONObject json = new JSONObject();
    	try{
            //根据目录和文件名生成文件对象
            File newfile = new File(realPath, newFileName);
            if(newfile.exists()){
            	//创建失败
            	System.out.println("文件已存在");
            }else{
            	newfile.getParentFile().mkdirs();
            	try{
            		newfile.createNewFile();
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            }
	    	//生成jpeg图片  
            file.transferTo(newfile);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	String returnUrl = serverHost + path + UriConstant.UPLOAD_IMG_PATH + "/" + picClassify + "/" + newFileName;
    	json.put("type", "success");
    	json.put("imgUrl", returnUrl);
    	return json.toJSONString();
    }
	
	@ResponseBody
	@RequestMapping(value="/uploadDemo")
	public ModelAndView uploadDemo(){
		return new ModelAndView("front/uploaddemo");
	}
	@ResponseBody
	@RequestMapping(value="/front_{initJsonFileName}")
	public ModelAndView dynamicGenerate(@PathVariable String initJsonFileName,HttpSession session,HttpServletResponse response){
		if(session.getAttribute(SessionConstant.JSON_FILE_NAME) != null)
			session.removeAttribute(SessionConstant.JSON_FILE_NAME);

		session.setAttribute(SessionConstant.JSON_FILE_NAME, initJsonFileName);
	    response.setDateHeader("Expires", 0);      
	    response.setHeader("Cache-Control", "no-cache");      
	    response.setHeader("Pragma", "no-cache");      
	    response.setDateHeader("Expires", 0);    
	    response.setHeader("Cache-Control", "no-cache");    
	    response.setHeader("Pragma", "no-cache");   
		return new ModelAndView("front/dynamicGenerate");
	}
	
	@ResponseBody
	@RequestMapping(value="/initial.api")
	public String initialHtmlAPI(String uri,HttpServletResponse response){
		logger.info("uri:"+uri);
		String initPageJson = frontService.getInitPageJson(uri);
		logger.info("initJson:"+initPageJson);
		if(StringUtils.isBlank(initPageJson)){
			response.setStatus(404);
			return "";
		}else{
			return initPageJson;
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/recieveFormData.api")
	public String recieveFormData(String formDatas,HttpSession session){
		JSONObject json = new JSONObject();
		if(session.getAttribute(SessionConstant.APPLY_CACHE) != null && session.getAttribute(SessionConstant.APPLY_CACHE).toString().equals(formDatas.trim())){
			json.put("type", "failure");
			json.put("reason", "您的申请已提交成功，请勿重复提交！");
			return json.toJSONString();
		}
		//没有申请缓存或者缓存信息不同的情况
		session.setAttribute(SessionConstant.APPLY_CACHE, formDatas.trim());
		try{
			if(frontService.submitApply(formDatas)){
				
			}else{
				
			}
		}catch(Exception e){
			
		}
		return json.toJSONString();

	}
	
	@ResponseBody
	@RequestMapping(value="/sendVerify.api")
	public String sendVerify(String telNum){
		return telNum;
	}
	
	@RequestMapping(value = "/download.api") 
	public ResponseEntity<byte[]> download(String filename) throws IOException {
		String classpath = this.getClass().getClassLoader().getResource("/downloadfiles").getPath();
		String path = classpath + "/" + filename;
		File file = new File(path); 
		HttpHeaders headers = new HttpHeaders(); 
		String fileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题 
		headers.setContentDispositionFormData("attachment", fileName); 
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); 
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), 
	    headers, HttpStatus.CREATED); 
	 } 
}
