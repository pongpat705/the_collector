package th.co.collector.components;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import th.co.collector.constants.Role;
import th.co.collector.entities.parameter.SystemParameter;
import th.co.collector.entities.user.User;
import th.co.collector.entities.user.UserRole;
import th.co.collector.repositories.parameter.SystemParameterRepository;
import th.co.collector.repositories.user.UserRepository;
import th.co.collector.repositories.user.UserRoleRepository;

@Component
@Order(2)
public class Setup {

	
private final Logger log = LoggerFactory.getLogger(this.getClass());	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private SystemParameterRepository systemParameterRepository;
	
	public static Map<Integer, String> MONTH_TH;
	
	public static Map<Integer, String> getMONTH_TH() {
		return MONTH_TH;
	}

	@PostConstruct
	 public void settingData(){
		initialRole();
		initCommon();
		initialSystemParameter();
		
		
	 }
	
	public void initCommon() {
		MONTH_TH = new HashMap<>();
		MONTH_TH.put(0, "มกราคม");
		MONTH_TH.put(1, "กุมภาพันธ์คม");
		MONTH_TH.put(2, "มีนาคม");
		MONTH_TH.put(3, "เมษายน");
		MONTH_TH.put(4, "พฤษภาคม");
		MONTH_TH.put(5, "มิภุนายน");
		MONTH_TH.put(6, "กรกฎาคม");
		MONTH_TH.put(7, "สิงหาคม");
		MONTH_TH.put(8, "กันยายน");
		MONTH_TH.put(9, "ตุลาคม");
		MONTH_TH.put(10, "พฤศจิกายน");
		MONTH_TH.put(11, "ธันวาคม");
	};
	 
	@Transactional
	public void initialRole(){
		 log.info("inserting admin");
		 
		 	
			User adminUser = userRepository.findByUserName("supadmin");
			if(null == adminUser) {
				adminUser = new User();
				adminUser.setEnabled("1");
				adminUser.setUserName("supadmin");
				adminUser.setPassword("supadmin");
				userRepository.save(adminUser);
				
				UserRole adminRole = new UserRole();
				adminRole.setEnabled("1");
				adminRole.setRole(Role.ROLE_SUPER_ADMIN);
				adminRole.setUser(adminUser);
				
				userRoleRepository.save(adminRole);
			}
			
	}
	
