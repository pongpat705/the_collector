package th.co.collector.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import th.co.collector.entities.Student;
import th.co.collector.entities.TransCode;
import th.co.collector.entities.chest.Chest;
import th.co.collector.entities.mobilize.MobilizeMaster;
import th.co.collector.entities.moneycontrol.BalanceMaster;
import th.co.collector.entities.moneycontrol.CashBook;
import th.co.collector.entities.moneycontrol.MoneyControl;
import th.co.collector.entities.moneycontrol.SchoolBudget;
import th.co.collector.repositories.BalanceMasterRepository;
import th.co.collector.repositories.CashBookRepository;
import th.co.collector.repositories.ChestRepository;
import th.co.collector.repositories.MobilizeReporsitory;
import th.co.collector.repositories.SchoolBudgetRepository;
import th.co.collector.repositories.StudentRepository;
import th.co.collector.repositories.form.MoneyControlRepository;
import th.co.collector.services.CommonService;

@RestController
@RequestMapping(value="/service")
public class FormRestController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	MoneyControlRepository moneyRepository;
	
	@Autowired
	CashBookRepository cashBookRepository;
	
	@Autowired
	BalanceMasterRepository balanceMasterRepository;
	
	@Autowired
	SchoolBudgetRepository budgetRepository;
	
	@Autowired
	ChestRepository chestRepository;
	
	@Autowired
	MobilizeReporsitory mobilizeMasterRepository;
	
	@Autowired
	StudentRepository studentRepos;
	
	@RequestMapping(value="/saveMoneyControl", method=RequestMethod.POST)
	public void saveMoneyControl(@RequestParam String controlType, @RequestBody List<MoneyControl> moneyControlList, HttpServletResponse sResponse, Authentication authentication) {
		
		Date entryDate = new Date();
		TransCode transCode = commonService.genTransCode(controlType);
		
//		BigDecimal cashInSum 		= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal cashOutSum 		= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal balanceSum 		= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal temporarySum 	= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal compensationSum 	= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal usabilitySum 	= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal utilitySum 		= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal materialSum 		= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal durableSum 		= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal landBuildSum 	= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal subsidySum 		= new BigDecimal(BigDecimal.ZERO.toString());
		for (MoneyControl moneyControl : moneyControlList) {
			moneyControl.setControlType(controlType);
			moneyControl.setTransCode(transCode.getCode());
			moneyControl.setEntryDate(entryDate);
			moneyControl.setRecordBy(authentication.getName());
			//sum
//			cashInSum = cashInSum.add(				null == moneyControl.getCashIn() 		? BigDecimal.ZERO : moneyControl.getCashIn()		);
//			cashOutSum = cashOutSum.add(			null == moneyControl.getCashOut() 		? BigDecimal.ZERO : moneyControl.getCashOut()		);
//			balanceSum = balanceSum.add(			null == moneyControl.getBalance() 		? BigDecimal.ZERO : moneyControl.getBalance()		);
//			temporarySum = temporarySum.add(		null == moneyControl.getTemporary() 	? BigDecimal.ZERO : moneyControl.getTemporary()		);
//			compensationSum = compensationSum.add(	null == moneyControl.getCompensation() 	? BigDecimal.ZERO : moneyControl.getCompensation()	);
//			usabilitySum = usabilitySum.add(		null == moneyControl.getUsability() 	? BigDecimal.ZERO : moneyControl.getUsability()		);
//			utilitySum = utilitySum.add(			null == moneyControl.getUtility() 		? BigDecimal.ZERO : moneyControl.getUtility()		);
//			materialSum = materialSum.add(			null == moneyControl.getMaterial() 		? BigDecimal.ZERO : moneyControl.getMaterial()		);
//			durableSum = durableSum.add(			null == moneyControl.getDurable() 		? BigDecimal.ZERO : moneyControl.getDurable()		);
//			landBuildSum = landBuildSum.add(		null == moneyControl.getLandBuild() 	? BigDecimal.ZERO : moneyControl.getLandBuild()		);
//			subsidySum = subsidySum.add(			null == moneyControl.getSubsidy() 		? BigDecimal.ZERO : moneyControl.getSubsidy()		);
			//sum
			moneyRepository.save(moneyControl);
		}
		
//		MoneyControl moneySum = new MoneyControl();
//		moneySum.setEntryDate(entryDate);
//		moneySum.setControlType(controlType);
//		moneySum.setTransCode("SUM_"+transCode.getCode());
//		moneySum.setCashIn(cashInSum);
//		moneySum.setCashOut(cashOutSum);
//		moneySum.setBalance(balanceSum);
//		moneySum.setTemporary(temporarySum);
//		moneySum.setCompensation(compensationSum);
//		moneySum.setUsability(usabilitySum);
//		moneySum.setUtility(utilitySum);
//		moneySum.setMaterial(materialSum);
//		moneySum.setDurable(durableSum);
//		moneySum.setLandBuild(landBuildSum);
//		moneySum.setSubsidy(subsidySum);
//		moneySum.setRemark("SUM");
//		moneyRepository.save(moneySum);
		
		sResponse.setStatus(HttpStatus.CREATED.value());
		
	}
	
	@RequestMapping(value="/approveCashBook", method=RequestMethod.GET)
	public void approveCashBook(@RequestParam Long bookId, HttpServletResponse sResponse, Authentication authentication) {
		Optional<CashBook> cashbooks = cashBookRepository.findById(bookId);
		CashBook cb = cashbooks.get();
		cb.setReviewBy(authentication.getName());
		cb.setApproveBy(authentication.getName());
		cashBookRepository.save(cb);
		sResponse.setStatus(HttpStatus.CREATED.value());
	}
	@RequestMapping(value="/saveCashBook", method=RequestMethod.POST)
	public void saveCashBook(@RequestBody List<CashBook> cashBookList, HttpServletResponse sResponse, Authentication authentication) {
		
		Date entryDate = new Date();
		
		TransCode transCode = commonService.genTransCode("CASHBOOK");
		
//		BigDecimal debitSum 			= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal creditBudgetSum 		= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal creditRevenueSum 	= new BigDecimal(BigDecimal.ZERO.toString());
//		BigDecimal creditNbudget 		= new BigDecimal(BigDecimal.ZERO.toString());
		for (CashBook cashbook : cashBookList) {
			cashbook.setTransactionCode(transCode.getCode());
			cashbook.setEntryDate(entryDate);
			cashbook.setRecordBy(authentication.getName());
			//sum
//			debitSum 			= debitSum.add(			null == cashbook.getDebit()			? BigDecimal.ZERO :cashbook.getDebit()			);
//			creditBudgetSum 	= creditBudgetSum.add(	null == cashbook.getCreditBudget()	? BigDecimal.ZERO :cashbook.getCreditBudget()	);
//			creditRevenueSum 	= creditRevenueSum.add(	null == cashbook.getCreditRevenue()	? BigDecimal.ZERO :cashbook.getCreditRevenue()	);
//			creditNbudget 		= creditNbudget.add(	null == cashbook.getCreditNbudget()	? BigDecimal.ZERO :cashbook.getCreditNbudget()	);
			//sum
			cashBookRepository.save(cashbook);
		}
		
//		CashBook cashbook = new CashBook();
//		cashbook.setEntryDate(entryDate);
//		cashbook.setTransactionCode("SUM_"+transCode.getCode());
//		cashbook.setDebit(debitSum);
//		cashbook.setCreditBudget(creditBudgetSum);
//		cashbook.setCreditRevenue(creditRevenueSum);
//		cashbook.setCreditNbudget(creditNbudget);
//		cashbook.setRemark("SUM");
//		cashBookRepository.save(cashbook);
		
		sResponse.setStatus(HttpStatus.CREATED.value());
		
	}
	
	@RequestMapping(value="/saveBalanceMaster", method=RequestMethod.POST)
	public void saveBalanceMaster(@RequestBody BalanceMaster balanceMaster, HttpServletResponse sResponse, Authentication authentication) {
		
		Date entryDate = new Date();
		
		balanceMaster.setCreateDate(entryDate);;
		balanceMaster.setRecordBy(authentication.getName());
		balanceMaster.setCreateBy(authentication.getName());
//		BigDecimal amountSum 			= new BigDecimal(BigDecimal.ZERO.toString());
//		for (Balance balance : balanceMaster.getBalances()) {
//			amountSum = amountSum.add(balance.getAmount());
//		}
//		Balance balanceSum = new Balance();
//		balanceSum.setAmount(amountSum);
//		balanceSum.setRemark("SUM");
//		balanceMaster.getBalances().add(balanceSum);
		balanceMasterRepository.save(balanceMaster);
		
		
		sResponse.setStatus(HttpStatus.CREATED.value());
		
	}
	
	@RequestMapping(value="/saveSchoolBudget", method=RequestMethod.POST)
	public void saveSchoolBudget(@RequestBody List<SchoolBudget> schoolBudgetList, HttpServletResponse sResponse, Authentication authentication) {
		
		Date entryDate = new Date();
		
		
		for (SchoolBudget schoolBudget : schoolBudgetList) {
			schoolBudget.setEntryDate(entryDate);
			schoolBudget.setRecordBy(authentication.getName());
			
			BigDecimal sum = new BigDecimal(BigDecimal.ZERO.toString());
			sum = sum.add(schoolBudget.getIncome());
			sum = sum.add(schoolBudget.getExtra());
			sum = sum.add(schoolBudget.getInterest());
			sum = sum.add(schoolBudget.getOther());
			schoolBudget.setSum(sum);
		}
		
		budgetRepository.saveAll(schoolBudgetList);
		
		BigDecimal amountSum 			= new BigDecimal(BigDecimal.ZERO.toString());
		for (SchoolBudget schoolBudget : budgetRepository.findAll()) {
			schoolBudget.setEntryDate(entryDate);
			schoolBudget.setRecordBy(authentication.getName());
			
			BigDecimal sum = new BigDecimal(BigDecimal.ZERO.toString());
			sum = sum.add(schoolBudget.getIncome());
			sum = sum.add(schoolBudget.getExtra());
			sum = sum.add(schoolBudget.getInterest());
			sum = sum.add(schoolBudget.getOther());
			schoolBudget.setSum(sum);
			
			amountSum = amountSum.add(schoolBudget.getSum());
		}
		
		Chest chest4 = chestRepository.findByAccountCode("BOOK4");
		chest4.setBalance(amountSum);
		
		chestRepository.save(chest4);
		
		sResponse.setStatus(HttpStatus.CREATED.value());
		
	}
	
	@RequestMapping(value="/getBalanceMaster", method=RequestMethod.GET)
	public BalanceMaster getBalanceMaster(@RequestParam Long masterId) {
		return balanceMasterRepository.findById(masterId).get();
	}
	
	@RequestMapping(value="/getChestBalance", method=RequestMethod.GET)
	public Map<String, Object> getChestBalance() {
		Map<String, Object> amount = new HashMap<>();
		Iterable<Chest> chestList = chestRepository.findAll();
		for (Chest chest : chestList) {
			if(null == chest.getBalance()) {
				chest.setBalance(new BigDecimal(BigDecimal.ZERO.toString()));
			}
			amount.put(chest.getAccountCode(), chest);
		}
		return amount;
	}
	
	
	@RequestMapping(value="/saveMobilizeMaster", method=RequestMethod.POST)
	public void saveMobilizeMaster(@RequestBody MobilizeMaster mobilizeMaster, HttpServletResponse sResponse) {
		
		Date entryDate = new Date();
		
		mobilizeMaster.setCreatedDate(entryDate);;
//		Boolean hasSum = false;
//		BigDecimal amountSum 			= new BigDecimal(BigDecimal.ZERO.toString());
//		for (Mobilize mobilize : mobilizeMaster.getMobilizes()) {
//			if(!"SUM".equals(mobilize.getDescription())) {
//				amountSum = amountSum.add(mobilize.getAmount());
//			} else {
//				hasSum = true;
//			}
//		}
//		if(hasSum) {
//			mobilizeMaster.getMobilizes().remove(mobilizeMaster.getMobilizes().size()-1);
//		}
//		Mobilize balanceSum = new Mobilize();
//		balanceSum.setAmount(amountSum);
//		balanceSum.setDescription("SUM");
//		mobilizeMaster.getMobilizes().add(balanceSum);
		mobilizeMasterRepository.save(mobilizeMaster);
		
		
		sResponse.setStatus(HttpStatus.CREATED.value());
		
	}
	
	@RequestMapping(value="/getMobilizeMaster", method=RequestMethod.GET)
	public MobilizeMaster getMobilizeMaster(@RequestParam Long mobilizeId) {
		return mobilizeMasterRepository.findById(mobilizeId).get();
	}
	
	
	@RequestMapping(value="/uploadFile" ,method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> uploadFile(@RequestParam MultipartFile uploadItem, Authentication authentication) throws Exception,ParseException{
		 List<Map<String, String>> dataFromExcel = new ArrayList<Map<String,String>>();
		 List<String> errorList = new ArrayList<String>();
		 Map<String, Object> resultObject = new HashMap<String, Object>();

		 try {
			 if(uploadItem.getContentType().equals("application/vnd.ms-excel") || uploadItem.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") || uploadItem.getContentType().equals("application/vnd.ms-excel.sheet.macroEnabled.12")){
				 dataFromExcel = uploadExcelFile(uploadItem, errorList);
				 //log.info("dataFromExcel : " + dataFromExcel);
				 resultObject.put("excel", dataFromExcel);
				 resultObject.put("errorList", errorList);
				 
				 for (Map<String, String> map : dataFromExcel) {
					 char active = 'Y';
					 
					 Student checkSt = studentRepos.findByStNatidAndActive(map.get("stNatid"), active);
					 if(null == checkSt) {
						Student st = new Student();
						st.setCreatedBy(authentication.getName());
						st.setCreatedDate(new Date());
						
						st.setActive(map.get("active").charAt(0));
						st.setStGrade(map.get("stGrade"));
						st.setStName(map.get("stName"));
						st.setStNatid(map.get("stNatid"));
						studentRepos.save(st);
					 } else {
						 checkSt.setActive(map.get("active").charAt(0));
						 checkSt.setStGrade(map.get("stGrade"));
						 checkSt.setStName(map.get("stName"));
						 checkSt.setUpdatedBy(authentication.getName());
						 checkSt.setUpdatedDate(new Date());
						 studentRepos.save(checkSt);
					 }
						
				}
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
	
	public List<Map<String, String>> uploadExcelFile(MultipartFile uploadItem, List<String> errorMap) throws OutOfMemoryError, Exception{
		log.info("Step : upload file toserrver and get datafrom excel");
		String fileType = uploadItem.getContentType();
		
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		
		if (uploadItem != null)
		if (uploadItem.getSize() > 0) {
			
				log.info("Size : " + uploadItem.getSize());				
				log.info("getInputStream : " + uploadItem.getInputStream());
	
				
				if(fileType.equals("application/vnd.ms-excel")){
					List<Map<String, String>> rawData = getRawDataFromExcel(uploadItem.getInputStream());
					if(rawData != null && !rawData.isEmpty()){
						//FillinData(rawData, errorMap);
					}
					result.addAll(rawData);
				}else if(fileType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
					List<Map<String, String>> rawData = getRawDataFromExcel(uploadItem.getInputStream());
					if(rawData != null && !rawData.isEmpty()){
						//FillinData(rawData, errorMap);
					}
					result.addAll(rawData);
				}
				log.info(" after get excel data ");
		}

		return result;
	}
	
	public List<Map<String, String>> getRawDataFromExcel(InputStream is) throws InvalidFormatException, IOException{
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
				
		try {
	        // read Excel file in Java
			XSSFWorkbook wb = new XSSFWorkbook(is);
			
			List<String> header = new ArrayList<String>();
			header.add("stName");
			header.add("stGrade");
			header.add("stNatid");
			header.add("active");
			
			int sheetNum = 0;
			XSSFSheet sheet = wb.getSheetAt(sheetNum);
			XSSFRow row;
			Iterator<Row> rows = sheet.rowIterator();
			
			List<Integer> col = new ArrayList<Integer>();
			col.add(0);
			col.add(1);
			col.add(2);
			col.add(3);
	
			String cellValue = "";
			while (rows.hasNext()) {					
				Map<String, String> document = new HashMap<String, String>();
			    row = (XSSFRow) rows.next();
			    if(row.getRowNum() != 0) {
				    for(int i : col) {
				    	if(null != row.getCell(i)) {
							cellValue = row.getCell(i).getStringCellValue();
					    	if(i == 0) {
					    		document.put(header.get(0), cellValue);
					    	}
					    	if(i == 1) {
					    		document.put(header.get(1), cellValue);
					    	}
					    	if(i == 2) {
					    		document.put(header.get(2), cellValue);
					    	}
					    	if(i == 3) {
					    		document.put(header.get(3), cellValue);
					    	}
				    	}
				    }	
				    
				    result.add(document);
			   }						    
			}		
			
			wb.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			Map<String, String> document = new HashMap<String, String>();
			document.put("e",e.getMessage());
			result.add(document); 
		}
		return result;
				
	}

}
