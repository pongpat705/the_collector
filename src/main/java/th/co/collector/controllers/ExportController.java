package th.co.collector.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import th.co.collector.entities.mobilize.Mobilize;
import th.co.collector.entities.mobilize.MobilizeMaster;
import th.co.collector.entities.moneycontrol.Balance;
import th.co.collector.entities.moneycontrol.BalanceMaster;
import th.co.collector.entities.parameter.SystemParameter;
import th.co.collector.repositories.BalanceMasterRepository;
import th.co.collector.repositories.MobilizeReporsitory;
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
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("DATE_YEAR", year+543);
			params.put("DATE_MONTH", Setup.getMONTH_TH().get(month));
			params.put("DATE_DAY", day);
			params.put("AMPHUR", balanceMaster.getAmphur());
			params.put("AMOUNT_TEXT", balanceMaster.getTotalText());
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
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("SEMETER", year+543);
			params.put("PRODUCT_CODE", Setup.getMONTH_TH().get(month));
			params.put("DATELINE", day);
			params.put("AMOUNT_TEXT", mobilizeMaster.getTotalText());
			params.put("NAME", mobilizeMaster.getStudentName());
			params.put("REF1", mobilizeMaster.getRef1());
			params.put("REF2", mobilizeMaster.getRef2());
			params.put("TOTAL_AMOUNT", mobilizeMaster.getTotalAmount());
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
			
			Collection<Map<String, ?>> myColl = (Collection<Map<String, ?>>) resultSet;
			
			JRMapCollectionDataSource ds = new JRMapCollectionDataSource(myColl);
			
			
			try {
				File file = new ClassPathResource("/jasper/MOBILIZE_REPORT.jasper").getFile();
				InputStream path = new FileInputStream(file);
				
				JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, ds);
				byte[] arrayByte = JasperExportManager.exportReportToPdf(jasperPrint);
				//MAO
				response.setContentType("application/octet-stream");
				response.setHeader("Content-disposition","attachment;filename=MOBILIZE_REPORT_"+System.currentTimeMillis()+".pdf"); 
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

}
