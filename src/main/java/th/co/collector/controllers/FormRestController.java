package th.co.collector.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.co.collector.entities.TransCode;
import th.co.collector.entities.chest.Chest;
import th.co.collector.entities.moneycontrol.Balance;
import th.co.collector.entities.moneycontrol.BalanceMaster;
import th.co.collector.entities.moneycontrol.CashBook;
import th.co.collector.entities.moneycontrol.MoneyControl;
import th.co.collector.entities.moneycontrol.SchoolBudget;
import th.co.collector.repositories.BalanceMasterRepository;
import th.co.collector.repositories.CashBookRepository;
import th.co.collector.repositories.ChestRepository;
import th.co.collector.repositories.SchoolBudgetRepository;
import th.co.collector.repositories.form.MoneyControlRepository;
import th.co.collector.services.CommonService;

@RestController
@RequestMapping(value="/service")
public class FormRestController {
	
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
	
	@RequestMapping(value="/saveMoneyControl", method=RequestMethod.POST)
	public void saveMoneyControl(@RequestParam String controlType, @RequestBody List<MoneyControl> moneyControlList, HttpServletResponse sResponse) {
		
		Date entryDate = new Date();
		TransCode transCode = commonService.genTransCode(controlType);
		
		BigDecimal cashInSum 		= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal cashOutSum 		= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal balanceSum 		= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal temporarySum 	= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal compensationSum 	= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal usabilitySum 	= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal utilitySum 		= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal materialSum 		= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal durableSum 		= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal landBuildSum 	= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal subsidySum 		= new BigDecimal(BigDecimal.ZERO.toString());
		for (MoneyControl moneyControl : moneyControlList) {
			moneyControl.setControlType(controlType);
			moneyControl.setTransCode(transCode.getCode());
			moneyControl.setEntryDate(entryDate);
			//sum
			cashInSum = cashInSum.add(				null == moneyControl.getCashIn() 		? BigDecimal.ZERO : moneyControl.getCashIn()		);
			cashOutSum = cashOutSum.add(			null == moneyControl.getCashOut() 		? BigDecimal.ZERO : moneyControl.getCashOut()		);
			balanceSum = balanceSum.add(			null == moneyControl.getBalance() 		? BigDecimal.ZERO : moneyControl.getBalance()		);
			temporarySum = temporarySum.add(		null == moneyControl.getTemporary() 	? BigDecimal.ZERO : moneyControl.getTemporary()		);
			compensationSum = compensationSum.add(	null == moneyControl.getCompensation() 	? BigDecimal.ZERO : moneyControl.getCompensation()	);
			usabilitySum = usabilitySum.add(		null == moneyControl.getUsability() 	? BigDecimal.ZERO : moneyControl.getUsability()		);
			utilitySum = utilitySum.add(			null == moneyControl.getUtility() 		? BigDecimal.ZERO : moneyControl.getUtility()		);
			materialSum = materialSum.add(			null == moneyControl.getMaterial() 		? BigDecimal.ZERO : moneyControl.getMaterial()		);
			durableSum = durableSum.add(			null == moneyControl.getDurable() 		? BigDecimal.ZERO : moneyControl.getDurable()		);
			landBuildSum = landBuildSum.add(		null == moneyControl.getLandBuild() 	? BigDecimal.ZERO : moneyControl.getLandBuild()		);
			subsidySum = subsidySum.add(			null == moneyControl.getSubsidy() 		? BigDecimal.ZERO : moneyControl.getSubsidy()		);
			//sum
			moneyRepository.save(moneyControl);
		}
		
		MoneyControl moneySum = new MoneyControl();
		moneySum.setEntryDate(entryDate);
		moneySum.setControlType(controlType);
		moneySum.setTransCode("SUM_"+transCode.getCode());
		moneySum.setCashIn(cashInSum);
		moneySum.setCashOut(cashOutSum);
		moneySum.setBalance(balanceSum);
		moneySum.setTemporary(temporarySum);
		moneySum.setCompensation(compensationSum);
		moneySum.setUsability(usabilitySum);
		moneySum.setUtility(utilitySum);
		moneySum.setMaterial(materialSum);
		moneySum.setDurable(durableSum);
		moneySum.setLandBuild(landBuildSum);
		moneySum.setSubsidy(subsidySum);
		moneySum.setRemark("SUM");
		moneyRepository.save(moneySum);
		
		sResponse.setStatus(HttpStatus.CREATED.value());
		
	}
	
