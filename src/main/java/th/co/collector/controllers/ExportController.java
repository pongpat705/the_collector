package th.co.collector.controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import th.co.collector.components.Setup;
import th.co.collector.constants.ApplicationConstants;
import th.co.collector.entities.Student;
import th.co.collector.entities.mobilize.Mobilize;
import th.co.collector.entities.mobilize.MobilizeMaster;
import th.co.collector.entities.moneycontrol.Balance;
import th.co.collector.entities.moneycontrol.BalanceMaster;
import th.co.collector.entities.parameter.SystemParameter;
import th.co.collector.repositories.BalanceMasterRepository;
import th.co.collector.repositories.MobilizeReporsitory;
import th.co.collector.repositories.StudentRepository;
import th.co.collector.repositories.parameter.SystemParameterRepository;

@Controller
public class ExportController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BalanceMasterRepository balanceMasterRepository;
	
	@Autowired
	SystemParameterRepository sysRepository;
	
	@Autowired
	Setup setup;
	
	@Autowired
	MobilizeReporsitory mobilizeRepository;
	
	@Autowired
	StudentRepository studentRepos;
	
	@RequestMapping(value="/balance/{masterId}/pdf", method=RequestMethod.GET)
	@ResponseBody
	public void genPdf(HttpServletRequest request, HttpServletResponse response, @PathVariable Long masterId) {
		BalanceMaster balanceMaster = balanceMasterRepository.findById(masterId).get();
		
		if(null != balanceMaster) {
			Date dateToCal = balanceMaster.getCreateDate();
			Calendar calendarToCal = Calendar.getInstance();
			calendarToCal.setTime(dateToCal);
			
			Integer day = calendarToCal.get(Calendar.DATE);
			Integer month = calendarToCal.get(Calendar.MONTH);
			Integer year = calendarToCal.get(Calendar.YEAR);
			
			BigDecimal amountSum 			= new BigDecimal(BigDecimal.ZERO.toString());
			for (Balance balance : balanceMaster.getBalances()) {
				amountSum = amountSum.add(balance.getAmount());
			}
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("DATE_YEAR", year+543);
			params.put("DATE_MONTH", Setup.getMONTH_TH().get(month));
			params.put("DATE_DAY", day);
			params.put("AMPHUR", balanceMaster.getAmphur());
			params.put("AMOUNT_TEXT", amountToWord(amountSum));
			params.put("DEPARTMENT", balanceMaster.getDepartment());
			
			List<Balance> balanceList = balanceMaster.getBalances();
			Collection<Map<String, ?>> resultSet = new ArrayList<>();
			for (Balance balance : balanceList) {
				Map<String, Object> balanceDetailList = new HashMap<>();
				
				SystemParameter sysParam = sysRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", balance.getBalanceType());
				
				balanceDetailList.put("AMOUNT", balance.getAmount());
				balanceDetailList.put("REMARK", StringUtils.defaultIfEmpty(balance.getRemark(), ""));
				if(null == sysParam) {
					balanceDetailList.put("DESCRIPTION", "รายการรวม");
				} else {
					balanceDetailList.put("DESCRIPTION", sysParam.getValue1()+" "+balance.getBalanceNumber()+" "+sysParam.getValue2());
				}
				resultSet.add(balanceDetailList);
			}
			
			Collection<Map<String, ?>> myColl = (Collection<Map<String, ?>>) resultSet;
			
			JRMapCollectionDataSource ds = new JRMapCollectionDataSource(myColl);
			
			
			try {
				File file = new ClassPathResource("/jasper/BALANCE_REPORT.jasper").getFile();
				InputStream path = new FileInputStream(file);
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, ds);
				byte[] arrayByte = JasperExportManager.exportReportToPdf(jasperPrint);
				//MAO
				response.setContentType("application/octet-stream");
				response.setHeader("Content-disposition","attachment;filename=BALANCE_REPORT_"+System.currentTimeMillis()+".pdf"); 
				response.setContentLength(arrayByte.length);
				ServletOutputStream outstream = response.getOutputStream();

				outstream.write(arrayByte);
				outstream.flush();
				outstream.close();

			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
	}
	
	@RequestMapping(value="/mobilize/{mobilizeId}/pdf", method=RequestMethod.GET)
	@ResponseBody
	public void genMobilizePdf(HttpServletRequest request, HttpServletResponse response, @PathVariable Long mobilizeId) {
		MobilizeMaster mobilizeMaster = mobilizeRepository.findById(mobilizeId).get();
		
		if(null != mobilizeMaster) {
			Date dateToCal = mobilizeMaster.getCreatedDate();
			Calendar calendarToCal = Calendar.getInstance();
			calendarToCal.setTime(dateToCal);
			
			Integer day = calendarToCal.get(Calendar.DATE);
			Integer month = calendarToCal.get(Calendar.MONTH);
			Integer year = calendarToCal.get(Calendar.YEAR);
			
			BigDecimal amountSum 			= new BigDecimal(BigDecimal.ZERO.toString());
			for (Mobilize balance : mobilizeMaster.getMobilizes()) {
				amountSum = amountSum.add(balance.getAmount());
			}
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SEMETER", year+543);
			params.put("PRODUCT_CODE", Setup.getMONTH_TH().get(month));
			params.put("DATELINE", day);
			params.put("AMOUNT_TEXT", amountToWord(amountSum));
			params.put("NAME", mobilizeMaster.getStudentName());
			params.put("REF1", mobilizeMaster.getRef1());
			params.put("REF2", mobilizeMaster.getRef2());
			params.put("TOTAL_AMOUNT", amountSum);
			params.put("IMG1", new ClassPathResource("/jasper/embled.jpg").getPath());
			params.put("IMG2", new ClassPathResource("/jasper/ktb.jpg").getPath());
			
			
			List<Mobilize> mobilizeList = mobilizeMaster.getMobilizes();
			Collection<Map<String, ?>> resultSet = new ArrayList<>();
			for (Mobilize mobilize : mobilizeList) {
				Map<String, Object> mobilizeDetailList = new HashMap<>();
				mobilizeDetailList.put("DESCRIPTION", mobilize.getDescription());
				mobilizeDetailList.put("AMOUNT", mobilize.getAmount());
				mobilizeDetailList.put("NO", mobilize.getNo());
				resultSet.add(mobilizeDetailList);
			}
			
			
			try {
				File file = new ClassPathResource("/jasper/MOBILIZE_REPORT.jasper").getFile();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zipO = new ZipOutputStream(baos);
				
				Pageable page = new PageRequest(0, Integer.MAX_VALUE);
				Page<Student> studentList = studentRepos.findByActive('Y', page);
				log.info("student size "+studentList.getSize());
				for (Student student : studentList.getContent()) {
					
					params.put("NAME", student.getStName());
					params.put("REF1", student.getStGrade());
					params.put("REF2", student.getStNatid());
					
					
					ZipEntry ze = new ZipEntry(student.getStNatid()+".pdf");
					zipO.putNextEntry(ze);
					InputStream path = new FileInputStream(file);
					Collection<Map<String, ?>> myColl = (Collection<Map<String, ?>>) resultSet;
					
					JRMapCollectionDataSource ds = new JRMapCollectionDataSource(myColl);
					JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, ds);
					byte[] arrayByte = JasperExportManager.exportReportToPdf(jasperPrint);
					
					zipO.write(arrayByte, 0, arrayByte.length);
					zipO.closeEntry();
					
				}
				zipO.flush();
				baos.flush();
				zipO.close();
				baos.close();
				//MAO
				response.setContentType("application/zip");
				response.setHeader("Content-disposition","attachment;filename=MOBILIZE_REPORT_"+System.currentTimeMillis()+".zip"); 
				ServletOutputStream outstream = response.getOutputStream();
				outstream.write(baos.toByteArray());
				outstream.flush();
				outstream.close();
				
				

			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
	}
	
	@RequestMapping(value="/student/template/excel", method=RequestMethod.GET)
	@ResponseBody
	public void studentTemplateExcel(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(this.getClass().getClassLoader().getResourceAsStream("jasper/template_excel.xlsx"));		
			response.setContentType("application/vnd.ms-excel");
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment;filename=template_excel.xlsx");
			ServletOutputStream out = response.getOutputStream();
			workbook.write(out);
			workbook.close();
			out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static String[] numStrings = {"", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า"};
	
	public String amountToWord(BigDecimal amount) {
		
		BigDecimal amont = amount;
		log.info(amont.toPlainString());
		log.info(""+amont.intValue());
		log.info(""+amont.scale());
		
		//scale value
		String scaleStr = amont.toPlainString().substring(amont.toPlainString().length()-amont.scale(), amont.toPlainString().length());
		String decimalStr = "";
		for (int i = 0; i < scaleStr.length(); i++) {
			String string = String.valueOf(scaleStr.charAt(i));
			if(i == 0) {
				decimalStr += numStrings[Integer.parseInt(string)];
				if(StringUtils.isNotBlank(numStrings[Integer.parseInt(string)])) {
					decimalStr += "สิบ";
				}
				
			} else {
				decimalStr += numStrings[Integer.parseInt(string)];
			}
		}
		if(StringUtils.isNotBlank(decimalStr)) {
			decimalStr += "สตางค์";
		}
		log.info(decimalStr);
		
		//full value
		String fullStr = String.valueOf(amont.intValue());
		fullStr = StringUtils.leftPad(fullStr, 7, "0");
		log.info(fullStr);
		String text = "";
		for (int i = 0; i < fullStr.length(); i++) {
			String string = String.valueOf(fullStr.charAt(i));
			if( 7 == fullStr.length()) {
				
				switch (i) {
				case 0:
					text += numStrings[Integer.parseInt(string)];
					if(StringUtils.isNotBlank(numStrings[Integer.parseInt(string)])) {
						text += "ล้าน";
					}
					break;
				case 1:
					text += numStrings[Integer.parseInt(string)];
					if(StringUtils.isNotBlank(numStrings[Integer.parseInt(string)])) {
						text += "แสน";
					}
					break;
				case 2:
					text += numStrings[Integer.parseInt(string)];
					if(StringUtils.isNotBlank(numStrings[Integer.parseInt(string)])) {
						text += "หมื่น";
					}
					break;
				case 3:
					text += numStrings[Integer.parseInt(string)];
					if(StringUtils.isNotBlank(numStrings[Integer.parseInt(string)])) {
						text += "พัน";
					}
					break;
				case 4:
					text += numStrings[Integer.parseInt(string)];
					if(StringUtils.isNotBlank(numStrings[Integer.parseInt(string)])) {
						text += "ร้อย";
					}
					break;
				case 5:
					if(2 == Integer.parseInt(string)) {
						text +="ยี่สิบ";
					} else {
						text += numStrings[Integer.parseInt(string)];
						if(StringUtils.isNotBlank(numStrings[Integer.parseInt(string)])) {
							text += "สิบ";
						}
					}
					
					break;
				case 6:
					if(1 == Integer.parseInt(string)) {
						text += "เอ็ด";
					} else {
						text += numStrings[Integer.parseInt(string)];
					}
					
					break;
				default:
					text += "";
					break;
				}
				
			}
			
		}
		String result = text+"บาท"+decimalStr;
		log.info(result);
		return result;
	}

}