	@Transactional
	public void initialSystemParameter() {
		
		SystemParameter schoolIncome = systemParameterRepository.findByParamGroupAndParamCode("FUNCTION", "SCHOOL_INCOME");
		if(null == schoolIncome) {
			schoolIncome = new SystemParameter();
			schoolIncome.setCreateBy("SYSTEM");
			schoolIncome.setCreateDate(new Date());
			schoolIncome.setParamGroup("FUNCTION");
			schoolIncome.setParamCode("SCHOOL_INCOME");
			schoolIncome.setValue1("บันทึกคุมรายได้สถานศึกษา");
			systemParameterRepository.save(schoolIncome);
		}
		
		SystemParameter income = systemParameterRepository.findByParamGroupAndParamCode("SCHOOL_INCOME", "INCOME");
		if(null == income) {
			income = new SystemParameter();
			income.setCreateBy("SYSTEM");
			income.setCreateDate(new Date());
			income.setParamGroup("SCHOOL_INCOME");
			income.setParamCode("INCOME");
			income.setValue1("รายรับ");
			systemParameterRepository.save(income);
		}
		
		SystemParameter balanceReport = systemParameterRepository.findByParamGroupAndParamCode("FUNCTION", "BALANCE_REPORT");
		if(null == balanceReport) {
			balanceReport = new SystemParameter();
			balanceReport.setCreateBy("SYSTEM");
			balanceReport.setCreateDate(new Date());
			balanceReport.setParamGroup("FUNCTION");
			balanceReport.setParamCode("BALANCE_REPORT");
			balanceReport.setValue1("รายงานคงเหลือประจำวัน");
			systemParameterRepository.save(balanceReport);
		}
		
		SystemParameter makeReport = systemParameterRepository.findByParamGroupAndParamCode("BALANCE_REPORT", "MAKE");
		if(null == makeReport) {
			makeReport = new SystemParameter();
			makeReport.setCreateBy("SYSTEM");
			makeReport.setCreateDate(new Date());
			makeReport.setParamGroup("BALANCE_REPORT");
			makeReport.setParamCode("MAKE");
			makeReport.setValue1("สร้างรายงาน");
			systemParameterRepository.save(makeReport);
		}
		
		SystemParameter balanceList = systemParameterRepository.findByParamGroupAndParamCode("BALANCE_REPORT", "LIST");
		if(null == balanceList) {
			balanceList = new SystemParameter();
			balanceList.setCreateBy("SYSTEM");
			balanceList.setCreateDate(new Date());
			balanceList.setParamGroup("BALANCE_REPORT");
			balanceList.setParamCode("LIST");
			balanceList.setValue1("รายงานทั้งหมด");
			systemParameterRepository.save(balanceList);
		}
		
		SystemParameter cashBook = systemParameterRepository.findByParamGroupAndParamCode("FUNCTION", "CASH_BOOK");
		if(null == cashBook) {
			cashBook = new SystemParameter();
			cashBook.setCreateBy("SYSTEM");
			cashBook.setCreateDate(new Date());
			cashBook.setParamGroup("FUNCTION");
			cashBook.setParamCode("CASH_BOOK");
			cashBook.setValue1("สมุดเงินสด");
			systemParameterRepository.save(cashBook);
		}
		
		SystemParameter importData = systemParameterRepository.findByParamGroupAndParamCode("FUNCTION", "IMPORT_DATA");
		if(null == importData) {
			importData = new SystemParameter();
			importData.setCreateBy("SYSTEM");
			importData.setCreateDate(new Date());
			importData.setParamGroup("FUNCTION");
			importData.setParamCode("IMPORT_DATA");
			importData.setValue1("นำเข้าข้อมูลจากธนาคาร");
			systemParameterRepository.save(importData);
		}
		
		SystemParameter report = systemParameterRepository.findByParamGroupAndParamCode("FUNCTION", "REPORT");
		if(null == report) {
			report = new SystemParameter();
			report.setCreateBy("SYSTEM");
			report.setCreateDate(new Date());
			report.setParamGroup("FUNCTION");
			report.setParamCode("REPORT");
			report.setValue1("รายงาน");
			systemParameterRepository.save(report);
		}
		
		SystemParameter balance = systemParameterRepository.findByParamGroupAndParamCode("REPORT", "BALANCE_REPORT");
		if(null == balance) {
			balance = new SystemParameter();
			balance.setCreateBy("SYSTEM");
			balance.setCreateDate(new Date());
			balance.setParamGroup("REPORT");
			balance.setParamCode("BALANCE_REPORT");
			balance.setValue1("เงินคงเหลือประจำวัน");
			systemParameterRepository.save(balance);
		}
		
		SystemParameter manageUser = systemParameterRepository.findByParamGroupAndParamCode("FUNCTION", "MANAGE_USER");
		if(null == manageUser) {
			manageUser = new SystemParameter();
			manageUser.setCreateBy("SYSTEM");
			manageUser.setCreateDate(new Date());
			manageUser.setParamGroup("FUNCTION");
			manageUser.setParamCode("MANAGE_USER");
			manageUser.setValue1("จัดการผู้ใช้");
			systemParameterRepository.save(manageUser);
		}
		
		SystemParameter moneyControlForm = systemParameterRepository.findByParamGroupAndParamCode("FUNCTION", "MONEY_CONTROL_FORM");
		if(null == moneyControlForm) {
			moneyControlForm = new SystemParameter();
			moneyControlForm.setCreateBy("SYSTEM");
			moneyControlForm.setCreateDate(new Date());
			moneyControlForm.setParamGroup("FUNCTION");
			moneyControlForm.setParamCode("MONEY_CONTROL_FORM");
			moneyControlForm.setValue1("บันทึกทะเบียนคุมเงิน");
			systemParameterRepository.save(moneyControlForm);
		}
		
		SystemParameter nBudget = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "N_BUDGET");
		if(null == nBudget) {
			nBudget = new SystemParameter();
			nBudget.setCreateBy("SYSTEM");
			nBudget.setCreateDate(new Date());
			nBudget.setParamGroup("MONEY_CONTROL_FORM");
			nBudget.setParamCode("N_BUDGET");
			nBudget.setValue1("เงินนอกงบประมาณ");
			systemParameterRepository.save(nBudget);
		}
		
