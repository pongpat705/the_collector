package th.co.collector.components;

import java.util.Date;

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
	
	@PostConstruct
	 public void settingData(){
//		initialRole();
		
		initialSystemParameter();
	 }
	 
	@Transactional
	public void initialRole(){
		 log.info("inserting admin");
			User adminUser = new User();
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
	}
	
}
