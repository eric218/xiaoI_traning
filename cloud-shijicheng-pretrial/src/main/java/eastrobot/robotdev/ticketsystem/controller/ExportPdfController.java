package eastrobot.robotdev.ticketsystem.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eastrobot.robotdev.ticketsystem.service.PdfService;

/**
 * export pdf controller
 * @author luotao
 *
 */
@Controller
@RequestMapping(value = "/exportPdf") 
public class ExportPdfController {
	private static Logger logger = Logger.getLogger(ExportPdfController.class);
	
	@Autowired
	private PdfService pdfService;
	
	/**
	 * export unmarried info with pdf file format
	 * @param formNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/migrantUnMarried") 
	public ResponseEntity<byte[]> migrantUnMarried(@RequestParam(value="formNo")Integer formNo) throws IOException {
		logger.info("start export migrant unmarried pdf file.");
		
		HttpHeaders headers = new HttpHeaders(); 
//		String fileName = new String("流动人口婚育证明.pdf".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题 
		headers.setContentDispositionFormData("attachment", URLEncoder.encode("流动人口婚育证明.pdf","utf-8"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		logger.info("end export migrant unmarried pdf file.");
		return new ResponseEntity<byte[]>(pdfService.getMigrantUnMarried(formNo), 
	    headers, HttpStatus.OK);
	 }
	
	/**
	 * export married info with pdf file format
	 * @param formNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/migrantMarried") 
	public ResponseEntity<byte[]> migrantMarried(@RequestParam(value="formNo")Integer formNo) throws IOException {
		logger.info("start export migrant married pdf file.");
		
		HttpHeaders headers = new HttpHeaders(); 
//		String fileName = new String("流动人口婚育证明.pdf".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题 
		headers.setContentDispositionFormData("attachment", URLEncoder.encode("流动人口婚育证明.pdf","utf-8"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		logger.info("end export migrant married pdf file.");
		return new ResponseEntity<byte[]>(pdfService.getMigrantMarried(formNo), 
	    headers, HttpStatus.OK);
	 }
	
	/**
	 * export preproductive civics info with pdf file format
	 * @param formNo
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/reproductiveCivics") 
	public ResponseEntity<byte[]> reproductiveCivics(@RequestParam(value="formNo")Integer formNo) throws IOException {
		logger.info("start export reproductive civics pdf file.");
		
		HttpHeaders headers = new HttpHeaders(); 
//		String fileName = new String("生殖保健服务证.pdf".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题 
		headers.setContentDispositionFormData("attachment", URLEncoder.encode("生殖保健服务证.pdf","utf-8"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		logger.info("end export reproductive civics pdf file.");
		return new ResponseEntity<byte[]>(pdfService.getPreproductiveCivics(formNo), 
	    headers, HttpStatus.OK);
	 }
	
	/**
	 * export company recruitment info with pdf file format
	 * @param jobid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/companyRecruitment") 
	public ResponseEntity<byte[]> companyRecruitment(@RequestParam(value="jobId")Integer jobId) throws IOException {
		logger.info("start export company recruitment pdf file.");
		
		HttpHeaders headers = new HttpHeaders(); 
//		String fileName = new String("招聘登记表.pdf".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题 
		headers.setContentDispositionFormData("attachment", URLEncoder.encode("招聘登记表.pdf","utf-8"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		logger.info("end export company recruitment pdf file.");
		return new ResponseEntity<byte[]>(pdfService.getCompanyRecruitmentDetail(jobId), 
	    headers, HttpStatus.OK);
	 }
	
	/**
	 * export personal job hunting info with pdf file format
	 * @param jobid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/personalJobHunting") 
	public ResponseEntity<byte[]> personalJobHunting(@RequestParam(value="jobId")Integer jobId) throws IOException {
		logger.info("start personal job hunting pdf file.");
		
		HttpHeaders headers = new HttpHeaders(); 
//		String fileName = new String("求职登记表.pdf".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题 
		headers.setContentDispositionFormData("attachment", URLEncoder.encode("求职登记表.pdf","utf-8"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		logger.info("end export personal job hunting pdf file.");
		return new ResponseEntity<byte[]>(pdfService.getPersonalJobHuntingDetail(jobId), 
	    headers, HttpStatus.OK);
	 }
	
	/**
	 * export poll info with pdf file format
	 * @param surveyId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/poll") 
	public ResponseEntity<byte[]> poll(@RequestParam(value="surveyId")Integer surveyId, HttpServletRequest request) throws IOException {
		logger.info("start export poll pdf file.");
		
		HttpHeaders headers = new HttpHeaders(); 
//		String fileName = new String("世纪城社区服务满意度调查统计.pdf".getBytes("UTF-8"), "iso-8859-1");// 为了解决中文名称乱码问题 
		headers.setContentDispositionFormData("attachment", URLEncoder.encode("世纪城社区服务满意度调查统计.pdf","utf-8"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		
		logger.info("end export poll pdf file.");
		return new ResponseEntity<byte[]>(pdfService.getPollDetail(surveyId), 
	    headers, HttpStatus.OK);
	 }
}
