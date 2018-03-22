package th.co.collector.entities.moneycontrol;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CASH_BOOK")
@TableGenerator(name="GEN_CASH_BOOK", initialValue=0, allocationSize=1)
public class CashBook{

	@Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="GEN_CASH_BOOK")
    private Long bookId;
	
	private String transactionCode;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date entryDate;
	
	private String docNo;
	
	private String bookNo;
	
	private String description;
	
	private BigDecimal debit;
	
	private BigDecimal creditBudget;
	
	private BigDecimal creditRevenue;
	
	private BigDecimal creditNbudget;
	
	private String remark;
	
	private String recordBy;
	private String reviewBy;
	private String approveBy;
	public Long getBookId() {
		return bookId;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public String getDocNo() {
		return docNo;
	}
	public String getBookNo() {
		return bookNo;
	}
	public String getDescription() {
		return description;
	}
	public BigDecimal getDebit() {
		return debit;
	}
	public BigDecimal getCreditBudget() {
		return creditBudget;
	}
	public BigDecimal getCreditRevenue() {
		return creditRevenue;
	}
	public BigDecimal getCreditNbudget() {
		return creditNbudget;
	}
	public String getRemark() {
		return remark;
	}
	public String getRecordBy() {
		return recordBy;
	}
	public String getReviewBy() {
		return reviewBy;
	}
	public String getApproveBy() {
		return approveBy;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	public void setCreditBudget(BigDecimal creditBudget) {
		this.creditBudget = creditBudget;
	}
	public void setCreditRevenue(BigDecimal creditRevenue) {
		this.creditRevenue = creditRevenue;
	}
	public void setCreditNbudget(BigDecimal creditNbudget) {
		this.creditNbudget = creditNbudget;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setRecordBy(String recordBy) {
		this.recordBy = recordBy;
	}
	public void setReviewBy(String reviewBy) {
		this.reviewBy = reviewBy;
	}
	public void setApproveBy(String approveBy) {
		this.approveBy = approveBy;
	}
	
}
