package eastrobot.robotdev.ticketsystem.service.impl;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import eastrobot.robotdev.ticketsystem.constant.CommonConstant;
import eastrobot.robotdev.ticketsystem.model.vo.Option;
import eastrobot.robotdev.ticketsystem.model.vo.Record;
import eastrobot.robotdev.ticketsystem.service.AttachmentService;
import eastrobot.robotdev.ticketsystem.service.JobCompJobsService;
import eastrobot.robotdev.ticketsystem.service.JobCompanyService;
import eastrobot.robotdev.ticketsystem.service.JobPersonService;
import eastrobot.robotdev.ticketsystem.service.MigrantMarriedService;
import eastrobot.robotdev.ticketsystem.service.MigrantUnmarriedService;
import eastrobot.robotdev.ticketsystem.service.PdfService;
import eastrobot.robotdev.ticketsystem.service.PollReplyService;
import eastrobot.robotdev.ticketsystem.service.ReproductiveCivicsService;
import eastrobot.robotdev.ticketsystem.utils.PdfCssUtils;
import eastrobot.robotdev.ticketsystem.utils.StringUtils;

/**
 * pdf service
 * 
 * @author luotao
 *
 */
@Service
public class PdfServiceImpl implements PdfService {
	@Autowired
	private MigrantUnmarriedService unMarriedService;

	@Autowired
	private MigrantMarriedService marriedService;

	@Autowired
	private ReproductiveCivicsService reproductiveCivicsService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private JobCompanyService jobCompanyService;

	@Autowired
	private JobCompJobsService jobCompJobsService;

	@Autowired
	private JobPersonService jobPersonService;

	@Autowired
	private PollReplyService pollReplyService;