	@RequestMapping(value="/saveCashBook", method=RequestMethod.POST)
	public void saveCashBook(@RequestBody List<CashBook> cashBookList, HttpServletResponse sResponse) {
		
		Date entryDate = new Date();
		
		TransCode transCode = commonService.genTransCode("CASHBOOK");
		
		BigDecimal debitSum 			= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal creditBudgetSum 		= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal creditRevenueSum 	= new BigDecimal(BigDecimal.ZERO.toString());
		BigDecimal creditNbudget 		= new BigDecimal(BigDecimal.ZERO.toString());
		for (CashBook cashbook : cashBookList) {
			cashbook.setTransactionCode(transCode.getCode());
			cashbook.setEntryDate(entryDate);
			//sum
			debitSum 			= debitSum.add(			null == cashbook.getDebit()			? BigDecimal.ZERO :cashbook.getDebit()			);
			creditBudgetSum 	= creditBudgetSum.add(	null == cashbook.getCreditBudget()	? BigDecimal.ZERO :cashbook.getCreditBudget()	);
			creditRevenueSum 	= creditRevenueSum.add(	null == cashbook.getCreditRevenue()	? BigDecimal.ZERO :cashbook.getCreditRevenue()	);
			creditNbudget 		= creditNbudget.add(	null == cashbook.getCreditNbudget()	? BigDecimal.ZERO :cashbook.getCreditNbudget()	);
			//sum
			cashBookRepository.save(cashbook);
		}
		
		CashBook cashbook = new CashBook();
		cashbook.setEntryDate(entryDate);
		cashbook.setTransactionCode("SUM_"+transCode.getCode());
		cashbook.setDebit(debitSum);
		cashbook.setCreditBudget(creditBudgetSum);
		cashbook.setCreditRevenue(creditRevenueSum);
		cashbook.setCreditNbudget(creditNbudget);
		cashbook.setRemark("SUM");
		cashBookRepository.save(cashbook);
		
		sResponse.setStatus(HttpStatus.CREATED.value());
		
	}
	
	@RequestMapping(value="/saveBalanceMaster", method=RequestMethod.POST)
	public void saveBalanceMaster(@RequestBody BalanceMaster balanceMaster, HttpServletResponse sResponse) {
		
		Date entryDate = new Date();
		
		balanceMaster.setCreateDate(entryDate);;
		
		BigDecimal amountSum 			= new BigDecimal(BigDecimal.ZERO.toString());
		for (Balance balance : balanceMaster.getBalances()) {
			amountSum = amountSum.add(balance.getAmount());
		}
		Balance balanceSum = new Balance();
		balanceSum.setAmount(amountSum);
		balanceSum.setRemark("SUM");
		balanceMaster.getBalances().add(balanceSum);
		balanceMasterRepository.save(balanceMaster);
		
		
		sResponse.setStatus(HttpStatus.CREATED.value());
		
	}
	
	@RequestMapping(value="/saveSchoolBudget", method=RequestMethod.POST)
	public void saveSchoolBudget(@RequestBody List<SchoolBudget> schoolBudgetList, HttpServletResponse sResponse) {
		
		Date entryDate = new Date();
		
		BigDecimal amountSum 			= new BigDecimal(BigDecimal.ZERO.toString());
		for (SchoolBudget schoolBudget : schoolBudgetList) {
			schoolBudget.setEntryDate(entryDate);
			
			amountSum = amountSum.add(schoolBudget.getSum());
		}
		
		budgetRepository.saveAll(schoolBudgetList);
		
		Chest chest4 = chestRepository.findByAccountCode("BOOK4");
		chest4.setBalance(amountSum);
		
		chestRepository.save(chest4);
		
		sResponse.setStatus(HttpStatus.CREATED.value());
		
	}
	
	@RequestMapping(value="/getBalanceMaster", method=RequestMethod.GET)
	public BalanceMaster getBalanceMaster(@RequestParam Long masterId) {
		return balanceMasterRepository.findById(masterId).get();
	}
	
	

}