		SystemParameter loanFund = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "LOAN_FUND");
		if(null == loanFund) {
			loanFund = new SystemParameter();
			loanFund.setCreateBy("SYSTEM");
			loanFund.setCreateDate(new Date());
			loanFund.setParamGroup("MONEY_CONTROL_FORM");
			loanFund.setParamCode("LOAN_FUND");
			loanFund.setValue1("ดำเนินการกองทุนกู้ยืม");
			systemParameterRepository.save(loanFund);
		}
		
		SystemParameter subsidy = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "SUBSIDY");
		if(null == subsidy) {
			subsidy = new SystemParameter();
			subsidy.setCreateBy("SYSTEM");
			subsidy.setCreateDate(new Date());
			subsidy.setParamGroup("MONEY_CONTROL_FORM");
			subsidy.setParamCode("SUBSIDY");
			subsidy.setValue1("อุดหนุนปัจจัยพื้นฐาน");
			systemParameterRepository.save(subsidy);
		}
		
		SystemParameter insurance = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "INSURANCE");
		if(null == insurance) {
			insurance = new SystemParameter();
			insurance.setCreateBy("SYSTEM");
			insurance.setCreateDate(new Date());
			insurance.setParamGroup("MONEY_CONTROL_FORM");
			insurance.setParamCode("INSURANCE");
			insurance.setValue1("ประกันอุบัติเหตุ");
			systemParameterRepository.save(insurance);
		}
		
		SystemParameter foreignTeacher = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "FOREIGN_TEACHER");
		if(null == foreignTeacher) {
			foreignTeacher = new SystemParameter();
			foreignTeacher.setCreateBy("SYSTEM");
			foreignTeacher.setCreateDate(new Date());
			foreignTeacher.setParamGroup("MONEY_CONTROL_FORM");
			foreignTeacher.setParamCode("FOREIGN_TEACHER");
			foreignTeacher.setValue1("จ้างครูต่างชาติ");
			systemParameterRepository.save(foreignTeacher);
		}
		
		SystemParameter expense = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "EXPENSE");
		if(null == expense) {
			expense = new SystemParameter();
			expense.setCreateBy("SYSTEM");
			expense.setCreateDate(new Date());
			expense.setParamGroup("MONEY_CONTROL_FORM");
			expense.setParamCode("EXPENSE");
			expense.setValue1("อุดหนุนค่าใช้จ่ายรายหัว");
			systemParameterRepository.save(expense);
		}
		
		SystemParameter educationSupport = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "EDUCATION_SUPPORT");
		if(null == educationSupport) {
			educationSupport = new SystemParameter();
			educationSupport.setCreateBy("SYSTEM");
			educationSupport.setCreateDate(new Date());
			educationSupport.setParamGroup("MONEY_CONTROL_FORM");
			educationSupport.setParamCode("EDUCATION_SUPPORT");
			educationSupport.setValue1("สนับสนุนการศึกษาโดยไม่เสียค่าใช้จ่าย");
			systemParameterRepository.save(educationSupport);
		}
		
		SystemParameter tax = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "TAX");
		if(null == tax) {
			tax = new SystemParameter();
			tax.setCreateBy("SYSTEM");
			tax.setCreateDate(new Date());
			tax.setParamGroup("MONEY_CONTROL_FORM");
			tax.setParamCode("TAX");
			tax.setValue1("ภาษีรายได้หัก ณ ที่จ่าย");
			systemParameterRepository.save(tax);
		}
		
		SystemParameter revenue = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "REVENUE");
		if(null == revenue) {
			revenue = new SystemParameter();
			revenue.setCreateBy("SYSTEM");
			revenue.setCreateDate(new Date());
			revenue.setParamGroup("MONEY_CONTROL_FORM");
			revenue.setParamCode("REVENUE");
			revenue.setValue1("รายได้แผ่นดิน");
			systemParameterRepository.save(revenue);
		}
		
		SystemParameter contractCollateral = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "CONTRACT_COLLATERAL");
		if(null == contractCollateral) {
			contractCollateral = new SystemParameter();
			contractCollateral.setCreateBy("SYSTEM");
			contractCollateral.setCreateDate(new Date());
			contractCollateral.setParamGroup("MONEY_CONTROL_FORM");
			contractCollateral.setParamCode("CONTRACT_COLLATERAL");
			contractCollateral.setValue1("หลักประกันสัญญา");
			systemParameterRepository.save(contractCollateral);
		}
		
		SystemParameter scholarShip = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "SCHOLARSHIP");
		if(null == scholarShip) {
			scholarShip = new SystemParameter();
			scholarShip.setCreateBy("SYSTEM");
			scholarShip.setCreateDate(new Date());
			scholarShip.setParamGroup("MONEY_CONTROL_FORM");
			scholarShip.setParamCode("SCHOLARSHIP");
			scholarShip.setValue1("เงินทุนการศึกษา");
			systemParameterRepository.save(scholarShip);
		}
		
		SystemParameter healthCheck = systemParameterRepository.findByParamGroupAndParamCode("MONEY_CONTROL_FORM", "HEALTH_CHECK");
		if(null == healthCheck) {
			healthCheck = new SystemParameter();
			healthCheck.setCreateBy("SYSTEM");
			healthCheck.setCreateDate(new Date());
			healthCheck.setParamGroup("MONEY_CONTROL_FORM");
			healthCheck.setParamCode("HEALTH_CHECK");
			healthCheck.setValue1("เงินค่าตรวจสุขภาพ");
			systemParameterRepository.save(healthCheck);
		}
		
		
		SystemParameter cheque = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "CHEQUE");
		if(null == cheque) {
			cheque = new SystemParameter();
			cheque.setCreateBy("SYSTEM");
			cheque.setCreateDate(new Date());
			cheque.setParamGroup("DROPDOWN_BALANCE");
			cheque.setParamCode("CHEQUE");
			cheque.setValue1("เช็ค");
			cheque.setValue2("ฉบับ");
			systemParameterRepository.save(cheque);
		}
		
		SystemParameter order = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "ORDER");
		if(null == order) {
			order = new SystemParameter();
			order.setCreateBy("SYSTEM");
			order.setCreateDate(new Date());
			order.setParamGroup("DROPDOWN_BALANCE");
			order.setParamCode("ORDER");
			order.setValue1("ธนาณัติ");
			order.setValue2("ฉบับ");
			systemParameterRepository.save(order);
		}
		
		SystemParameter sPayment = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "SEC_PAYMENT");
		if(null == sPayment) {
			sPayment = new SystemParameter();
			sPayment.setCreateBy("SYSTEM");
			sPayment.setCreateDate(new Date());
			sPayment.setParamGroup("DROPDOWN_BALANCE");
			sPayment.setParamCode("SEC_PAYMENT");
			sPayment.setValue1("ใบสำคัญรองจ่าย");
			sPayment.setValue2("ฉบับ");
			systemParameterRepository.save(sPayment);
		}
		
		SystemParameter bCert = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "BORROW_CERT");
		if(null == bCert) {
			bCert = new SystemParameter();
			bCert.setCreateBy("SYSTEM");
			bCert.setCreateDate(new Date());
			bCert.setParamGroup("DROPDOWN_BALANCE");
			bCert.setParamCode("BORROW_CERT");
			bCert.setValue1("สัญญารับรองการยืมเงิน");
			bCert.setValue2("ฉบับ");
			systemParameterRepository.save(bCert);
		}
		
		SystemParameter bPayment = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "BILL_PAYMENT");
		if(null == bPayment) {
			bPayment = new SystemParameter();
			bPayment.setCreateBy("SYSTEM");
			bPayment.setCreateDate(new Date());
			bPayment.setParamGroup("DROPDOWN_BALANCE");
			bPayment.setParamCode("BILL_PAYMENT");
			bPayment.setValue1("ใบเบิกเงินเพื่อจ่ายในราชการ");
			bPayment.setValue2("ฉบับ");
			systemParameterRepository.save(bPayment);
		}
		
		SystemParameter book = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "BOOK");
		if(null == book) {
			book = new SystemParameter();
			book.setCreateBy("SYSTEM");
			book.setCreateDate(new Date());
			book.setParamGroup("DROPDOWN_BALANCE");
			book.setParamCode("BOOK");
			book.setValue1("สมุดคู่ฝาก");
			book.setValue2("เล่ม");
			systemParameterRepository.save(book);
		}
		
		SystemParameter book1 = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "BOOK1");
		if(null == book1) {
			book1 = new SystemParameter();
			book1.setCreateBy("SYSTEM");
			book1.setCreateDate(new Date());
			book1.setParamGroup("DROPDOWN_BALANCE");
			book1.setParamCode("BOOK1");
			book1.setValue1("ธ.กรุงไทย ปทุมฯ(อุดหนุน)");
			book1.setValue2("เล่ม");
			systemParameterRepository.save(book1);
		}
		
		SystemParameter book2 = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "BOOK2");
		if(null == book2) {
			book2 = new SystemParameter();
			book2.setCreateBy("SYSTEM");
			book2.setCreateDate(new Date());
			book2.setParamGroup("DROPDOWN_BALANCE");
			book2.setParamCode("BOOK2");
			book2.setValue1("ธ.กรุงไทย ปทุมฯ(กองทุน)");
			book2.setValue2("เล่ม");
			systemParameterRepository.save(book2);
		}
		
		SystemParameter book3 = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "BOOK3");
		if(null == book3) {
			book3 = new SystemParameter();
			book3.setCreateBy("SYSTEM");
			book3.setCreateDate(new Date());
			book3.setParamGroup("DROPDOWN_BALANCE");
			book3.setParamCode("BOOK3");
			book3.setValue1("ธ.กรุงไทย สามโคก(ทุนฯ)");
			book3.setValue2("เล่ม");
			systemParameterRepository.save(book3);
		}
		
		SystemParameter book4 = systemParameterRepository.findByParamGroupAndParamCode("DROPDOWN_BALANCE", "BOOK4");
		if(null == book4) {
			book4 = new SystemParameter();
			book4.setCreateBy("SYSTEM");
			book4.setCreateDate(new Date());
			book4.setParamGroup("DROPDOWN_BALANCE");
			book4.setParamCode("BOOK4");
			book4.setValue1("ธ.กรุงไทย สามโคก(ทุนฯ)");
			book4.setValue2("เล่ม");
			systemParameterRepository.save(book4);
		}
	}
	
}