	/**
	 * get unmarried info
	 */
	@Override
	public byte[] getMigrantUnMarried(Integer muId) {
		try {
			Map<String, Object> selectParam = new HashMap<>();
			selectParam.put("notifyFormId", muId);

			// get migrant unmarried info
			Map<String, Object> migrantUnMarriedInfo = new HashMap<>();
			migrantUnMarriedInfo = unMarriedService.getMaritalUnmarriedDetail(selectParam).get(0);

			// 新建document对象
			Document pdfDoc = new Document(PageSize.A4, 36, 36, 24, 36);
			// 构造好的pdf文件输出位置
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			PdfWriter.getInstance(pdfDoc, baos);
			// 打开pdf文件---注：只有document 打开后，才能往文件内写入导出信息
			pdfDoc.open();

			String fontBasePath = this.getClass().getClassLoader().getResource("/font").getPath();
			String imageBasePath = this.getClass().getClassLoader().getResource("/image").getPath();
			String dataFilesBasePath = this.getClass().getClassLoader().getResource("").getPath()
					.replace("WEB-INF/classes", "dataFiles");

			// PDFTable类似于html的表格文件，但是只有列的概念，定义列的数量，不能定义行的数量。
			PdfPTable infoTable = new PdfPTable(2);
			infoTable.setWidthPercentage(100);

			// 设置中文字体
			// BaseFont bfChinese = BaseFont.createFont("STSong-Light",
			// "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

			BaseFont bfChinese = BaseFont.createFont(fontBasePath + "simfang.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			Font FontHeader = new Font(bfChinese, 14, Font.NORMAL);
			Font Fontbody = new Font(bfChinese, 12, Font.NORMAL);

			PdfPCell infoHeaderCell = new PdfPCell(new Paragraph("流动人口婚育证明预审", FontHeader));
			PdfCssUtils.setHeaderFontStyleAlingCenter(infoHeaderCell);
			infoTable.addCell(infoHeaderCell);

			PdfPCell infoBodyCell = new PdfPCell(
					new Paragraph("预审单编号： "+String.valueOf(migrantUnMarriedInfo.get("notifyFormId")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeft(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			infoBodyCell = new PdfPCell(
					new Paragraph(formatter.format(migrantUnMarriedInfo.get("proposerTime")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRight(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("    基本信息", Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftWithBlueBackground(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("姓名：" + (StringUtils.isEmpty((String) migrantUnMarriedInfo.get("applicationName"))
							? "" : migrantUnMarriedInfo.get("applicationName")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("婚姻状况:" + (StringUtils.isEmpty((String) migrantUnMarriedInfo.get("martiaStatus")) ? ""
							: migrantUnMarriedInfo.get("martiaStatus")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRightHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			Image underLine = Image.getInstance(imageBasePath + "underline.png");
			// underLine.setWidthPercentage(30);
			// underLine.setScaleToFitHeight(true);
			// underLine.setScaleToFitLineWhenOverflow(false);
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("身份证号：" + (StringUtils.isEmpty((String) migrantUnMarriedInfo.get("applicationId"))
							? "" : migrantUnMarriedInfo.get("applicationId")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("出生日期：" + (StringUtils.isEmpty((String) migrantUnMarriedInfo.get("birthday")) ? ""
							: migrantUnMarriedInfo.get("birthday")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRightHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph(
					"身份证有效期：" + (StringUtils.isEmpty((String) migrantUnMarriedInfo.get("idCardExpiryDate")) ? ""
							: migrantUnMarriedInfo.get("idCardExpiryDate")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("户籍地址：" + (StringUtils.isEmpty((String) migrantUnMarriedInfo.get("regAddr")) ? ""
							: migrantUnMarriedInfo.get("regAddr")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("居住地址：" + (StringUtils.isEmpty((String) migrantUnMarriedInfo.get("domAddr")) ? ""
							: migrantUnMarriedInfo.get("domAddr")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("    提交材料", Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftWithBlueBackground(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			
			// 查询formId对应的附件信息
			List<Map<String, Object>> filesInfo = attachmentService.getFileByFormNo(selectParam);
			
			int j = 0;
			for (Map<String, Object> fileInfo : filesInfo) {
				j++;
				infoBodyCell = new PdfPCell(new Paragraph("附件" + j + "：" + fileInfo.get("fileName"), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2(infoBodyCell);
				infoTable.addCell(infoBodyCell);
			}
			
			pdfDoc.add(infoTable);
			
			PdfPTable adjunctTable;

			int i = 0;
			for (Map<String, Object> fileInfo : filesInfo) {
				pdfDoc.newPage();
				adjunctTable = new PdfPTable(1);
				adjunctTable.setWidthPercentage(100);
				i++;
				infoBodyCell = new PdfPCell(new Paragraph("附件" + i + "：" + fileInfo.get("fileName"), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2(infoBodyCell);
				adjunctTable.addCell(infoBodyCell);
				for (String fileAddr : (String[]) fileInfo.get("fileAddr")) {
					Image image = Image.getInstance(fileAddr);
					infoBodyCell = new PdfPCell(image);
					PdfCssUtils.setAdjunctStyle(infoBodyCell);
					image.setScaleToFitLineWhenOverflow(true);
					adjunctTable.addCell(infoBodyCell);
				}
				pdfDoc.add(adjunctTable);
			}

			// 关闭文档对象，注：当文档对象真正关闭后，数据才会写入文件中。
			pdfDoc.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * get married info
	 */
	@Override
	public byte[] getMigrantMarried(Integer muId) {
		try {
			Map<String, Object> selectParam = new HashMap<>();
			selectParam.put("notifyFormId", muId);

			// get migrant unmarried info
			Map<String, Object> migrantMarriedInfo = new HashMap<>();
			migrantMarriedInfo = marriedService.getMaritalMarriedDetail(selectParam).get(0);

			// 新建document对象
			Document pdfDoc = new Document(PageSize.A4, 36, 36, 24, 36);
			// 构造好的pdf文件输出位置
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			PdfWriter.getInstance(pdfDoc, baos);
			// 打开pdf文件---注：只有document 打开后，才能往文件内写入导出信息
			pdfDoc.open();

			String fontBasePath = this.getClass().getClassLoader().getResource("/font").getPath();
			String imageBasePath = this.getClass().getClassLoader().getResource("/image").getPath();
			String dataFilesBasePath = this.getClass().getClassLoader().getResource("").getPath()
					.replace("WEB-INF/classes", "dataFiles");

			// PDFTable类似于html的表格文件，但是只有列的概念，定义列的数量，不能定义行的数量。
			PdfPTable infoTable = new PdfPTable(2);
			infoTable.setWidthPercentage(100);

			// 设置中文字体
			// BaseFont bfChinese = BaseFont.createFont("STSong-Light",
			// "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

			BaseFont bfChinese = BaseFont.createFont(fontBasePath + "simfang.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			Font FontHeader = new Font(bfChinese, 14, Font.NORMAL);
			Font Fontbody = new Font(bfChinese, 12, Font.NORMAL);

			PdfPCell infoHeaderCell = new PdfPCell(new Paragraph("流动人口婚育证明预审", FontHeader));
			PdfCssUtils.setHeaderFontStyleAlingCenter(infoHeaderCell);
			infoTable.addCell(infoHeaderCell);

			PdfPCell infoBodyCell = new PdfPCell(
					new Paragraph("预审单编号： "+String.valueOf(migrantMarriedInfo.get("notifyFormId")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeft(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			infoBodyCell = new PdfPCell(
					new Paragraph(formatter.format(migrantMarriedInfo.get("proposerTime")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRight(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("    基本信息", Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftWithBlueBackground(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			//女方婚姻状况
			String femaleMartialStatus=(String) migrantMarriedInfo.get("femaleMartialStatus");
			
			//男方婚姻状况
			String maleMartialStatus=(String) migrantMarriedInfo.get("maleMartialStatus");
			
			Image underLine = Image.getInstance(imageBasePath + "underline.png");

			if (!StringUtils.isEmpty(maleMartialStatus)&&!("离异".equals(maleMartialStatus)||"丧偶".equals(maleMartialStatus))) {
				infoBodyCell = new PdfPCell(
						new Paragraph("女方姓名：" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("femaleName")) ? ""
								: migrantMarriedInfo.get("femaleName")), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftHeight25(infoBodyCell);
				infoTable.addCell(infoBodyCell);
				infoBodyCell = new PdfPCell(
						new Paragraph("婚姻状况:" + (StringUtils.isEmpty(femaleMartialStatus)
								? "" : femaleMartialStatus), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingRightHeight25(infoBodyCell);
				infoTable.addCell(infoBodyCell);
				
				infoBodyCell = new PdfPCell(underLine);
				PdfCssUtils.setUnderLineStyle(infoBodyCell);
				infoTable.addCell(infoBodyCell);
				infoBodyCell = new PdfPCell(
						new Paragraph("身份证号：" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("femaleId")) ? ""
								: migrantMarriedInfo.get("femaleId")), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
				infoTable.addCell(infoBodyCell);
				underLine = Image.getInstance(imageBasePath + "underline.png");
				infoBodyCell = new PdfPCell(underLine);
				PdfCssUtils.setUnderLineStyle(infoBodyCell);
				infoTable.addCell(infoBodyCell);
			}
			
			
			if (!StringUtils.isEmpty(femaleMartialStatus)&&!("离异".equals(femaleMartialStatus)||"丧偶".equals(femaleMartialStatus))) {
				infoBodyCell = new PdfPCell(
						new Paragraph("男方姓名：" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("maleName")) ? ""
								: migrantMarriedInfo.get("maleName")), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftHeight25(infoBodyCell);
				infoTable.addCell(infoBodyCell);
				infoBodyCell = new PdfPCell(
						new Paragraph("婚姻状况:" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("maleMartialStatus"))
								? "" : migrantMarriedInfo.get("maleMartialStatus")), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingRightHeight25(infoBodyCell);
				infoTable.addCell(infoBodyCell);
				underLine = Image.getInstance(imageBasePath + "underline.png");
				infoBodyCell = new PdfPCell(underLine);
				PdfCssUtils.setUnderLineStyle(infoBodyCell);
				infoTable.addCell(infoBodyCell);
				infoBodyCell = new PdfPCell(
						new Paragraph("身份证号：" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("maleId")) ? ""
								: migrantMarriedInfo.get("maleId")), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
				infoTable.addCell(infoBodyCell);

				underLine = Image.getInstance(imageBasePath + "underline.png");
				infoBodyCell = new PdfPCell(underLine);
				PdfCssUtils.setUnderLineStyle(infoBodyCell);
				infoTable.addCell(infoBodyCell);
			}

			if ((!StringUtils.isEmpty(femaleMartialStatus)&&!("离异".equals(femaleMartialStatus)||"丧偶".equals(femaleMartialStatus)))||
			(!StringUtils.isEmpty(maleMartialStatus)&&!("离异".equals(maleMartialStatus)||"丧偶".equals(maleMartialStatus)))) {
				infoBodyCell = new PdfPCell(
						new Paragraph("婚姻登记证：" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("marriageCertificat"))
								? "" : migrantMarriedInfo.get("marriageCertificat")), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
				infoTable.addCell(infoBodyCell);
				underLine = Image.getInstance(imageBasePath + "underline.png");
			}
			
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("户籍地址：" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("regAddr")) ? ""
							: migrantMarriedInfo.get("regAddr")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("居住地址：" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("domAddr")) ? ""
							: migrantMarriedInfo.get("domAddr")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph("现有子女数："
					+ (StringUtils.isEmpty((String) migrantMarriedInfo.get("childrenNum")) ? ""
							: migrantMarriedInfo.get("childrenNum"))
					+ "    男孩:"
					+ (StringUtils.isEmpty((String) migrantMarriedInfo.get("sonNum")) ? ""
							: migrantMarriedInfo.get("sonNum"))
					+ "    女孩:" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("daughterNum")) ? ""
							: migrantMarriedInfo.get("daughterNum")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("夫妇是否外出：" + ((boolean) migrantMarriedInfo.get("isDeparture") == true
							? "是    离开时间:" + (StringUtils.isEmpty((String) migrantMarriedInfo.get("departureTime")) ? ""
									: migrantMarriedInfo.get("departureTime"))
							: "否"), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"是否进行节育措施：" + ((boolean) migrantMarriedInfo.get("isBirthControl") == true ? "是    节育类型:"
							+ (StringUtils.isEmpty((String) migrantMarriedInfo.get("conTraceptiveMode")) ? ""
									: migrantMarriedInfo.get("conTraceptiveMode"))
							: "否")
							+ (StringUtils.isNotEmpty((String) migrantMarriedInfo.get("maleName"))
									&& StringUtils.isEmpty((String) migrantMarriedInfo.get("femaleName")) ? ""
											: "    是否参加妇检:" + ((((boolean) migrantMarriedInfo
													.get("isGynecologicalCheck") == true
													&& StringUtils.isNotEmpty(
															(String) migrantMarriedInfo.get("marriageCertificat"))))
																	? "是" : "否")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("    提交材料", Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftWithBlueBackground(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			
			// 查询formId对应的附件信息
			List<Map<String, Object>> filesInfo = attachmentService.getFileByFormNo(selectParam);
			
			int j = 0;
			for (Map<String, Object> fileInfo : filesInfo) {
				j++;
				infoBodyCell = new PdfPCell(new Paragraph("附件" + j + "：" + fileInfo.get("fileName"), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2(infoBodyCell);
				infoTable.addCell(infoBodyCell);
			}

			pdfDoc.add(infoTable);
			PdfPTable adjunctTable;

			int i = 0;
			for (Map<String, Object> fileInfo : filesInfo) {
				pdfDoc.newPage();
				adjunctTable = new PdfPTable(1);
				adjunctTable.setWidthPercentage(100);
				i++;
				infoBodyCell = new PdfPCell(new Paragraph("附件" + i + "：" + fileInfo.get("fileName"), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2(infoBodyCell);
				adjunctTable.addCell(infoBodyCell);
				for (String fileAddr : (String[]) fileInfo.get("fileAddr")) {
					Image image = Image.getInstance(fileAddr);
					infoBodyCell = new PdfPCell(image);
					PdfCssUtils.setAdjunctStyle(infoBodyCell);
					image.setScaleToFitLineWhenOverflow(true);
					adjunctTable.addCell(infoBodyCell);
				}
				pdfDoc.add(adjunctTable);
			}

			// 关闭文档对象，注：当文档对象真正关闭后，数据才会写入文件中。
			pdfDoc.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * get preproductive civics info
	 */
	@Override
	public byte[] getPreproductiveCivics(Integer muId) {
		try {
			Map<String, Object> selectParam = new HashMap<>();
			selectParam.put("notifyFormId", muId);

			// get migrant unmarried info
			Map<String, Object> preproductiveCivicsInfo = new HashMap<>();
			preproductiveCivicsInfo = reproductiveCivicsService.getMaritalMarriedDetail(selectParam).get(0);

			// 新建document对象
			Document pdfDoc = new Document(PageSize.A4, 36, 36, 24, 36);
			// 构造好的pdf文件输出位置
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			PdfWriter.getInstance(pdfDoc, baos);
			// 打开pdf文件---注：只有document 打开后，才能往文件内写入导出信息
			pdfDoc.open();

			String fontBasePath = this.getClass().getClassLoader().getResource("/font").getPath();
			String imageBasePath = this.getClass().getClassLoader().getResource("/image").getPath();
			String dataFilesBasePath = this.getClass().getClassLoader().getResource("").getPath()
					.replace("WEB-INF/classes", "dataFiles");

			// PDFTable类似于html的表格文件，但是只有列的概念，定义列的数量，不能定义行的数量。
			PdfPTable infoTable = new PdfPTable(2);
			infoTable.setWidthPercentage(100);

			// 设置中文字体
			// BaseFont bfChinese = BaseFont.createFont("STSong-Light",
			// "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

			BaseFont bfChinese = BaseFont.createFont(fontBasePath + "simfang.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			Font FontHeader = new Font(bfChinese, 14, Font.NORMAL);
			Font Fontbody = new Font(bfChinese, 12, Font.NORMAL);

			PdfPCell infoHeaderCell = new PdfPCell(new Paragraph("生殖保健服务证预审", FontHeader));
			PdfCssUtils.setHeaderFontStyleAlingCenter(infoHeaderCell);
			infoTable.addCell(infoHeaderCell);

			PdfPCell infoBodyCell = new PdfPCell(
					new Paragraph("预审单编号： "+String.valueOf(preproductiveCivicsInfo.get("formNo")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeft(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			infoBodyCell = new PdfPCell(
					new Paragraph(formatter.format(preproductiveCivicsInfo.get("proposerTime")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRight(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("    基本信息", Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftWithBlueBackground(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("女方姓名：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("femaleName"))
							? "" : preproductiveCivicsInfo.get("femaleName")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftHeight25WithColor(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"婚姻状况:" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("feMaritalStatus")) ? ""
							: preproductiveCivicsInfo.get("feMaritalStatus")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRightHeight25WithColor(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("身份证号：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("femaleIdCard"))
							? "" : preproductiveCivicsInfo.get("femaleIdCard")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("出生日期：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("femaleBirthday"))
							? "" : preproductiveCivicsInfo.get("femaleBirthday")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRightHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			Image underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"户籍地址：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("femaleRegAddress")) ? ""
							: preproductiveCivicsInfo.get("femaleRegAddress")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"居住地址：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("femaleDomAddress")) ? ""
							: preproductiveCivicsInfo.get("femaleDomAddress")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("男方姓名：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("maleName")) ? ""
							: preproductiveCivicsInfo.get("maleName")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftHeight25WithColor(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"婚姻状况:" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("maleMaritalStatus")) ? ""
							: preproductiveCivicsInfo.get("maleMaritalStatus")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRightHeight25WithColor(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("身份证号：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("maleIdCard"))
							? "" : preproductiveCivicsInfo.get("maleIdCard")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("出生日期：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("maleBirthday"))
							? "" : preproductiveCivicsInfo.get("maleBirthday")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRightHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("户籍地址：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("maleRegAddress"))
							? "" : preproductiveCivicsInfo.get("maleRegAddress")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("居住地址：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("maleDomAddress"))
							? "" : preproductiveCivicsInfo.get("maleDomAddress")), Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"婚姻登记证号：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("marrCertiNumber")) ? ""
							: preproductiveCivicsInfo.get("marrCertiNumber")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"婚姻登记日期:" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("marrCertiDate")) ? ""
							: preproductiveCivicsInfo.get("marrCertiDate")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingRightHeight25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"婚姻登记机关：" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("marrCertiOrgan")) ? ""
							: preproductiveCivicsInfo.get("marrCertiOrgan")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph("现有子女数："
					+ (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("childerNum")) ? ""
							: preproductiveCivicsInfo.get("childerNum"))
					+ "  男孩:"
					+ (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("sonNum")) ? ""
							: preproductiveCivicsInfo.get("sonNum"))
					+ "  女孩:" + (StringUtils.isEmpty((String) preproductiveCivicsInfo.get("daughterNum")) ? ""
							: preproductiveCivicsInfo.get("daughterNum")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			underLine = Image.getInstance(imageBasePath + "underline.png");
			infoBodyCell = new PdfPCell(underLine);
			PdfCssUtils.setUnderLineStyle(infoBodyCell);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"当前婚育情况：" + (StringUtils.isEmpty(String.valueOf(preproductiveCivicsInfo.get("isPregancy"))) ? ""
							: (Integer) preproductiveCivicsInfo.get("isPregancy") == 0 ? "未怀孕"
									: "已怀孕  周数:" + (StringUtils
											.isEmpty((String) preproductiveCivicsInfo.get("pregnancyWeeks")) ? ""
													: preproductiveCivicsInfo.get("pregnancyWeeks"))),
					Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftColspan2Height25(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("    提交材料", Fontbody));
			PdfCssUtils.setBodyFontStyleAlingLeftWithBlueBackground(infoBodyCell);
			infoTable.addCell(infoBodyCell);

			// 查询formId对应的附件信息
			List<Map<String, Object>> filesInfo = attachmentService.getFileByFormNo(selectParam);

			int j = 0;
			for (Map<String, Object> fileInfo : filesInfo) {
				j++;
				infoBodyCell = new PdfPCell(new Paragraph("附件" + j + "：" + fileInfo.get("fileName"), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2(infoBodyCell);
				infoTable.addCell(infoBodyCell);
			}
			
			pdfDoc.add(infoTable);

			PdfPTable adjunctTable;

			int i = 0;
			for (Map<String, Object> fileInfo : filesInfo) {
				pdfDoc.newPage();
				adjunctTable = new PdfPTable(1);
				adjunctTable.setWidthPercentage(100);
				i++;
				infoBodyCell = new PdfPCell(new Paragraph("附件" + i + "：" + fileInfo.get("fileName"), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2(infoBodyCell);
				adjunctTable.addCell(infoBodyCell);
				for (String fileAddr : (String[]) fileInfo.get("fileAddr")) {
					Image image = Image.getInstance(fileAddr);
					infoBodyCell = new PdfPCell(image);
					PdfCssUtils.setAdjunctStyle(infoBodyCell);
					image.setScaleToFitLineWhenOverflow(true);
					adjunctTable.addCell(infoBodyCell);
				}
				pdfDoc.add(adjunctTable);
			}

			// 关闭文档对象，注：当文档对象真正关闭后，数据才会写入文件中。
			pdfDoc.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * get company recruitment info
	 */
	@Override
	public byte[] getCompanyRecruitmentDetail(Integer jobId) {
		try {
			Map<String, Object> companyRecruitmentDetail = new HashMap<>();
			companyRecruitmentDetail = jobCompanyService.getCompDetailById(jobId);

			// 新建document对象
			Document pdfDoc = new Document(PageSize.A4, 36, 36, 24, 36);
			// 构造好的pdf文件输出位置
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			PdfWriter.getInstance(pdfDoc, baos);
			// 打开pdf文件---注：只有document 打开后，才能往文件内写入导出信息
			pdfDoc.open();

			String fontBasePath = this.getClass().getClassLoader().getResource("/font").getPath();

			// PDFTable类似于html的表格文件，但是只有列的概念，定义列的数量，不能定义行的数量。
			PdfPTable infoTable = new PdfPTable(4);
			infoTable.setWidthPercentage(100);

			// 设置中文字体
			// BaseFont bfChinese = BaseFont.createFont("STSong-Light",
			// "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

			BaseFont bfChinese = BaseFont.createFont(fontBasePath + "simfang.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			Font FontHeader = new Font(bfChinese, 18, Font.BOLD);
			Font FontHeaderBody = new Font(bfChinese, 11, Font.NORMAL);
			Font Fontbody = new Font(bfChinese, 12, Font.NORMAL);
			Font FontBodyHeader = new Font(bfChinese, 13, Font.BOLD);
			Font FontbodyTableHeader = new Font(bfChinese, 12, Font.BOLD);

			PdfPCell infoHeaderCell = new PdfPCell(new Paragraph("招聘登记表", FontHeader));
			PdfCssUtils.setHeaderFontStyleAlingCenter(infoHeaderCell, 4);
			infoTable.addCell(infoHeaderCell);

			infoHeaderCell = new PdfPCell(
					new Paragraph("登记日期：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("registerDate"))
							? "" : companyRecruitmentDetail.get("registerDate")), FontHeaderBody));
			PdfCssUtils.setHeaderFontStyleAlingLeft(infoHeaderCell, 1);
			infoTable.addCell(infoHeaderCell);

			infoHeaderCell = new PdfPCell(
					new Paragraph("有效期：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("expireDate"))
							? "" : companyRecruitmentDetail.get("expireDate")), FontHeaderBody));
			PdfCssUtils.setHeaderFontStyleAlingLeft(infoHeaderCell, 1);
			infoTable.addCell(infoHeaderCell);

			infoHeaderCell = new PdfPCell(
					new Paragraph("登记表编号：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("jobNo")) ? ""
							: companyRecruitmentDetail.get("jobNo")), FontHeaderBody));
			PdfCssUtils.setHeaderFontStyleAlingRight(infoHeaderCell, 2);
			infoTable.addCell(infoHeaderCell);

			PdfPCell infoBodyCell = new PdfPCell(
					new Paragraph("招聘单位全称：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("compName"))
							? "" : companyRecruitmentDetail.get("compName")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("招聘单位性质：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("compNature"))
							? "" : companyRecruitmentDetail.get("compNature")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("经济类型：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("econType")) ? ""
							: companyRecruitmentDetail.get("econType")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("所属行业：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("industry")) ? ""
							: companyRecruitmentDetail.get("industry")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"法人代表：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("representative")) ? ""
							: companyRecruitmentDetail.get("representative")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"组织机构代码证编号：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("organizeCode")) ? ""
							: companyRecruitmentDetail.get("organizeCode")),
					Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("招聘方式：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("recruType"))
							? "" : companyRecruitmentDetail.get("recruType")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("邮政编码：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("postCode")) ? ""
							: companyRecruitmentDetail.get("postCode")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("营业执照号：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("busiLicense"))
							? "" : companyRecruitmentDetail.get("busiLicense")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph(
					"所属地区：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("compPartOfAddr")) ? ""
							: companyRecruitmentDetail.get("compPartOfAddr")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"详细地址：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("compDetailAddr")) ? ""
							: companyRecruitmentDetail.get("compDetailAddr")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("联系人：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("contactName"))
							? "" : companyRecruitmentDetail.get("contactName")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("联系电话：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("contactTel"))
							? "" : companyRecruitmentDetail.get("contactTel")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("公司座机：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("compTel")) ? ""
							: companyRecruitmentDetail.get("compTel")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("招聘邮箱：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("contactEmail"))
							? "" : companyRecruitmentDetail.get("contactEmail")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("招聘要求", FontBodyHeader));
			PdfCssUtils.setHeaderFontStyleAlingCenter(infoBodyCell, 4);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("用工形式：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("employForm"))
							? "" : companyRecruitmentDetail.get("employForm")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("用工方式：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("employMode"))
							? "" : companyRecruitmentDetail.get("employMode")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("户籍要求：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("houseRequire"))
							? "" : companyRecruitmentDetail.get("houseRequire")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"试用期限：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("probationPeriod")) ? ""
							: companyRecruitmentDetail.get("probationPeriod")),
					Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph(
					"是否提供食宿：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("accommodation")) ? ""
							: companyRecruitmentDetail.get("accommodation")),
					Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"是否缴纳社会保险：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("socialSecur")) ? ""
							: companyRecruitmentDetail.get("socialSecur")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"是否参加周五小型招聘会：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("jobFair")) ? ""
							: companyRecruitmentDetail.get("jobFair")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("工作地点：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("workAddr")) ? ""
							: companyRecruitmentDetail.get("workAddr")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 4);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph(
					"其他福利待遇说明：" + (StringUtils.isEmpty((String) companyRecruitmentDetail.get("benefitDesc")) ? ""
							: companyRecruitmentDetail.get("benefitDesc")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 4);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("招聘岗位", FontBodyHeader));
			PdfCssUtils.setHeaderFontStyleAlingCenter(infoBodyCell, 4);
			infoTable.addCell(infoBodyCell);

			PdfPTable infoSubTable = new PdfPTable(7);
			infoSubTable.setWidthPercentage(95);

			// 招聘岗位表格表头
			infoBodyCell = new PdfPCell(new Paragraph("招聘工种", FontbodyTableHeader));
			PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
			infoSubTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph("文化程度", FontbodyTableHeader));
			PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
			infoSubTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph("年龄要求", FontbodyTableHeader));
			PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
			infoSubTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph("招聘人数", FontbodyTableHeader));
			PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
			infoSubTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph("性别", FontbodyTableHeader));
			PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
			infoSubTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph("工资待遇及奖金", FontbodyTableHeader));
			PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
			infoSubTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph("岗位相关要求", FontbodyTableHeader));
			PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
			infoSubTable.addCell(infoBodyCell);

			// 获取招聘岗位列表
			List<Map<String, Object>> jobCompJobs = new LinkedList<>();
			if (companyRecruitmentDetail.containsKey("jobNo")) {
				jobCompJobs = jobCompJobsService.getCompJobsByJobNo(String.valueOf(jobId));
			}
			for (Map<String, Object> map : jobCompJobs) {
				infoBodyCell = new PdfPCell(new Paragraph(
						(String) (StringUtils.isEmpty((String) map.get("position")) ? "" : map.get("position")),
						Fontbody));
				PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
				infoSubTable.addCell(infoBodyCell);

				infoBodyCell = new PdfPCell(new Paragraph(
						"" + (StringUtils.isEmpty((String) map.get("edu")) ? "" : map.get("edu")), Fontbody));
				PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
				infoSubTable.addCell(infoBodyCell);

				infoBodyCell = new PdfPCell(new Paragraph(
						"" + (StringUtils.isEmpty((String) map.get("age")) ? "" : map.get("age")), Fontbody));
				PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
				infoSubTable.addCell(infoBodyCell);

				infoBodyCell = new PdfPCell(new Paragraph("" + map.get("recruitNum"), Fontbody));
				PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
				infoSubTable.addCell(infoBodyCell);

				infoBodyCell = new PdfPCell(new Paragraph(
						"" + (StringUtils.isEmpty((String) map.get("sex")) ? "" : map.get("sex")), Fontbody));
				PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
				infoSubTable.addCell(infoBodyCell);

				infoBodyCell = new PdfPCell(new Paragraph(
						"" + (StringUtils.isEmpty((String) map.get("salary")) ? "" : map.get("salary")), Fontbody));
				PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
				infoSubTable.addCell(infoBodyCell);

				infoBodyCell = new PdfPCell(new Paragraph(
						"" + (StringUtils.isEmpty((String) map.get("reqiurement")) ? "" : map.get("reqiurement")),
						Fontbody));
				PdfCssUtils.setBodyTableFontStyle(infoBodyCell, 1);
				infoSubTable.addCell(infoBodyCell);
			}

			infoBodyCell = new PdfPCell(infoSubTable);
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 4);
			infoTable.addCell(infoBodyCell);

			// 页脚
			infoBodyCell = new PdfPCell(new Paragraph(
					"本公司承诺网上招聘填写信息及申报材料完全真实有效，本公司将严格遵守国家相关法律、法规及规定，守法经营、诚实信用，如有虚假，本公司愿承担一切法律责任。", Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 4);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("信息确认（请签字）： __________", Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 4, Element.ALIGN_RIGHT);
			infoTable.addCell(infoBodyCell);

			pdfDoc.add(infoTable);

			// 打印附件
			Map<String, Object> fileSelectParam = new HashMap<>();
			fileSelectParam.put("jobId", jobId);
			fileSelectParam.put("modelCode", CommonConstant.JOB_COMPANY);
			List<Map<String, Object>> filesInfo = attachmentService.getRecruitFiles(fileSelectParam);

			PdfPTable adjunctTable;

			int i = 0;
			for (Map<String, Object> fileInfo : filesInfo) {
				pdfDoc.newPage();
				adjunctTable = new PdfPTable(1);
				adjunctTable.setWidthPercentage(100);
				i++;
				infoBodyCell = new PdfPCell(new Paragraph("附件" + i + "：" + fileInfo.get("fileName"), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2(infoBodyCell);
				adjunctTable.addCell(infoBodyCell);
				for (String fileAddr : (String[]) fileInfo.get("fileAddr")) {
					Image image = Image.getInstance(fileAddr);
					infoBodyCell = new PdfPCell(image);
					PdfCssUtils.setAdjunctStyle(infoBodyCell);
					image.setScaleToFitLineWhenOverflow(true);
					adjunctTable.addCell(infoBodyCell);
				}
				pdfDoc.add(adjunctTable);
			}

			// 关闭文档对象，注：当文档对象真正关闭后，数据才会写入文件中。
			pdfDoc.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * get personal job hunting info
	 */
	@Override
	public byte[] getPersonalJobHuntingDetail(Integer jobId) {
		try {
			// get migrant unmarried info
			Map<String, Object> personalJobHuntingDetail = new HashMap<>();
			personalJobHuntingDetail = jobPersonService.getPersonDetailById(jobId);

			// 新建document对象
			Document pdfDoc = new Document(PageSize.A4, 36, 36, 24, 36);
			// 构造好的pdf文件输出位置
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			PdfWriter.getInstance(pdfDoc, baos);
			// 打开pdf文件---注：只有document 打开后，才能往文件内写入导出信息
			pdfDoc.open();

			String fontBasePath = this.getClass().getClassLoader().getResource("/font").getPath();

			// PDFTable类似于html的表格文件，但是只有列的概念，定义列的数量，不能定义行的数量。
			PdfPTable infoTable = new PdfPTable(6);
			infoTable.setWidthPercentage(100);

			// 设置中文字体
			// BaseFont bfChinese = BaseFont.createFont("STSong-Light",
			// "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

			BaseFont bfChinese = BaseFont.createFont(fontBasePath + "simfang.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			Font FontHeader = new Font(bfChinese, 18, Font.BOLD);
			Font FontHeaderBody = new Font(bfChinese, 11, Font.NORMAL);
			Font Fontbody = new Font(bfChinese, 12, Font.NORMAL);

			PdfPCell infoHeaderCell = new PdfPCell(new Paragraph("个人求职登记表", FontHeader));
			PdfCssUtils.setHeaderFontStyleAlingCenter(infoHeaderCell, 6);
			infoTable.addCell(infoHeaderCell);

			infoHeaderCell = new PdfPCell(
					new Paragraph("登记日期：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("createDate"))
							? "" : personalJobHuntingDetail.get("createDate")), FontHeaderBody));
			PdfCssUtils.setHeaderFontStyleAlingLeft(infoHeaderCell, 1);
			infoTable.addCell(infoHeaderCell);

			infoHeaderCell = new PdfPCell(
					new Paragraph("有效期：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("expireDate"))
							? "" : personalJobHuntingDetail.get("expireDate")), FontHeaderBody));
			PdfCssUtils.setHeaderFontStyleAlingLeft(infoHeaderCell, 1);
			infoTable.addCell(infoHeaderCell);

			infoHeaderCell = new PdfPCell(
					new Paragraph("登记表编号：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("jobNo")) ? ""
							: personalJobHuntingDetail.get("jobNo")), FontHeaderBody));
			PdfCssUtils.setHeaderFontStyleAlingRight(infoHeaderCell, 4);
			infoTable.addCell(infoHeaderCell);

			infoHeaderCell = new PdfPCell(
					new Paragraph("姓名：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("jName")) ? ""
							: personalJobHuntingDetail.get("jName")), FontHeaderBody));
			PdfCssUtils.setBodyFontStyleWrap(infoHeaderCell, 1);
			infoTable.addCell(infoHeaderCell);
			PdfPCell infoBodyCell = new PdfPCell(
					new Paragraph("性别：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("sex")) ? ""
							: personalJobHuntingDetail.get("sex")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("民族：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("nation")) ? ""
							: personalJobHuntingDetail.get("nation")), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("文化程度：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("education"))
							? "" : personalJobHuntingDetail.get("education")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("年龄：" + personalJobHuntingDetail.get("age"), Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("身高：" + personalJobHuntingDetail.get("height"), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("身份证号码：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("idCard")) ? ""
							: personalJobHuntingDetail.get("idCard")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 3);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("健康状况：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("healthCond"))
							? "" : personalJobHuntingDetail.get("healthCond")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("户口性质：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("regNature"))
							? "" : personalJobHuntingDetail.get("regNature")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("婚姻状况：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("marryStatus"))
							? "" : personalJobHuntingDetail.get("marryStatus")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(new Paragraph(
					"是否持职业资格证书：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("isCertificate")) ? ""
							: personalJobHuntingDetail.get("isCertificate")),
					Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("联系电话：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("tel")) ? ""
							: personalJobHuntingDetail.get("tel")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 3);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("人员类型：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("category")) ? ""
							: personalJobHuntingDetail.get("category")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 6);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("毕业院校：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("university"))
							? "" : personalJobHuntingDetail.get("university")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("所学专业：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("major")) ? ""
							: personalJobHuntingDetail.get("major")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("毕业时间：" + personalJobHuntingDetail.get("graduateDate"), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("专业工种：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("profType")) ? ""
							: personalJobHuntingDetail.get("profType")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("专业技术等级：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("profLvl"))
							? "" : personalJobHuntingDetail.get("profLvl")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("从业年限：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("profTime")) ? ""
							: personalJobHuntingDetail.get("profTime")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("应聘岗位：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("position")) ? ""
							: personalJobHuntingDetail.get("position")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 4);
			infoTable.addCell(infoBodyCell);
			infoBodyCell = new PdfPCell(
					new Paragraph("月薪要求：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("salary")) ? ""
							: personalJobHuntingDetail.get("salary")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("户口所在地：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("regAddr")) ? ""
							: personalJobHuntingDetail.get("regAddr")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 6);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(
					new Paragraph("现居住地：" + (StringUtils.isEmpty((String) personalJobHuntingDetail.get("domAddr")) ? ""
							: personalJobHuntingDetail.get("domAddr")), Fontbody));
			PdfCssUtils.setBodyFontStyle(infoBodyCell, 6);
			infoTable.addCell(infoBodyCell);

			// 培训学习经历
			infoBodyCell = new PdfPCell(
					new Paragraph("学习工作培训经历：", Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 6);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph(
					(String) (StringUtils.isEmpty((String) personalJobHuntingDetail.get("workExper")) ? ""
							: ((String) personalJobHuntingDetail.get("workExper")).replaceAll(
									"<p([^>]*)>|<span([^>]*)>|<div([^>]*)>|</p>|<br />|</span>|</div>", "")),
					Fontbody));
			PdfCssUtils.setBodyFontStyleWithNoHeightLimit(infoBodyCell, 6);
			infoTable.addCell(infoBodyCell);

			// 页脚
			infoBodyCell = new PdfPCell(new Paragraph(
					"本公司承诺网上招聘填写信息及申报材料完全真实有效，本公司将严格遵守国家相关法律、法规及规定，守法经营、诚实信用，如有虚假，本公司愿承担一切法律责任。", Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 6);
			infoTable.addCell(infoBodyCell);

			infoBodyCell = new PdfPCell(new Paragraph("信息确认（请签字）： __________", Fontbody));
			PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 6, Element.ALIGN_RIGHT);
			infoTable.addCell(infoBodyCell);

			pdfDoc.add(infoTable);

			
			// 打印附件
			Map<String, Object> fileSelectParam = new HashMap<>();
			fileSelectParam.put("jobId", jobId);
			fileSelectParam.put("modelCode", CommonConstant.JOB_PERSON);
			List<Map<String, Object>> filesInfo = attachmentService.getRecruitFiles(fileSelectParam);

			PdfPTable adjunctTable;

			int i = 0;
			for (Map<String, Object> fileInfo : filesInfo) {
				pdfDoc.newPage();
				adjunctTable = new PdfPTable(1);
				adjunctTable.setWidthPercentage(100);
				i++;
				infoBodyCell = new PdfPCell(new Paragraph("附件" + i + "：" + fileInfo.get("fileName"), Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeftColspan2(infoBodyCell);
				adjunctTable.addCell(infoBodyCell);
				for (String fileAddr : (String[]) fileInfo.get("fileAddr")) {
					Image image = Image.getInstance(fileAddr);
					infoBodyCell = new PdfPCell(image);
					PdfCssUtils.setAdjunctStyle(infoBodyCell);
					image.setScaleToFitLineWhenOverflow(true);
					adjunctTable.addCell(infoBodyCell);
				}
				pdfDoc.add(adjunctTable);
			}
			// 关闭文档对象，注：当文档对象真正关闭后，数据才会写入文件中。
			pdfDoc.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * get poll detail info
	 */
	@Override
	public byte[] getPollDetail(Integer surveyId) {
		try {
			// get migrant unmarried info
			List<Record> pollList = new ArrayList<Record>();
			pollList = pollReplyService.findSurveyLists(surveyId);

			// 新建document对象
			Document pdfDoc = new Document(PageSize.A4, 36, 36, 24, 36);
			// 构造好的pdf文件输出位置
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			PdfWriter.getInstance(pdfDoc, baos);
			// 打开pdf文件---注：只有document 打开后，才能往文件内写入导出信息
			pdfDoc.open();

			String fontBasePath = this.getClass().getClassLoader().getResource("/font").getPath();

			// PDFTable类似于html的表格文件，但是只有列的概念，定义列的数量，不能定义行的数量。
			PdfPTable infoTable = new PdfPTable(3);
			int width[] = { 40, 10, 50 };
			infoTable.setWidths(width);
			infoTable.setWidthPercentage(100);

			// 设置中文字体
			// BaseFont bfChinese = BaseFont.createFont("STSong-Light",
			// "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);

			BaseFont bfChinese = BaseFont.createFont(fontBasePath + "simfang.ttf", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			Font FontHeader = new Font(bfChinese, 14, Font.NORMAL);
			Font Fontbody = new Font(bfChinese, 12, Font.NORMAL);

			PdfPCell infoHeaderCell = new PdfPCell(new Paragraph("世纪城社区服务满意度调查统计", FontHeader));
			PdfCssUtils.setHeaderFontStyleAlingCenter(infoHeaderCell, 5);
			infoTable.addCell(infoHeaderCell);
			int i = 0;
			for (Record poll : pollList) {
				i++;
				PdfPCell infoBodyCell = new PdfPCell(new Paragraph(
						"Q" + i + "、" + (StringUtils.isEmpty((String) poll.getTitle()) ? "" : poll.getTitle()),
						Fontbody));
				PdfCssUtils.setBodyFontStyleAlingLeft(infoBodyCell, 3, 30);
				infoTable.addCell(infoBodyCell);
				String type = poll.getType();
				if ("option".equals(type)) {
					infoBodyCell = new PdfPCell(new Paragraph("选项", Fontbody));
					PdfCssUtils.setBodyFontStyleWithCenterColorGray1(infoBodyCell);
					infoTable.addCell(infoBodyCell);
					infoBodyCell = new PdfPCell(new Paragraph("小计", Fontbody));
					PdfCssUtils.setBodyFontStyleWithCenterColorGray1(infoBodyCell);
					infoTable.addCell(infoBodyCell);
					infoBodyCell = new PdfPCell(new Paragraph("比例", Fontbody));
					PdfCssUtils.setBodyFontStyleWithColorGray1(infoBodyCell);
					infoTable.addCell(infoBodyCell);

					List<Option> options = poll.getOptions();
					int j = 0;
					for (Option option : options) {
						j++;
						if ((j % 2) == 1) {
							infoBodyCell = new PdfPCell(
									new Paragraph((StringUtils.isEmpty(String.valueOf(option.getContent())) ? ""
											: String.valueOf(option.getContent())), Fontbody));
							PdfCssUtils.setBodyFontStyleWithColorWrite(infoBodyCell);
							infoTable.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(
									new Paragraph((StringUtils.isEmpty(String.valueOf(option.getNum())) ? ""
											: String.valueOf(option.getNum())), Fontbody));
							PdfCssUtils.setBodyFontStyleWithColorWrite(infoBodyCell, 1, Element.ALIGN_CENTER);
							infoTable.addCell(infoBodyCell);
							PdfPTable t0 = new PdfPTable(2);
							int width0[] = { 80, 20 };
							t0.setWidths(width0);
							PdfPTable t1 = new PdfPTable(1);
							PdfPTable t2 = new PdfPTable(2);
							String percent = StringUtils.isEmpty(String.valueOf(option.getPercent())) ? "0"
									: String.valueOf(option.getPercent());
							Integer percentInt = Integer.valueOf(percent.substring(0, percent.indexOf(".")));
							int width2[] = { percentInt, (100 - percentInt) };
							t2.setWidths(width2);
							infoBodyCell = new PdfPCell(new Paragraph(""));
							infoBodyCell.setBorder(0);
							infoBodyCell.setBackgroundColor(new BaseColor(41, 164, 250));
							t2.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(new Paragraph(""));
							infoBodyCell.setBorder(0);
							t2.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(t2);
							infoBodyCell.setBorder(0);
							// infoBodyCell.setPadding(3);
							infoBodyCell.setBackgroundColor(new BaseColor(227, 228, 230));
							t1.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(t1);
							infoBodyCell.setBorder(0);
							infoBodyCell.setPadding(3);
							t0.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(
									new Paragraph((StringUtils.isEmpty(String.valueOf(option.getPercent())) ? ""
											: String.valueOf(option.getPercent()) + "%"), Fontbody));
							infoBodyCell.setBorder(0);
							t0.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(t0);
							PdfCssUtils.setBodyFontStyleWithColorWrite(infoBodyCell);
							infoTable.addCell(infoBodyCell);
						} else {
							infoBodyCell = new PdfPCell(
									new Paragraph((StringUtils.isEmpty(String.valueOf(option.getContent())) ? ""
											: String.valueOf(option.getContent())), Fontbody));
							PdfCssUtils.setBodyFontStyleWithColorGray2(infoBodyCell);
							infoTable.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(
									new Paragraph((StringUtils.isEmpty(String.valueOf(option.getNum())) ? ""
											: String.valueOf(option.getNum())), Fontbody));
							PdfCssUtils.setBodyFontStyleWithColorGray2(infoBodyCell, 1, Element.ALIGN_CENTER);
							infoTable.addCell(infoBodyCell);
							PdfPTable t0 = new PdfPTable(2);
							int width0[] = { 80, 20 };
							t0.setWidths(width0);
							PdfPTable t1 = new PdfPTable(1);
							PdfPTable t2 = new PdfPTable(2);
							String percent = StringUtils.isEmpty(String.valueOf(option.getPercent())) ? "0"
									: String.valueOf(option.getPercent());
							Integer percentInt = Integer.valueOf(percent.substring(0, percent.indexOf(".")));
							int width2[] = { percentInt, (100 - percentInt) };
							t2.setWidths(width2);
							infoBodyCell = new PdfPCell(new Paragraph(""));
							infoBodyCell.setBorder(0);
							infoBodyCell.setBackgroundColor(new BaseColor(41, 164, 250));
							t2.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(new Paragraph(""));
							infoBodyCell.setBorder(0);
							t2.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(t2);
							infoBodyCell.setBorder(0);
							// infoBodyCell.setPadding(3);
							infoBodyCell.setBackgroundColor(new BaseColor(227, 228, 230));
							t1.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(t1);
							infoBodyCell.setBorder(0);
							infoBodyCell.setPadding(3);
							t0.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(
									new Paragraph((StringUtils.isEmpty(String.valueOf(option.getPercent())) ? ""
											: String.valueOf(option.getPercent()) + "%"), Fontbody));
							infoBodyCell.setBorder(0);
							t0.addCell(infoBodyCell);
							infoBodyCell = new PdfPCell(t0);
							PdfCssUtils.setBodyFontStyleWithColorGray2(infoBodyCell);
							infoTable.addCell(infoBodyCell);
						}
					}

					infoBodyCell = new PdfPCell(new Paragraph("本题有效填写人次", Fontbody));
					PdfCssUtils.setBodyFontStyleWithColorGray1(infoBodyCell);
					infoTable.addCell(infoBodyCell);
					infoBodyCell = new PdfPCell(new Paragraph(
							(StringUtils.isEmpty(String.valueOf(poll.getSum())) ? "" : String.valueOf(poll.getSum())),
							Fontbody));
					PdfCssUtils.setBodyFontStyleWithCenterColorGray1(infoBodyCell);
					infoTable.addCell(infoBodyCell);
					infoBodyCell = new PdfPCell(new Paragraph("", Fontbody));
					PdfCssUtils.setBodyFontStyleWithColorGray1(infoBodyCell);
					infoTable.addCell(infoBodyCell);

				} else {
					infoBodyCell = new PdfPCell(new Paragraph("回复情况", Fontbody));
					PdfCssUtils.setBodyFontStyleWithColorGray1(infoBodyCell, 3);
					infoTable.addCell(infoBodyCell);
					List<Option> options = poll.getOptions();
					for (Option option : options) {
						infoBodyCell = new PdfPCell(
								new Paragraph((StringUtils.isEmpty(String.valueOf(option.getContent())) ? ""
										: String.valueOf(option.getContent())), Fontbody));
						PdfCssUtils.setBodyFontStyleWithColorWrite(infoBodyCell, 3, Element.ALIGN_LEFT);
						infoTable.addCell(infoBodyCell);
					}
					infoBodyCell = new PdfPCell(new Paragraph("答题人数："
							+ (StringUtils.isEmpty(String.valueOf(poll.getSum())) ? "" : String.valueOf(poll.getSum())),
							Fontbody));
					PdfCssUtils.setBodyFontStyleWithColorWrite(infoBodyCell, 3, Element.ALIGN_LEFT);
					infoTable.addCell(infoBodyCell);
				}
			}

			// PdfPCell infoBodyCell = new PdfPCell(new Paragraph("姓名：" +
			// (StringUtils.isEmpty((String) pollList.get("personName")) ? "" :
			// pollList.get("personName")), Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			// infoTable.addCell(infoBodyCell);
			// infoBodyCell = new PdfPCell(new Paragraph("性别：" +
			// (StringUtils.isEmpty((String) pollList.get("sex")) ? "" :
			// pollList.get("sex")), Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			// infoTable.addCell(infoBodyCell);
			// infoBodyCell = new PdfPCell(new Paragraph("民族：" +
			// (StringUtils.isEmpty((String) pollList.get("nation")) ? "" :
			// pollList.get("nation")), Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			// infoTable.addCell(infoBodyCell);
			// infoBodyCell = new PdfPCell(new Paragraph("学历水平：" +
			// (StringUtils.isEmpty((String) pollList.get("degree")) ? "" :
			// pollList.get("degree")), Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 1);
			// infoTable.addCell(infoBodyCell);
			// infoBodyCell = new PdfPCell(new Paragraph("出生年月：" +
			// (StringUtils.isEmpty((String) pollList.get("birthday")) ? "" :
			// pollList.get("birthday")), Fontbody));
			// PdfCssUtils.setBodyFontStyleWrap(infoBodyCell, 1);
			// infoTable.addCell(infoBodyCell);
			//
			// infoBodyCell = new PdfPCell(new Paragraph("毕业院校：" +
			// (StringUtils.isEmpty((String) pollList.get("school")) ? "" :
			// pollList.get("school")), Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 3);
			// infoTable.addCell(infoBodyCell);
			// infoBodyCell = new PdfPCell(new Paragraph("求职意愿：" +
			// (StringUtils.isEmpty((String) pollList.get("intention")) ? "" :
			// pollList.get("intention")), Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			// infoTable.addCell(infoBodyCell);
			// infoBodyCell = new PdfPCell(new Paragraph("联系电话：" +
			// (StringUtils.isEmpty((String) pollList.get("phone")) ? "" :
			// pollList.get("phone")), Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 3);
			// infoTable.addCell(infoBodyCell);
			// infoBodyCell = new PdfPCell(new Paragraph("期望薪资：" +
			// (StringUtils.isEmpty((String) pollList.get("salary")) ? "" :
			// pollList.get("salary")), Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 2);
			// infoTable.addCell(infoBodyCell);
			//
			// infoBodyCell = new PdfPCell(new Paragraph("家庭住址：" +
			// (StringUtils.isEmpty((String) pollList.get("addr")) ? "" :
			// pollList.get("addr")), Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 5);
			// infoTable.addCell(infoBodyCell);
			// infoBodyCell = new PdfPCell(new Paragraph("工作经历：", Fontbody));
			// PdfCssUtils.setBodyFontStyle(infoBodyCell, 5);
			// infoTable.addCell(infoBodyCell);
			// infoBodyCell = new PdfPCell(new Paragraph((String)
			// (StringUtils.isEmpty((String) pollList.get("workExperience")) ?
			// "" : ((String)
			// pollList.get("workExperience")).replaceAll("<p([^>]*)>|<span([^>]*)>|<div([^>]*)>|</p>|<br
			// />|</span>|</div>", "")), Fontbody));
			// PdfCssUtils.setBodyFontStyleWithNoHeightLimit(infoBodyCell, 5);
			// infoTable.addCell(infoBodyCell);

			pdfDoc.add(infoTable);
			// 关闭文档对象，注：当文档对象真正关闭后，数据才会写入文件中。
			pdfDoc.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
