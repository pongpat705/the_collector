package th.co.collector.entities.moneycontrol;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import th.co.collector.entities.BaseDomain;

@Entity
@Table(name = "CASH_BOOK")
@TableGenerator(name="GEN_CASH_BOOK", initialValue=0, allocationSize=1)
public class CashBook extends BaseDomain{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_CASH_BOOK")
    private Long bookId;
	
	private String transactionCode;
	
	private Date entryDate;
	
	private String docNo;
	
	private String bookNo;
	
	private String description;
	
	private BigDecimal debit;
	
	private BigDecimal creditBudget;
	
	private BigDecimal creditRevenue;
	
	private BigDecimal creditNbudget;
	
	private String remark;

	protected Long getBookId() {
		return bookId;
	}

	protected String getTransactionCode() {
		return transactionCode;
	}

	protected Date getEntryDate() {
		return entryDate;
	}

	protected String getDocNo() {
		return docNo;
	}

	protected String getBookNo() {
		return bookNo;
	}

	protected String getDescription() {
		return description;
	}

	protected BigDecimal getDebit() {
		return debit;
	}

	protected BigDecimal getCreditBudget() {
		return creditBudget;
	}

	protected BigDecimal getCreditRevenue() {
		return creditRevenue;
	}

	protected BigDecimal getCreditNbudget() {
		return creditNbudget;
	}

	protected String getRemark() {
		return remark;
	}

	protected void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	protected void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	protected void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	protected void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	protected void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	protected void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	protected void setCreditBudget(BigDecimal creditBudget) {
		this.creditBudget = creditBudget;
	}

	protected void setCreditRevenue(BigDecimal creditRevenue) {
		this.creditRevenue = creditRevenue;
	}

	protected void setCreditNbudget(BigDecimal creditNbudget) {
		this.creditNbudget = creditNbudget;
	}

	protected void setRemark(String remark) {
		this.remark = remark;
	}
	
}
