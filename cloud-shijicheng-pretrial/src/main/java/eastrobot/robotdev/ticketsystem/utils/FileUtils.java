package eastrobot.robotdev.ticketsystem.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import eastrobot.robotdev.ticketsystem.constant.CommonConstant;
import eastrobot.robotdev.ticketsystem.model.Attachment;

public class FileUtils {
	
	/**
	 * @Description: 获取服务器地址
	 * @param request
	 * @return  http://xxx.xxx.xxx.xxx:8080
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Oct 13, 2017 3:22:08 PM 
	 *
	 */
	public static String getServerPath(HttpServletRequest request){
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	}
	
	/**
	 * @Description: 区分测试环境和生产环境
	 * @param evn
	 * @param request
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Oct 20, 2017 1:49:50 PM 
	 *
	 */
	public static String getServerPath(boolean env,HttpServletRequest request){
		String serverPath=null;
		if (env) {
			//product
			serverPath=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		}else {
			//test
			serverPath=request.getScheme() + "://" + CommonConstant.SERVER_HTTP_PATH + ":" + CommonConstant.SERVER_HTTP_PORT;
		}
		return serverPath;
	}
	
	
	/**
	 * @Description: 获取文件路径
	 * @param fileName  如\fileDate\recruit\2017\10\19\RECRUIT1508397580001.pdf
	 * @return  http://xxx.xxx.xxx.xxx:8080/
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @param request 
	 * @date: Oct 10, 2017 10:27:35 AM 
	 *
	 */
	public static String getRealPath(String fileName, HttpServletRequest request){
		String path=getServerPath(request)+File.separator+CommonConstant.FILE_PATH+File.separator+fileName;
		return path.replaceAll("\\", "/");
	}
	
	/**
	 * @Description: 区分带有文件夹路径与无文件夹路径的文件
	 * @param fileName
	 * @param filePath
	 * @param request
	 * @return
	 * @version: v1.1.0
	 * @author: xiangdong.she
	 * @date: Oct 20, 2017 1:29:48 PM 
	 *
	 */
	public static String getRealPath(String fileName,String filePath, HttpServletRequest request){
		String path;
		if (filePath==null||"".equals(filePath)) {
			//招聘相关的文件，带有文件夹路径 ，如\fileDate\recruit\2017\10\19\xxx.jpg
			path=getServerPath(CommonConstant.ENV,request)+fileName;
		}else {
			//申请相关的文件，无文件夹路径，默认文件夹为upload,可以通过逻辑操作传过来
			path=getServerPath(CommonConstant.ENV,request)+File.separator+filePath+File.separator+fileName;
		}
		return path.replaceAll("\\\\", "/");
	}

	/**
	 * @Description: 保存附件到本地
	 * @param request
	 * @param myfile
	 * @param fileType
	 * @param modelCode
	 * @return
	 * @version: v1.1.0
	 * @author: wei wei
	 * @throws Exception 
	 * @date: Oct 10, 2017 11:27:35 AM 
	 *
	 */
	public static Attachment upload(HttpServletRequest request, MultipartFile myfile, String fileType, String modelCode) throws Exception {
		Attachment attachment = new Attachment();
		String realPath = request.getSession().getServletContext().getRealPath(File.separator);
		System.out.println("1--" + realPath);
		realPath = realPath.substring(0, realPath.length() - 1);
		System.out.println("2--" + realPath);
		int aString = realPath.lastIndexOf(File.separator);
		System.out.println("3--" + aString);

		int type = myfile.getOriginalFilename().lastIndexOf(".");
		String fileSuffix = myfile.getOriginalFilename().substring(type, myfile.getOriginalFilename().length());

		SimpleDateFormat year = new SimpleDateFormat("yyyy");
		SimpleDateFormat month = new SimpleDateFormat("MM");
		SimpleDateFormat day = new SimpleDateFormat("dd");
		Date date = new Date();

		realPath = realPath.substring(0, aString) +File.separator +"fileDate"+File.separator + fileType + File.separator + year.format(date) + File.separator
				+ month.format(date) + File.separator + day.format(date) + File.separator;
		File folder = new File(realPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		String newName = System.currentTimeMillis() + fileSuffix;
		realPath = realPath + modelCode + newName;

		myfile.transferTo(new File(realPath));
		
		String fileAddress = File.separator+"fileDate" +File.separator + fileType + File.separator + 
				year.format(date) + File.separator + month.format(date) + File.separator + day.format(date) + File.separator +
				modelCode + newName;
		attachment.setFileName(myfile.getOriginalFilename());
		attachment.setFileAddr(fileAddress);
		return attachment;
	}
	
	/**
	 * @Description: 删除附件
	 * @param request
	 * @param filePath
	 * @return
	 * @version: v1.1.0
	 * @author: wei wei
	 * @throws Exception 
	 * @date: Oct 10, 2017 11:35:35 AM 
	 *
	 */
	public static void deleteFiles(HttpServletRequest request, List<String> filePath) {
		String realPath = request.getSession().getServletContext().getRealPath(File.separator);
		realPath = realPath.substring(0, realPath.length() - 1);
		int aString = realPath.lastIndexOf(File.separator);
		realPath = realPath.substring(0, aString);

		if (filePath != null && !filePath.isEmpty()) {
			for (String path : filePath) {
				File file = new File(realPath + path);
				System.out.println("path==" + realPath + path);
				if (file.exists()) {
					file.delete();
					System.out.println("成功删除文件");
				}
			}
		}
	}
	
}
